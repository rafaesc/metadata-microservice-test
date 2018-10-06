package metadata.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import metadata.api.exceptions.UserNotFoundException;
import metadata.api.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final String HOST_DB = "http://localhost:8081/db/v1/users";

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
        ResponseEntity<List<User>> response = client.exchange(HOST_DB, HttpMethod.GET, null,
                typeRef);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<User> userList = response.getBody();
            return userList;
        } else {
            throw new UserNotFoundException("");
        }
    }
}