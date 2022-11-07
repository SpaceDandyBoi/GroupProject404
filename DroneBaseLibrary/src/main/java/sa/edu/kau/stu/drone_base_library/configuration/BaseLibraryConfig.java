package sa.edu.kau.stu.drone_base_library.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("sa.edu.kau.stu.drone_base_library")
@EntityScan("sa.edu.kau.stu.drone_base_library")
public class BaseLibraryConfig {
}
