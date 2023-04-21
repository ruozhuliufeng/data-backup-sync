package tech.msop.data.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import tech.msop.core.tool.utils.DateUtil;
import tech.msop.mybatis.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 */
@Getter
@Setter
@Entity
@Table(name = "tb_user")
@TableName("tb_user")
public class UserEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 用户密码
     */
    @Column(name = "password")
    private String password;
    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;
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
