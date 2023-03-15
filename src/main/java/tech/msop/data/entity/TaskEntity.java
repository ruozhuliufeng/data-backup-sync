package tech.msop.data.entity;

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
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "taskId")
    private Long taskId;

    /**
     * 任务状态 0：未开始  1：进行中  2：异常终止
     */
    @Column(name = "task_status")
    private Integer taskStatus;

    /**
     * 定时任务表达式
     */
    @Column(name = "task_cron")
    private String taskCron;
}
