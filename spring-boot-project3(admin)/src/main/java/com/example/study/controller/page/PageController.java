package com.example.study.controller.page;

import com.example.study.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {

    @Autowired
    private AdminMenuService adminMenuService;

    @RequestMapping(path = {""},method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/pages/main");
        mv.addObject("menuList", adminMenuService.getAdminMenu());
        mv.addObject("code", "main");
//        return new ModelAndView("/pages/main")
//                .addObject("menuList", adminMenuService.getAdminMenu())
//                .addObject("code", "main")
//                ;
        return mv;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        return new ModelAndView("/pages/user")
                .addObject("menuList", adminMenuService.getAdminMenu())
                .addObject("code", "user")
                ;
    }
}
