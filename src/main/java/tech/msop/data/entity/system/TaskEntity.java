package tech.msop.data.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import tech.msop.core.tool.utils.DateUtil;
import tech.msop.mybatis.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 定时任务
 *
 * @author ruozhuliufeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_task")
@TableName("tb_task")
public class TaskEntity extends BaseEntity {
    /**
     * 任务主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 任务名称
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 任务状态 0：未开始  1：进行中  2：异常终止
     */
    @Column(name = "task_status")
    private Integer taskStatus;
    /**
     * 定时任务表达式
     */
    @Column(name = "cron")
    private String taskCron;
    /**
     * 云端存储类别<br/>
     * 0：阿里云 OSS<br/>
     * 1：百度云 BOS<br/>
     * 2：华为云 OBS<br/>
     * 3：七牛云 KODO<br/>
     * 4：腾讯云 COS<br/>
     * 5：WEBDAV<br/>
     */
    @Column(name = "storage_type")
    private Integer storageType;
    /**
     * 云端存储
     */
    @Column(name = "storage_id")
    private Long storageId;
    /**
     * 任务类型<br/>
     * 0：指定文件同步<br/>
     * 1：指定文件夹同步<br/>
     * 2：数据库备份并同步<br/>
     */
    @Column(name = "task_type")
    private Integer taskType;
    /**
     * 同步文件夹,以 / 开头
     *
     * 取值 /data/backup + syncPath
     */
    @Column(name = "sync_path")
    private String syncPath;
    /**
     * 数据库ID
     */
    @Column(name = "database_id")
    private Long databaseId;
    /**
     * 需要同步的数据库表
     * 1: 指定数据库,如 sys_user、sys_role 等
     * 2: 全部数据库,如 *
     */
    @Column(name="database")
    private String database;


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
