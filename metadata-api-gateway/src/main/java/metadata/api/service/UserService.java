package metadata.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import metadata.api.model.User;

@Service("userService")
public interface UserService {
    public List<User> getUsers();

}