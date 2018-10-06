package metadata.db.service;

import java.util.List;

import org.springframework.stereotype.Service;

import metadata.db.model.UserEntity;

@Service("userService")
public interface UserService {

    public UserEntity findById(Long id);

    public List<UserEntity> findUsers();

    public void saveUser(UserEntity user);

    public void updateUser(Long id, UserEntity user);

    public void deleteUser(Long id);

}