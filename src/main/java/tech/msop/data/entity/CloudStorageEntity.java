package tech.msop.data.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 云存储配置信息
 *
 * @author ruozhuliufeng
 */
@Data
@Entity
@Table(name = "tb_cloud_storage")
public class CloudStorageEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 存储平台标识，唯一
     */
    @Column(name = "platform",unique = true)
    private String platform;
}
