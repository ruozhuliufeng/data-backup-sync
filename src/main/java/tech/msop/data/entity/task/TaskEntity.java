package tech.msop.data.entity.task;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 定时任务
 *
 * @author ruozhuliufeng
 */
@Data
@Entity
@Table(name = "tb_task")
public class TaskEntity {
    /**
     * 任务主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long taskId;

    /**
     * 任务状态 0：未开始  1：进行中  2：异常终止
     */
    @Column(name = "status")
    private Integer taskStatus;
    /**
     * 定时任务表达式
     */
    @Column(name = "cron")
    private String taskCron;
    /**
     * 云端存储
     */
    @Column(name = "storage_id")
    private Long storageId;
    /**
     * 任务类型
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
    @Column(name="databaseName")
    private String database_name;
}