package simpe.spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Document("users")
 * "users" it's my collection
 */
@Document("users")
public class User {
    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;


    /**
     * to use MongoTemplate we have to create empty constructor
     * it's used to init the Objet(User) in reflection
     */
    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
