package tech.msop.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.service.DatabaseService;
import tech.msop.data.vo.DatabaseQueryVO;

/**
 * 数据库信息处理
 *
 * @author ruozhuliufeng
 */
@Controller
@RequestMapping("/database")
@RequiredArgsConstructor
public class DatabaseController {
    private final DatabaseService databaseService;
    /**
     * 数据库信息页面
     *
     * @param model 模型
     * @return ModelAndView
     */
    @GetMapping({"/", "/index"})
    public ModelAndView getIndex(ModelAndView model) {

        model.setViewName("database_pages");
        return model;
    }

    /**
     * 数据库信息列表
     *
     * @param query 查询条件
     * @param model 模型
     * @return ModelAndView
     */
    @GetMapping("/query/list")
    public ModelAndView queryList(DatabaseQueryVO query, ModelAndView model) {
        return model;
    }

    /**
     * 保存数据
     *
     * @param entity 数据库信息
     * @return 模型
     */
    @PostMapping("/save")
    public ModelAndView save(DatabaseEntity entity) {
        databaseService.saveDatabase(entity);
        ModelAndView model = new ModelAndView();
        model.setViewName("database_pages");
        return model;
    }

    /**
     * 查询数据库信息
     *
     * @param id    数据库ID
     * @param model 模型
     * @return 模型
     */
    @GetMapping("/query/{id}")
    public ModelAndView query(@PathVariable("id") Integer id, ModelAndView model) {

        return model;
    }

    /**
     * 更新数据库信息
     *
     * @param entity 数据库信息
     * @param model  模型
     * @return 模型
     */
    @PutMapping("/update")
    public ModelAndView update(@RequestBody DatabaseEntity entity, ModelAndView model) {
        return model;
    }

    /**
     * 批量删除数据库信息
     *
     * @param query 数据库ID列表
     * @param model 模型
     * @return 模型
     */
    @DeleteMapping("/delete")
    public ModelAndView delete(DatabaseQueryVO query, ModelAndView model) {
        return model;
    }
}
