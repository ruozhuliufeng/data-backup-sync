package tech.msop.data.entity.storage;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 华为云 OBS 配置
 */
@Data
@Entity
@Table(name = "tb_storage_obs")
@TableName("tb_storage_obs")
public class HuaweiObsStorageEntity implements Serializable {
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
    @Column(name = "platform",unique = true)
    private String platform = "";
    /**
     * 基础路径
     */
    @Column(name = "base_path")
    private String basePath = "";
}
