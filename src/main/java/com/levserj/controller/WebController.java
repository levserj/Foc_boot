package com.levserj.controller;

import com.levserj.domain.User;
import com.levserj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
/*        mav.addObject("userForm", new User());*/
        mav.setViewName("signUp");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView signIn(ModelAndView mav) {
/*        mav.addObject("userForm", new User());*/
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

}
