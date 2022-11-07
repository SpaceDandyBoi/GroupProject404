package sa.edu.kau.stu.path_service.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import sa.edu.kau.stu.drone_base_library.configuration.BaseLibraryConfig;

@Configuration
@ComponentScan("sa.edu.kau.stu.path_service")
@Import({ BaseLibraryConfig.class })
public class PathServiceConfig {
}
