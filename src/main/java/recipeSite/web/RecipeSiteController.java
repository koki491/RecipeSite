package recipeSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import recipeSite.domain.LargeCategory;
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
        Integer small_category_id = 1;
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

    //マイページを表示
    @PostMapping(path = "/myPage")
    public String myPage() {
        return "myPage";
    }

}
