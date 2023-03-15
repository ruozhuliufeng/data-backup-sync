package tech.msop.data.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 审计日志
 */
@Entity
@Data
@Table(name = "tb_audit_log")
public class AuditLogEntity {
    /**
     * 日志主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
