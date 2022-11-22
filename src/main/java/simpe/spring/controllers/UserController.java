package simpe.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simpe.spring.models.User;
import simpe.spring.services.UserService;

import java.util.List;
import java.util.Optional;

/**
 * There is no option use @RequestMapping
 * Because we have @GetMapping and @PostMapping in same class
 * even if we try to do something like this @RequestMapping(value = "/users", method= {RequestMethod.GET, RequestMethod.POST})
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return this.userService.getAll();
    }

    @GetMapping("/users/{firstName}")
    public Optional<User> getUserByFirstName(@PathVariable String firstName) {
        return this.userService.getUserByFirstName(firstName);
    }

    @PostMapping(value = "/users")
    public void save(@RequestBody User user) {
        this.userService.save(user);
    }
}
