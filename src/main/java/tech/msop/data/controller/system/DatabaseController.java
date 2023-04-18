package tech.msop.data.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.msop.core.tool.model.Result;
import tech.msop.core.tool.utils.Func;
import tech.msop.data.entity.system.DatabaseEntity;
import tech.msop.data.service.system.DatabaseService;
import tech.msop.mybatis.support.Condition;
import tech.msop.mybatis.support.Query;

import javax.validation.Valid;

/**
 * AliOSS 配置控制器
 *
 * @author ruozhuliufeng
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/system/database")
public class DatabaseController {
    private final DatabaseService databaseService;

    /**
     * 首页跳转
     * @param view 页面视图
     * @return 视图
     */
    @GetMapping({"/","/index"})
    public ModelAndView index(ModelAndView view){
        view.setViewName("system/database");
        return view;
    }
    /**
     * 详情
     *
     * @param entity 查询条件
     * @return 数据库 存储信息
     */
    @GetMapping("/detail")
    public Result<DatabaseEntity> detail(DatabaseEntity entity) {
        DatabaseEntity detail = databaseService.getOne(Condition.getQueryWrapper(entity));
        return Result.succeed(detail);
    }

    /**
     * 分页列表
     *
     * @param entity 查询条件
     * @param query  分页条件
     * @return 分页数据
     */
    @GetMapping("/list")
    public Result<IPage<DatabaseEntity>> list(DatabaseEntity entity, Query query) {
        IPage<DatabaseEntity> pages = databaseService.page(Condition.getPage(query), Condition.getQueryWrapper(entity));
        return Result.succeed(pages);
    }

    /**
     * 新增 数据库 存储信息
     *
     * @param entity 数据库 存储信息信息
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@Valid @RequestBody DatabaseEntity entity) {
        databaseService.save(entity);
        return Result.succeed();
    }

    /**
     * 修改 数据库 存储信息
     *
     * @param entity 数据库存储信息信息
     * @return Result
     */
    @PostMapping("/update")
    public Result update(@Valid @RequestBody DatabaseEntity entity) {
        databaseService.updateById(entity);
        return Result.succeed();
    }

    /**
     * 新增或修改 数据库 存储信息
     *
     * @param entity 数据库 存储信息信息
     * @return Result
     */
    @PostMapping("/submit")
    public Result submit(@Valid @RequestBody DatabaseEntity entity) {
        databaseService.saveOrUpdate(entity);
        return Result.succeed();
    }

    /**
     * 逻辑删除 数据库 存储信息
     *
     * @param ids 数据库 存储信息ID列表
     * @return Result
     */
    @PostMapping("/remove")
    public Result remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        databaseService.deleteLogic(Func.toLongList(ids));
        return Result.succeed();
    }
}
