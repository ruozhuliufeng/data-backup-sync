package tech.msop.data.entity.system;

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
import java.util.Date;

/**
 * 数据库信息
 *
 * @author ruozhuliufeng
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_database")
@TableName("tb_database")
public class DatabaseEntity extends BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 数据库地址
     */
    @Column(name = "host")
    private String host;

    /**
     * 端口
     */
    @Column(name = "port")
    private Integer port;
    /**
     * 数据库用户名称
     */
    @Column(name = "username")
    private String username;
    /**
     * 数据库用户密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 数据库标识
     */
    @Column(name = "database_name", unique = true)
    private String databaseName;
    /**
     * 状态 0：禁用 1：启用
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 数据库类型
     * 0: MySQL <br/>
     * 1: Oracle <br/>
     * 2: SqlServer <br/>
     * 3: Postgres <br/>
     */
    @Column(name = "type")
    private Integer type;

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
     * 状态[0:未删除 1：已删除]
     */
    @TableLogic
    @Column(name = "is_deleted")
    private Integer isDeleted;
}
