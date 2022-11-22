package simpe.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import simpe.spring.models.User;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> findAll() {
        return this.mongoTemplate.findAll(User.class);
    }

    public Optional<User> findByFirstName(String firstName) {
        Query query = Query.query(Criteria.where("firstName").is(firstName));
        User user = this.mongoTemplate.findOne(query, User.class);

        return Optional.ofNullable(user);
    }

    public void save(User user) {
        this.mongoTemplate.insert(user);
    }

}
