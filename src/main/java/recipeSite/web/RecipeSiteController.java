package recipeSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import recipeSite.service.LargeCategoryService;
import recipeSite.service.RecipeService;
import recipeSite.service.SmallCategoryService;
import recipeSite.service.UserService;

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

    //デフォルトページ
    @GetMapping
    public String index() {
        return "index";
    }

    //カテゴリーを選択した後のページ
    @GetMapping(path = "/recipe_category")
    public String recipeCategory() {
        return "recipe_category";
    }

    //検索結果が表示されるページ
    @GetMapping(path = "/search_result")
    public String searchResult() {
        return "search_result";
    }

    //マイページを表示
    @PostMapping(path = "/myPage")
    public String myPage() {
        return "my_page";
    }

    //ログインフォーム
    @PostMapping(path = "/loginForm")
    public String postLogin(Model model) {
        return "/login_form";
    }

    //ユーザー登録画面
    @GetMapping(path = "/newUser")
    public String newUser() {
        return "new_user";
    }

    //ユーザー登録実装
    @PostMapping(path = "/newUser")
    public ModelAndView register(
            ModelAndView mav,
            @RequestParam("username") String username,
            @RequestParam("encoded_password") String encoded_password) {

        RegisterUserForm registerUserForm = new RegisterUserForm();
        registerUserForm.setUsername(username);
        registerUserForm.setEncoded_password(encoded_password);
        try {
            //ユーザー情報を登録
            userService.create(registerUserForm);
            mav.setViewName("loginForm");
        } catch (Exception e) {
            mav.setViewName("newUser");
            mav.addObject("error", "ユーザー名は使用できません。：" + username);
        }
        return mav;
    }
}
