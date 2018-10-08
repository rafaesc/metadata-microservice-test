package metadata.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import metadata.api.exceptions.UserNotFoundException;
import metadata.api.model.User;
import lombok.extern.log4j.Log4j2;

@Service("userService")
@Log4j2
public class UserServiceImpl implements UserService {

    private static final String HOST_DB = "http://localhost:8081/db/v1";

    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public UserServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<User> getUsers() {
        RestTemplate client = restTemplateBuilder.build();
        ParameterizedTypeReference<List<User>> typeRef = new ParameterizedTypeReference<List<User>>() {
        };

        String url = HOST_DB.concat("/users");
        log.info(url);
        ResponseEntity<List<User>> response = client.exchange(url, HttpMethod.GET, null, typeRef);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<User> userList = response.getBody();
            return userList;
        } else {
            throw new UserNotFoundException("");
        }
    }

    @Override
    public User getUser(Long id) {
        RestTemplate client = restTemplateBuilder.build();

        ParameterizedTypeReference<User> typeRef = new ParameterizedTypeReference<User>() {
        };

        String url = HOST_DB.concat("/users/" + id.toString());
        log.info(url);
        ResponseEntity<User> response = client.exchange(url, HttpMethod.GET, null, typeRef);

        if (response.getStatusCode().is2xxSuccessful()) {
            User user = response.getBody();
            return user;
        } else {
            throw new UserNotFoundException("");
        }
    }

    @Override
    public void saveUser(User user) throws Exception {
        RestTemplate client = restTemplateBuilder.build();

        ParameterizedTypeReference<Boolean> typeRef = new ParameterizedTypeReference<Boolean>() {
        };

        String url = HOST_DB.concat("/user");
        log.info(url);
        HttpEntity<User> entity = new HttpEntity<User>(user, new HttpHeaders());

        ResponseEntity<Boolean> response = client.exchange(url, HttpMethod.POST, entity, typeRef);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error");
        }
    }

    @Override
    public void updateUser(Long id, User user) throws Exception {
        RestTemplate client = restTemplateBuilder.build();

        ParameterizedTypeReference<Boolean> typeRef = new ParameterizedTypeReference<Boolean>() {
        };

        String url = HOST_DB.concat("/user/" + id.toString());
        log.info(url);
        HttpEntity<User> entity = new HttpEntity<User>(user, new HttpHeaders());

        ResponseEntity<Boolean> response = client.exchange(url, HttpMethod.PUT, entity, typeRef);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error");
        }
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        RestTemplate client = restTemplateBuilder.build();

        ParameterizedTypeReference<Boolean> typeRef = new ParameterizedTypeReference<Boolean>() {
        };

        String url = HOST_DB.concat("/user/" + id.toString());
        log.info(url);
        HttpEntity<User> entity = new HttpEntity<User>(null, new HttpHeaders());

        ResponseEntity<Boolean> response = client.exchange(url, HttpMethod.DELETE, entity, typeRef);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error");
        }
    }
}