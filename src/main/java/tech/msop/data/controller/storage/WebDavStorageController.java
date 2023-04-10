package tech.msop.data.controller.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.msop.core.tool.model.Result;
import tech.msop.data.entity.storage.WebDavStorageEntity;
import tech.msop.data.service.storage.WebDavStorageService;

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

    @PostMapping("/save")
    public Result save(@RequestBody WebDavStorageEntity entity){
        storageService.save(entity);
        return Result.succeed();
    }
}
