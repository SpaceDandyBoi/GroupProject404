package sa.edu.kau.stu.drone_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import sa.edu.kau.stu.drone_base_library.configuration.BaseLibraryConfig;
import sa.edu.kau.stu.drone_database_service.configuration.DatabaseServiceConfig;
import sa.edu.kau.stu.path_service.configuration.PathServiceConfig;

@SpringBootApplication
@Import({ BaseLibraryConfig.class, PathServiceConfig.class, DatabaseServiceConfig.class})
public class DroneSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(DroneSystemApplication.class, args);
	}
}
