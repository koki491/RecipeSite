package recipeSite.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import recipeSite.service.RegisterUserService;

@Controller
public class LoginController {

    @Autowired
    private RegisterUserService registerUserService;

    //ログインページを表示
    @RequestMapping(path = "/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    //ログインフォーム
    @GetMapping(path = "/loginForm")
    public String loginForm(Model model) {
        return "loginForm";
    }

    @PostMapping(path = "/loginForm")
    public String postLogin(Model model) {
        return "/loginForm";
    }

    //ユーザー登録画面
    @GetMapping(path = "/newUser")
    public String newUser() {
        return "newUser";
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
            registerUserService.create(registerUserForm);
            mav.setViewName("loginForm");
        } catch (Exception e) {
            mav.setViewName("newUser");
            mav.addObject("error", "ユーザー名は使用できません。：" + username);
        }
        return mav;
    }
}
