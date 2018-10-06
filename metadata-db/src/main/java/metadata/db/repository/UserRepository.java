package metadata.db.repository;

import org.springframework.stereotype.Repository;

import metadata.db.model.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findById(Long id);
}