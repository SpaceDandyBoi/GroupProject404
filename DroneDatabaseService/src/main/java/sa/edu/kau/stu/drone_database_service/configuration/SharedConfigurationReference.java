package sa.edu.kau.stu.drone_database_service.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan("sa.edu.kau.stu.drone_database_service")
@EnableMongoRepositories("sa.edu.kau.stu.drone_database_service.repository")
@EntityScan("sa.edu.kau.stu.drone_database_service")
public class SharedConfigurationReference {
}
