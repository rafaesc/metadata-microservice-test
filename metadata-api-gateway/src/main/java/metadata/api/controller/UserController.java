package metadata.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.log4j.Log4j2;
import metadata.api.model.User;
import metadata.api.service.UserService;

@RestController
@RequestMapping(value = "/api/v1", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers() throws Exception {
        log.info("getUsers");

        return userService.getUsers();
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable(value = "id", required = true) Long id) throws Exception {
        return userService.getUser(id);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) throws Exception {
        userService.saveUser(user);
    }

    @PutMapping(value = "/user/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public boolean putUser(@PathVariable(value = "id", required = true) Long id, @RequestBody User user)
            throws Exception {
        userService.updateUser(id, user);
        return true;
    }

    @DeleteMapping(value = "/user/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id", required = true) Long id) throws Exception {
        userService.deleteUser(id);
    }

}