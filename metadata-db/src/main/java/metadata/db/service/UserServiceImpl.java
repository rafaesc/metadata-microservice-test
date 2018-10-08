package metadata.db.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import metadata.db.model.UserEntity;
import metadata.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserEntity userModify) {
        logger.info(id);
        UserEntity user = this.findById(id);

        String email = userModify.getEmail();
        String name = userModify.getName();
        String password = userModify.getPassword();

        if (email != null && !email.isEmpty()) {
            user.setEmail(userModify.getEmail());
        }

        if (name != null && !name.isEmpty()) {
            user.setName(userModify.getName());
        }

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = this.findById(id);
        userRepository.delete(user);
    }
}
