package metadata.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;
import metadata.api.model.User;
import metadata.api.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users", produces = { MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getUsers() throws Exception {
        log.info("getUsers");
        
        // throw new UserNotFoundException("id-");
        return userService.getUsers();
    }
}