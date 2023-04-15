package tech.msop.data.controller.storage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tech.msop.core.tool.model.Result;
import tech.msop.core.tool.utils.Func;
import tech.msop.data.entity.storage.WebDavStorageEntity;
import tech.msop.data.service.storage.WebDavStorageService;
import tech.msop.mybatis.support.Condition;
import tech.msop.mybatis.support.Query;

import javax.validation.Valid;

/**
 * WebDAV 配置控制器
 *
 * @author ruozhuliufeng
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/webdav/storage")
public class WebDavStorageController {
    private final WebDavStorageService storageService;

    /**
     * 详情
     *
     * @param entity 查询条件
     * @return WebDAV 存储信息
     */
    @GetMapping("/detail")
    public Result<WebDavStorageEntity> detail(WebDavStorageEntity entity) {
        WebDavStorageEntity detail = storageService.getOne(Condition.getQueryWrapper(entity));
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
    public Result<IPage<WebDavStorageEntity>> list(WebDavStorageEntity entity, Query query) {
        IPage<WebDavStorageEntity> pages = storageService.page(Condition.getPage(query), Condition.getQueryWrapper(entity));
        return Result.succeed(pages);
    }

    /**
     * 新增 WebDAV 存储信息
     *
     * @param entity WebDAV 存储信息信息
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@Valid @RequestBody WebDavStorageEntity entity) {
        storageService.save(entity);
        return Result.succeed();
    }

    /**
     * 修改 WebDAV 存储信息
     *
     * @param entity WebDAV 存储信息信息
     * @return Result
     */
    @PostMapping("/update")
    public Result update(@Valid @RequestBody WebDavStorageEntity entity) {
        storageService.updateById(entity);
        return Result.succeed();
    }

    /**
     * 新增或修改 WebDAV 存储信息
     *
     * @param entity WebDAV 存储信息信息
     * @return Result
     */
    @PostMapping("/submit")
    public Result submit(@Valid @RequestBody WebDavStorageEntity entity) {
        storageService.saveOrUpdate(entity);
        return Result.succeed();
    }

    /**
     * 逻辑删除 WebDAV 存储信息
     *
     * @param ids WebDAV 存储信息ID列表
     * @return Result
     */
    @PostMapping("/remove")
    public Result remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
//        storageService.deleteLogic(Func.toLongList(ids));
        return Result.succeed();
    }
}
