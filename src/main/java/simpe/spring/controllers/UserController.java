package simpe.spring.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @GetMapping("/users/{id}")
    public Optional<User> getUserByID(@PathVariable Long id) {
        return this.userService.getById(id);
    }

    @PostMapping(value = "/users")
    public void save(@RequestBody UserDto userDto) {
        this.userService.save(userDto.firstName, userDto.lastName);
    }

    /**
     * DTO is Data Transfer Object
     * DTO in wiki https://en.wikipedia.org/wiki/Data_transfer_object
     * <p>
     * I used this class because I don't want get user id (It is identity)
     */
    public static class UserDto {
        @JsonProperty
        private String firstName;
        @JsonProperty
        private String lastName;
    }
}
