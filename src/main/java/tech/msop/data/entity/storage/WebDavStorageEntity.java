package tech.msop.data.entity.storage;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_storage_webdav")
public class WebDavStorageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    /**
     * 服务器地址，注意以"/"结尾
     */
    @Column(name = "server")
    private String server;
    /**
     * 用户名
     */
    @Column(name = "user")
    private String user;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 访问域名
     */
    @Column(name = "domain")
    private String domain = "";
    /**
     * 启用存储
     */
    @Column(name = "enable_storage")
    private Boolean enableStorage;
    /**
     * 存储平台
     */
    @Column(name = "platform",unique = true)
    private String platform = "";
    /**
     * 基础路径
     */
    @Column(name = "base_path")
    private String basePath = "";
    /**
     * 存储路径，上传的文件都会存储在这个路径下面，注意以"/"结尾
     */
    @Column(name = "storage_path")
    private String storagePath = "/";
}
