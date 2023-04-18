package tech.msop.data.controller.storage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tech.msop.core.tool.model.Result;
import tech.msop.core.tool.utils.Func;
import tech.msop.data.entity.storage.BaiduBosStorageEntity;
import tech.msop.data.service.storage.BaiduBosStorageService;
import tech.msop.mybatis.support.Condition;
import tech.msop.mybatis.support.Query;

import javax.validation.Valid;

/**
 * 百度云 BOS 配置控制器
 *
 * @author ruozhuliufeng
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/storage/bos")
public class BaiduBosStorageController {
    private final BaiduBosStorageService storageService;

    /**
     * 首页跳转
     * @param view 页面视图
     * @return 视图
     */
    @GetMapping({"/","/index"})
    public ModelAndView index(ModelAndView view){
        view.setViewName("storage/bos");
        return view;
    }
    /**
     * 详情
     *
     * @param entity 查询条件
     * @return BOS 存储信息
     */
    @GetMapping("/detail")
    public Result<BaiduBosStorageEntity> detail(BaiduBosStorageEntity entity) {
        BaiduBosStorageEntity detail = storageService.getOne(Condition.getQueryWrapper(entity));
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
    public Result<IPage<BaiduBosStorageEntity>> list(BaiduBosStorageEntity entity, Query query) {
        IPage<BaiduBosStorageEntity> pages = storageService.page(Condition.getPage(query), Condition.getQueryWrapper(entity));
        return Result.succeed(pages);
    }

    /**
     * 新增 BOS 存储信息
     *
     * @param entity BOS 存储信息信息
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@Valid @RequestBody BaiduBosStorageEntity entity) {
        storageService.save(entity);
        return Result.succeed();
    }

    /**
     * 修改 BOS 存储信息
     *
     * @param entity BOS 存储信息信息
     * @return Result
     */
    @PostMapping("/update")
    public Result update(@Valid @RequestBody BaiduBosStorageEntity entity) {
        storageService.updateById(entity);
        return Result.succeed();
    }

    /**
     * 新增或修改 BOS 存储信息
     *
     * @param entity BOS 存储信息信息
     * @return Result
     */
    @PostMapping("/submit")
    public Result submit(@Valid @RequestBody BaiduBosStorageEntity entity) {
        storageService.saveOrUpdate(entity);
        return Result.succeed();
    }

    /**
     * 逻辑删除 BOS 存储信息
     *
     * @param ids BOS 存储信息ID列表
     * @return Result
     */
    @PostMapping("/remove")
    public Result remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        storageService.deleteLogic(Func.toLongList(ids));
        return Result.succeed();
    }
}
