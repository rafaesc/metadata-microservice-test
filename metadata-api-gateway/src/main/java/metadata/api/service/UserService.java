package metadata.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import metadata.api.model.User;

@Service("userService")
public interface UserService {
    public List<User> getUsers();

    public User getUser(Long id);

    public void saveUser(User user) throws Exception;

    public void updateUser(Long id, User user) throws Exception;

    public void deleteUser(Long id) throws Exception;

}