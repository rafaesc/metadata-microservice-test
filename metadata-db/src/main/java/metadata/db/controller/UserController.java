package metadata.db.controller;

import java.util.List;

import metadata.db.model.UserEntity;
import metadata.db.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/db/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<UserEntity> getUsers() throws Exception {
        List<UserEntity> userList = userService.findUsers();
        return userList;
    }

    @GetMapping(value = "/users/{id}")
    public UserEntity getUser(@PathVariable(value = "id", required = true) Long id) throws Exception {
        return userService.findById(id);
    }

    @PostMapping(value = "/user")
    public boolean saveUser(@RequestBody UserEntity user) throws Exception {
        userService.saveUser(user);
        return true;
    }

    @PutMapping(value = "/user/{id}")
    public boolean putUser(@PathVariable(value = "id", required = true) Long id, @RequestBody UserEntity user)
            throws Exception {
        userService.updateUser(id, user);
        return true;
    }

    @DeleteMapping(value = "/user/{id}")
    public boolean deleteUser(@PathVariable(value = "id", required = true) Long id) throws Exception {
        userService.deleteUser(id);

        return true;
    }
}