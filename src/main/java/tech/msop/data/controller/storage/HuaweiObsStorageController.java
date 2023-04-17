package tech.msop.data.controller.storage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.msop.core.tool.model.Result;
import tech.msop.core.tool.utils.Func;
import tech.msop.data.entity.storage.HuaweiObsStorageEntity;
import tech.msop.data.service.storage.HuaweiObsStorageService;
import tech.msop.mybatis.support.Condition;
import tech.msop.mybatis.support.Query;

import javax.validation.Valid;

/**
 * 华为云 OBS 配置控制器
 *
 * @author ruozhuliufeng
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/storage/obs")
public class HuaweiObsStorageController {
    private final HuaweiObsStorageService storageService;

    /**
     * 详情
     *
     * @param entity 查询条件
     * @return OBS 存储信息
     */
    @GetMapping("/detail")
    public Result<HuaweiObsStorageEntity> detail(HuaweiObsStorageEntity entity) {
        HuaweiObsStorageEntity detail = storageService.getOne(Condition.getQueryWrapper(entity));
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
    public Result<IPage<HuaweiObsStorageEntity>> list(HuaweiObsStorageEntity entity, Query query) {
        IPage<HuaweiObsStorageEntity> pages = storageService.page(Condition.getPage(query), Condition.getQueryWrapper(entity));
        return Result.succeed(pages);
    }

    /**
     * 新增 OBS 存储信息
     *
     * @param entity OBS 存储信息信息
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@Valid @RequestBody HuaweiObsStorageEntity entity) {
        storageService.save(entity);
        return Result.succeed();
    }

    /**
     * 修改 OBS 存储信息
     *
     * @param entity OBS 存储信息信息
     * @return Result
     */
    @PostMapping("/update")
    public Result update(@Valid @RequestBody HuaweiObsStorageEntity entity) {
        storageService.updateById(entity);
        return Result.succeed();
    }

    /**
     * 新增或修改 OBS 存储信息
     *
     * @param entity OBS 存储信息信息
     * @return Result
     */
    @PostMapping("/submit")
    public Result submit(@Valid @RequestBody HuaweiObsStorageEntity entity) {
        storageService.saveOrUpdate(entity);
        return Result.succeed();
    }

    /**
     * 逻辑删除 OBS 存储信息
     *
     * @param ids OBS 存储信息ID列表
     * @return Result
     */
    @PostMapping("/remove")
    public Result remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        storageService.deleteLogic(Func.toLongList(ids));
        return Result.succeed();
    }
}
