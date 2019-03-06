package my.web.app.controller;

import my.web.app.model.entity.User;
import my.web.app.model.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.validation.Valid;

@Controller
public class MainController {

    private static final int WEAK = 1;
    private static final int FEAR = 4;
    private static final int STRONG = 7;


    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(){
        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add(){
        return new ModelAndView("adduser","user",new User());
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "adduser";
        }
        userService.addUser(user);
        return "redirect:users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(){
        return new ModelAndView("users","users",userService.getUsers());
    }

    /**
     * Instead of RedirectView may be used String as return value.
     * For example return "redirect:users"
     * Records return new RedirectView("users"); and return "redirect:users"; are identical
     * When you write "return users" will be returned jsp page at once
     * When you write "return redirect:users" will be redirected to method with RequestMapping "users"
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RedirectView deleteUser(@RequestParam int id){
        userService.deleteUser(id);
        return new RedirectView("users");
    }

    @RequestMapping(value = "/user_details", method = RequestMethod.POST)
    public ModelAndView userDetails(@RequestParam int id){
        User user = userService.getUserById(id);
        return new ModelAndView("userDetails","user",user);
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public RedirectView updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return new RedirectView("users");
    }

    /**
     * Method-example.
     * Request example is http://localhost:8080/mywebapp/get_json_user/qwerty
     * @param login
     * @return
     */
    @RequestMapping(value = "/get_json_user/{login}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getJsonUser(@PathVariable("login") String login){
        User user = new User();
        user.setLogin(login);
        return user;
    }

    /**
     * Method-example.
     * Request example is http://localhost:8080/mywebapp/get_json_user?login=qwerty
     * @param login
     * @return
     */
    @RequestMapping(value = "/get_json_user", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getJsonUser1(@RequestParam("login") String login){
        User user = new User();
        user.setLogin(login);
        return user;
    }

    @RequestMapping(value = "/put_json_user", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> putJsonUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /**
     * Ajax method example
     * @param password
     * @return
     */
    @RequestMapping(value = "/checkStrength", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})
    public @ResponseBody String checkStrength(@RequestParam("password") String password){
        if (password.length() >= WEAK && password.length() < FEAR){
            return "Weak password";
        } else if (password.length() >= FEAR && password.length() < STRONG){
            return "Fear password";
        } else if (password.length() >= STRONG){
            return "Strong password";
        } else return "";
    }

    @Autowired
    @Qualifier(value = "userService")
    private void setUserService(UserService userService){
        this.userService = userService;
    }

}
