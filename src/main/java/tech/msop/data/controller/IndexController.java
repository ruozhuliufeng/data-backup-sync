package tech.msop.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tech.msop.core.log.annotation.AuditLog;

/**
 * 首页控制器
 */
@RestController
@Slf4j
public class IndexController {

    /**
     * 首页
     *
     * @param model model
     * @return ModelAndView
     */
    @GetMapping({"/", "/index"})
    @AuditLog("首页访问")
    public ModelAndView index(ModelAndView model) {
        model.setViewName("index");
        return model;
    }

    /**
     * 主页
     *
     * @param model model
     * @return ModelAndView
     */
    @GetMapping("/main")
    public ModelAndView main(ModelAndView model) {
        model.setViewName("main");
        return model;
    }
}
