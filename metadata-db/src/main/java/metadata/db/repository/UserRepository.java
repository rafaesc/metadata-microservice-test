package metadata.db.repository;

import metadata.db.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * User: Quinten Date: 29-4-2014 Time: 17:04
 *
 * @author Quinten De Swaef
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findById(Long id);

    UserEntity findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
