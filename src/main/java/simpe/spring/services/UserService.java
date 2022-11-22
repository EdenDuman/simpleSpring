package simpe.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simpe.spring.models.User;
import simpe.spring.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserByFirstName(String firstName) {
        return this.userRepository.findByFirstName(firstName);
    }

    public void save(User user) {
        this.userRepository.save(user);
    }
}
