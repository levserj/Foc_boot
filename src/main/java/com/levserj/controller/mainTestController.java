package com.levserj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Serhii Levchynskyi on 18.04.2016.
 */
@Controller()
public class mainTestController {

    @RequestMapping("/")
    public ModelAndView root() {
        return new ModelAndView("main");
    }

    @RequestMapping("/foc")
    public ModelAndView mainTest(@RequestParam(required = false, defaultValue = "World") String name) {
        return new ModelAndView("mainTest", "name", name);
    }

    @RequestMapping("/signUp")
    public ModelAndView signUp(ModelAndView mav) {
/*        mav.addObject("userForm", new User());*/
        mav.setViewName("signUp");
        return mav;
    }

}
