package tech.msop.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.msop.data.entity.DatabaseEntity;

/**
 * 数据库信息操作
 *
 * @author ruozhuliufeng
 */
@Repository
public interface DatabaseRepository extends JpaRepository<DatabaseEntity, Long> {
}
