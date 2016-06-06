package com.levserj.controller;

import com.levserj.domain.User;
import com.levserj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Serhii Levchynskyi on 18.04.2016.
 */
@Controller()
public class WebController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ModelAndView root() {
        return new ModelAndView("main");
    }

    @RequestMapping("/foc")
    public ModelAndView foc() {
        return new ModelAndView("main");
    }

    @RequestMapping("/signUp")
    public ModelAndView signUp(ModelAndView mav) {

        mav.setViewName("signUp");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView signIn(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            mav.addObject("logout", "You've been successfully logged out.");
        }
        mav.setViewName("signIn");
        return mav;
    }

    @RequestMapping("/myPage")
    public ModelAndView myPage(ModelAndView mav, Principal principal) {
        User currentUser = userService.readUserByEmail(principal.getName());
        mav.addObject("userId", currentUser.getId());
        mav.setViewName("myPage");
        return mav;
    }
/*    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addObject("logout", "true");
        model.setViewName("signIn");
        return model;
    }*/

/*    @RequestMapping("/createNewUser")
    public ModelAndView createNewUser(@ModelAttribute @Valid User newUser,
                                      BindingResult result,
                                      ModelAndView mav) {
        if (result.hasErrors()){
            mav.addObject("message", "Couldn't make an object user(binding error)");
            mav.setViewName("signUp");
        }
        User user = userService.createUser(newUser);
        if (user !=null){
            mav.addObject("message", "User " + user.getEmail() + " created");
            mav.setViewName("main");
        } else {
            mav.addObject("message", "User " + user.getEmail() + " was NOT created");
            mav.setViewName("main");
        }
        return mav;
    }*/

}
