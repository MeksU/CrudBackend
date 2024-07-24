package pl.meksu.campaignrud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.meksu.campaignrud.exception.DataNotFoundException;
import pl.meksu.campaignrud.model.User;
import pl.meksu.campaignrud.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    public User createUser(User user) {
        user.setFunds(25000.0);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    public boolean isMailUnique(String mail) {
        return !userRepository.existsByMail(mail);
    }

    public User loginUser(String mail, String password) {
        Optional<User> optionalUser = userRepository.findByMail(mail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}