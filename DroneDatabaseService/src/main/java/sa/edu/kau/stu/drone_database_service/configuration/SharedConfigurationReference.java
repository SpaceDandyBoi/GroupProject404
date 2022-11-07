package sa.edu.kau.stu.drone_database_service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import sa.edu.kau.stu.drone_base_library.configuration.BaseLibraryConfig;

@Configuration
@EnableMongoRepositories("sa.edu.kau.stu.drone_database_service.repository")
@ComponentScan("sa.edu.kau.stu.drone_database_service")
@Import({ BaseLibraryConfig.class })
public class SharedConfigurationReference {
}
