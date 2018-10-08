package metadata.db.repository;

import metadata.db.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findById(Long id);

    UserEntity findByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
