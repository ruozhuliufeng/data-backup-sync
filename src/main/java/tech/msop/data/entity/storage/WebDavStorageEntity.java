package tech.msop.data.entity.storage;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import tech.msop.core.tool.utils.DateUtil;
import tech.msop.mybatis.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_storage_webdav")
@TableName("tb_storage_webdav")
public class WebDavStorageEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 服务器地址，注意以"/"结尾
     */
    @Column(name = "server")
    private String server;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 访问域名
     */
    @Column(name = "domain")
    private String domain;
    /**
     * 启用存储
     */
    @Column(name = "enable_storage")
    private Boolean enableStorage;
    /**
     * 存储平台
     */
    @Column(name = "platform",unique = true)
    private String platform;
    /**
     * 基础路径
     */
    @Column(name = "base_path")
    private String basePath;
    /**
     * 存储路径，上传的文件都会存储在这个路径下面，注意以"/"结尾
     */
    @Column(name = "storage_path")
    private String storagePath;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Long createUser;
    /**
     * 创建部门
     */
    @Column(name = "create_dept")
    private Long createDept;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改人
     */
    @Column(name = "update_user")
    private Long updateUser;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 业务状态 [1:正常]
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 状态[0:未删除 1：已删除]
     */
    @TableLogic
    @Column(name = "is_deleted")
    private Integer isDeleted;
}
