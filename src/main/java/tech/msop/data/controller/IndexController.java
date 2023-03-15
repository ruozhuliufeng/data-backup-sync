package tech.msop.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 首页控制器
 */
@RestController
@Slf4j
public class IndexController {

    /**
     * 首页
     * @param model model
     * @return ModelAndView
     */
    @GetMapping({"/","/index"})
    public ModelAndView index(ModelAndView model){
        model.setViewName("index");
        return model;
    }
}
