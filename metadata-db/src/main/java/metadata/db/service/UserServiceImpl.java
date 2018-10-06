package metadata.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import metadata.db.model.UserEntity;
import metadata.db.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> findUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

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
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = this.findById(id);
        userRepository.delete(user);
    }
}