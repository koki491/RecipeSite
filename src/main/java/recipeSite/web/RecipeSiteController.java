package recipeSite.web;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import recipeSite.domain.*;
import recipeSite.service.LargeCategoryService;
import recipeSite.service.RecipeService;
import recipeSite.service.SmallCategoryService;
import recipeSite.service.LoginUserDetailsService;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "/")
public class RecipeSiteController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private LargeCategoryService largeCategoryService;
    @Autowired
    private SmallCategoryService smallCategoryService;
    @Autowired
    private LoginUserDetailsService loginUserDetailsService;

    private Integer large_category_id = 1;
    private Integer small_category_id = 1;

    @ModelAttribute
    RecipeForm recipeForm() {
        return new RecipeForm();
    }

    //デフォルトページ
    @GetMapping
    public String index(Model model) {
        List<LargeCategory> large_categories = largeCategoryService.findAll();
        model.addAttribute("large_categories", large_categories);
        return "index";
    }

    //カテゴリーを選択した後のページ
    @GetMapping(path = "/recipeCategory")
    public String recipeCategory(@RequestParam(required = false) Integer id, Model model, Model model1) {
        large_category_id = id;
        List<SmallCategory> smallCategories = smallCategoryService.findByLargeCategoryId(large_category_id);
        model.addAttribute("smallCategories", smallCategories);
        List<Recipe> recipes = recipeService.findByLargeSmallId(large_category_id, small_category_id);
        List<RecipeParse> recipes1 = parseRecipesInfo(recipes);
        model1.addAttribute("recipes", recipes1);
        return "recipeCategory";
    }

    @GetMapping(path = "/recipeCategoryChange")
    public String recipeCategoryChange(@RequestParam(required = false) Integer id, Model model, Model model1) {
        small_category_id = id;
        List<SmallCategory> smallCategories = smallCategoryService.findByLargeCategoryId(large_category_id);
        model.addAttribute("smallCategories", smallCategories);
        List<Recipe> recipes = recipeService.findByLargeSmallId(large_category_id, small_category_id);
        List<RecipeParse> recipes1 = parseRecipesInfo(recipes);
        model1.addAttribute("recipes", recipes1);
        return "recipeCategory";
    }

    //検索結果が表示されるページ
    @PostMapping(path = "/searchResult")
    public String searchResult(@Validated RecipeForm recipeForm, Model model) {
        List<Recipe> recipes = recipeService.findByName(recipeForm.getCooking_name());
        List<RecipeParse> recipes1 = parseRecipesInfo(recipes);
        model.addAttribute("recipes", recipes1);
        return "searchResult";
    }

    @GetMapping(path = "/recipe")
    public String Recipe(@RequestParam(required = false) Integer id, Model model) {
        Recipe recipe = recipeService.findById(id);
        RecipeParse recipe1 = parseRecipeInfo(recipe);
        model.addAttribute("recipe", recipe1);
        return "recipe";
    }

    //マイページを表示
    @RequestMapping(path = "/myPage")
    public String myPage(Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
        String username = userDetails.getUser().getUsername();
        String password = userDetails.getUser().getEncoded_password();
        Integer id = loginUserDetailsService.findByNamePass(username, password);
        List<Recipe> recipes = recipeService.findByUserId(id);
        List<RecipeParse> recipes1 = parseRecipesInfo(recipes);
        model.addAttribute("recipes", recipes1);
        return "myPage";
    }

    //レシピ画面
    @PostMapping(path = "postRecipe")
    public String postRecipe(Model model, Model model1) {
        List<LargeCategory> large_categories = largeCategoryService.findAll();
        model.addAttribute("large_categories", large_categories);
        List<SmallCategory> small_categories = smallCategoryService.findByLargeCategoryId(1);
        model1.addAttribute("small_categories", small_categories);
        return "postRecipe";
    }

    @PostMapping(path = "add")
    String create(@Validated RecipeForm recipeForm, @AuthenticationPrincipal LoginUserDetails userDetails) {
        Recipe recipe = new Recipe();
        String username = userDetails.getUser().getUsername();
        String password = userDetails.getUser().getEncoded_password();
        Integer id = loginUserDetailsService.findByNamePass(username, password);
        recipe.setUser_id(id);
        try {
            byte[] bytes = recipeForm.getCooking_image().getBytes();
            recipe.setCooking_image(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(recipeForm, recipe);
        recipeService.create(recipe);
        return "redirect:/myPage";
    }

    @GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, RecipeForm recipeForm) {
        Recipe recipe = recipeService.findById(id);
        BeanUtils.copyProperties(recipe, recipeForm);
        return "/edit";
    }

    @RequestMapping(path = "edit", params = "goToTop", method = RequestMethod.POST)
    String goToTop() {
        return "redirect:/myPage";
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id, RecipeForm recipeForm, @NotNull BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return editForm(id, recipeForm);
        }
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(recipeForm, recipe);
        recipe.setId(id);
        recipeService.update(recipe);
        return "redirect:/myPage";
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        Recipe recipe = new Recipe();
        recipe.setId(id);
        recipeService.delete(recipe);
        return "redirect:/myPage";
    }

    public String encodedBase64(byte[] data) {
        return Base64.encodeBase64String(data);
    }

    //byte配列をBase64型に変換したレシピ情報をまとめるメソッド
    public List<RecipeParse> parseRecipesInfo(List<Recipe> recipes) {
        List<RecipeParse> recipes1 = new ArrayList<RecipeParse>();
        for(Recipe recipe : recipes) {
            RecipeParse recipe1 = new RecipeParse();
            recipe1.setId(recipe.getId());
            recipe1.setUser_id(recipe.getUser_id());
            recipe1.setCooking_name(recipe.getCooking_name());
            recipe1.setCooking_image(encodedBase64(recipe.getCooking_image()));
            recipe1.setCooking_recipe(recipe.getCooking_recipe());
            recipe1.setSmall_category_id(recipe.getSmall_category_id());
            recipe1.setLarge_category_id(recipe.getLarge_category_id());
            recipes1.add(recipe1);
        }
        return recipes1;
    }

    public RecipeParse parseRecipeInfo(Recipe recipe) {
        RecipeParse recipeParse = new RecipeParse();
        recipeParse.setId(recipe.getId());
        recipeParse.setUser_id(recipe.getUser_id());
        recipeParse.setCooking_name(recipe.getCooking_name());
        recipeParse.setCooking_image(encodedBase64(recipe.getCooking_image()));
        recipeParse.setCooking_recipe(recipe.getCooking_recipe());
        recipeParse.setSmall_category_id(recipe.getSmall_category_id());
        recipeParse.setLarge_category_id(recipe.getLarge_category_id());

        return recipeParse;
    }

}
