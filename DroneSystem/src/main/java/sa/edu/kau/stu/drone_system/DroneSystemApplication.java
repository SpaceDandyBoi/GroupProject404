package sa.edu.kau.stu.drone_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import sa.edu.kau.stu.drone_database_service.configuration.SharedConfigurationReference;

@SpringBootApplication
@Import(SharedConfigurationReference.class)
public class DroneSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(DroneSystemApplication.class, args);
	}
}
