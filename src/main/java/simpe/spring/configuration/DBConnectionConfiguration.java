package simpe.spring.configuration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import simpe.spring.exceptions.ValidationParamException;

@Configuration
public class DBConnectionConfiguration {
    @Value("${simple.spring.database.address}")
    private String databaseAddress;

    @Value("${simple.spring.database.port}")
    private String databasePort;

    @Value("${simple.spring.database.database}")
    private String databaseDatabase;

    private void validateDataBaseParams() throws ValidationParamException {
        if (Strings.isBlank(databaseAddress)) {
            throw new ValidationParamException("simple.spring.database.address");
        }

        if (Strings.isBlank(databasePort)) {
            throw new ValidationParamException("simple.spring.database.port");
        }

        if (Strings.isBlank(databaseDatabase)) {
            throw new ValidationParamException("simple.spring.database.database");
        }
    }

    private String getConnectionString() throws ValidationParamException {
        this.validateDataBaseParams();

        return String.format("mongodb://%s:%s/%s", this.databaseAddress, this.databasePort, this.databaseDatabase);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws ValidationParamException {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(getConnectionString()));
    }
}
