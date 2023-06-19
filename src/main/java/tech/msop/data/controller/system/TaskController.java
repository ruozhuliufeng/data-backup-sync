package tech.msop.data.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.msop.core.tool.model.Result;
import tech.msop.core.tool.utils.Func;
import tech.msop.data.constants.DataBackupConstant;
import tech.msop.data.entity.system.Option;
import tech.msop.data.entity.system.TaskEntity;
import tech.msop.data.service.system.TaskService;
import tech.msop.data.utils.MenuTreeUtil;
import tech.msop.data.vo.TaskVO;
import tech.msop.data.wrapper.TaskWrapper;
import tech.msop.mybatis.support.Condition;
import tech.msop.mybatis.support.Query;

import javax.validation.Valid;
import java.util.List;

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
     * 首页跳转
     *
     * @param view 页面视图
     * @return 视图
     */
    @GetMapping({"/", "/index"})
    public ModelAndView index(ModelAndView view) {
        view.setViewName("system/task");
        return view;
    }

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
     * @param query  分页条件
     * @return 分页数据
     */
    @GetMapping("/list")
    public Result<IPage<TaskVO>> list(TaskEntity entity, Query query) {
        IPage<TaskEntity> pages = taskService.page(Condition.getPage(query), Condition.getQueryWrapper(entity));
        return Result.succeed(TaskWrapper.build().pageVO(pages));
    }

    /**
     * 新增 任务 存储信息
     *
     * @param entity 任务 存储信息信息
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@Valid @RequestBody TaskEntity entity) {
        taskService.saveTask(entity);
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
        taskService.updateTask(entity);
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
        // 处理定时任务信息
        taskService.submit(entity);
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
        taskService.removeByIdList(Func.toLongList(ids));
        return Result.succeed();
    }

    /**
     * 获取存储信息的组合下拉选项
     *
     * @return 组合下拉选项
     */
    @GetMapping("/query/storage/option")
    public Result<List<Option>> queryStorageOptionList() {
        List<Option> list = taskService.queryStorageOptionList();
        return Result.succeed(MenuTreeUtil.getTree(list, DataBackupConstant.ID_FLAG));
    }

    /**
     * 启动/关闭 定时任务
     *
     * @param id     任务ID
     * @param status 状态 0：停止 1：开启
     * @return Result
     */
    @GetMapping("/change/status/{id}/{status}")
    public Result changeStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        taskService.changeStatus(id, status);
        return Result.succeed();
    }
}
