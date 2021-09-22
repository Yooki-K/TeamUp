package com.teamup.demo.tool;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class GlobalExceptionHandler {

    @PostMapping("/errorPage")
    public ModelAndView defaultErrorHandler(Error error){
        ModelAndView mav = new ModelAndView();
        System.out.println(error.toString());
        mav.addObject("mes", String.format("%s<p class=\"page-mk\">%s</p>",error.getStatus(),error.getMes()));
        mav.addObject("from", error.getFrom());
        mav.setViewName("error");
        return mav;
    }

}

