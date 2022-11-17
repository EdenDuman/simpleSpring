package simpe.spring.configuration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import simpe.spring.exceptions.ValidationParamException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DBConnectionConfiguration {
    @Value("${simple.spring.database.address}")
    private String databaseAddress;

    @Value("${simple.spring.database.username}")
    private String databaseUsername;

    @Value("${simple.spring.database.password}")
    private String databasePassword;

    private void validateDataBaseParams() throws ValidationParamException {
        if (Strings.isBlank(databaseAddress)) {
            throw new ValidationParamException("simple.spring.database.address");
        }

        if (Strings.isBlank(databaseUsername)) {
            throw new ValidationParamException("simple.spring.database.username");
        }

        if (Strings.isBlank(databasePassword)) {
            throw new ValidationParamException("simple.spring.database.password");
        }
    }

    @Bean
    public Connection getConnection() throws SQLException, ClassNotFoundException, ValidationParamException {
        this.validateDataBaseParams();

        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(databaseAddress, databaseUsername, databasePassword);
    }
}
