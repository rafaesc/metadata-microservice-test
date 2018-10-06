package metadata.db.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;
import metadata.db.model.UserEntity;
import metadata.db.service.UserService;

@RestController
@RequestMapping(value = "/db/v1/users", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserEntity> getUsers() throws Exception {
        log.info("db getUsers");
        // throw new UserNotFoundException("id-");
        List<UserEntity> userList = userService.findUsers();
        return userList;
    }
}