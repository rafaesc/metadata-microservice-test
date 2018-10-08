package metadata.db.service;

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
        UserEntity user = this.findById(id);

        if (userModify.getEmail().equals("")) {
            user.setEmail(userModify.getEmail());
        }

        if (userModify.getName().equals("")) {
            user.setName(userModify.getName());
        }

        if (userModify.getPassword().equals("")) {
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
