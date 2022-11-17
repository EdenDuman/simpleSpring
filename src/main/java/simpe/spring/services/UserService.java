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

    public Optional<User> getById(Long id) {
        return this.userRepository.findById(id);
    }

    public void save(String fistName, String lastName) {
        this.userRepository.save(new User(fistName, lastName));
    }
}
