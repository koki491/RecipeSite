package recipeSite.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import recipeSite.domain.LargeCategory;
import recipeSite.domain.LoginUserDetails;
import recipeSite.domain.Recipe;
import recipeSite.domain.SmallCategory;
import recipeSite.service.LargeCategoryService;
import recipeSite.service.RecipeService;
import recipeSite.service.SmallCategoryService;
import recipeSite.service.UserService;

import java.util.List;

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
    private UserService userService;

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
        //小タグを入力するとsmall_category_idが切り替わるようにしたい
        Integer small_category_id = 2;
        List<SmallCategory> smallCategories = smallCategoryService.findByLargeCategoryId(id);
        model.addAttribute("smallCategories", smallCategories);
        List<Recipe> recipes = recipeService.findByLargeSmallId(id, small_category_id);
        model1.addAttribute("recipes", recipes);
        return "recipeCategory";
    }

    //検索結果が表示されるページ
    @PostMapping(path = "/searchResult")
    public String searchResult(@Validated RecipeForm recipeForm, Model model) {
        List<Recipe> recipes = recipeService.findByName(recipeForm.getCooking_name());
        model.addAttribute("recipes", recipes);
        return "searchResult";
    }

    @GetMapping(path = "/recipe")
    public String Recipe(@RequestParam(required = false) Integer id, Model model) {
        Recipe recipe = recipeService.findById(id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    //マイページを表示
    @GetMapping(path = "/myPage")
    public String myPage() {
        return "myPage";
    }

    //レシ画面
    @GetMapping(path = "postRecipe")
    public String postRecipe(Model model, Model model1) {
        List<LargeCategory> large_categories = largeCategoryService.findAll();
        model.addAttribute("large_categories", large_categories);
        List<SmallCategory> small_categories = smallCategoryService.findByLargeCategoryId(1);
        model1.addAttribute("small_categories", small_categories);
        return "postRecipe";
    }

    @PostMapping(path = "add")
    String create(@Validated RecipeForm recipeForm) { // @AuthenticationPrincipal LoginUserDetails userDetails
        Recipe recipe = new Recipe();
//        recipe.setUser_id(userDetails.getUser().getId());
        BeanUtils.copyProperties(recipeForm, recipe);
        System.out.println(recipe.getSmall_category_id());
        recipeService.create(recipe);
        return "/myPage";
    }

}
