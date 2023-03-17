package tech.msop.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 数据库信息
 *
 * @author ruozhuliufeng
 */
@Data
@Entity
@Table(name = "tb_database")
public class DatabaseEntity {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
     * 数据库名称
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
     */
    @Column(name = "type")
    private Integer type;

}
