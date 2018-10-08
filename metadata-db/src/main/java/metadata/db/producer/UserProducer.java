package metadata.db.producer;

import metadata.db.model.UserEntity;
import metadata.db.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserProducer {
    private Log logger = LogFactory.getLog(UserProducer.class);

    private UserService userService;

    @Autowired
    public UserProducer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void produceData() {
        findUsers();
        addUsers();
        findUsers();
    }

    private void addUsers() {
        logger.info("-> Adding new user now!");
        userService.saveUser(new UserEntity("Peter", "peter@gmail.com", "peter123"));
        userService.saveUser(new UserEntity("Daniela", "daniela@gmail.com", "daniela123"));
        userService.saveUser(new UserEntity("John", "john@gmail.com", "john123"));
        userService.saveUser(new UserEntity("Jimena", "jimena@gmail.com", "jimena123"));
    }

    private void findUsers() {
        logger.info("Trying to find all users.");
        List<UserEntity> allUsers = userService.findUsers();
        if(allUsers.isEmpty()) {
            logger.info("--No users found--");
        } else {
            for (UserEntity foundUser : allUsers) {
                logger.info(String.format("user with id %d and name %s found :)", foundUser.getId(), foundUser.getName()));
            }
        }
    }
}
