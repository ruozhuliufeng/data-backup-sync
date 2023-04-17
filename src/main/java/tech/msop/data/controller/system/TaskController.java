package tech.msop.data.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.msop.core.tool.model.Result;
import tech.msop.core.tool.utils.Func;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.service.system.TaskService;
import tech.msop.mybatis.support.Condition;
import tech.msop.mybatis.support.Query;

import javax.validation.Valid;

/**
 * 任务 配置控制器
 *
 * @author ruozhuliufeng
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/system/task")
public class TaskController {
    private final TaskService taskService;

    /**
     * 详情
     *
     * @param entity 查询条件
     * @return 任务 存储信息
     */
    @GetMapping("/detail")
    public Result<TaskEntity> detail(TaskEntity entity) {
        TaskEntity detail = taskService.getOne(Condition.getQueryWrapper(entity));
        return Result.succeed(detail);
    }

    /**
     * 分页列表
     *
     * @param entity 查询条件
     * @param query      分页条件
     * @return 分页数据
     */
    @GetMapping("/list")
    public Result<IPage<TaskEntity>> list(TaskEntity entity, Query query) {
        IPage<TaskEntity> pages = taskService.page(Condition.getPage(query), Condition.getQueryWrapper(entity));
        return Result.succeed(pages);
    }

    /**
     * 新增 任务 存储信息
     *
     * @param entity 任务 存储信息信息
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@Valid @RequestBody TaskEntity entity) {
        taskService.save(entity);
        return Result.succeed();
    }

    /**
     * 修改 任务 存储信息
     *
     * @param entity 任务 存储信息信息
     * @return Result
     */
    @PostMapping("/update")
    public Result update(@Valid @RequestBody TaskEntity entity) {
        taskService.updateById(entity);
        return Result.succeed();
    }

    /**
     * 新增或修改 任务 存储信息
     *
     * @param entity 任务 存储信息信息
     * @return Result
     */
    @PostMapping("/submit")
    public Result submit(@Valid @RequestBody TaskEntity entity) {
        taskService.saveOrUpdate(entity);
        return Result.succeed();
    }

    /**
     * 逻辑删除 任务 存储信息
     *
     * @param ids 任务 存储信息ID列表
     * @return Result
     */
    @PostMapping("/remove")
    public Result remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        taskService.deleteLogic(Func.toLongList(ids));
        return Result.succeed();
    }
}
