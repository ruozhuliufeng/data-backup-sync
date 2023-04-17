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

/**
 * 阿里云 OSS 配置
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_storage_oss")
@TableName("tb_storage_oss")
public class AliOssStorageEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 访问密钥
     */
    @Column(name = "access_key")
    private String accessKey;
    /**
     * 密钥
     */
    @Column(name = "secret_key")
    private String secretKey;
    /**
     * 端点
     */
    @Column(name = "end_point")
    private String endPoint;
    /**
     * 存储桶名称
     */
    @Column(name = "bucket_name")
    private String bucketName;
    /**
     * 访问域名
     */
    @Column(name = "domain")
    private String domain = "";
    /**
     * 启用存储
     */
    @Column(name = "enable_storage")
    private Boolean enableStorage = false;
    /**
     * 存储平台标识
     */
    @Column(name = "platform", unique = true)
    private String platform = "";
    /**
     * 基础路径
     */
    @Column(name = "base_path")
    private String basePath = "";

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
     * 创建时间
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
