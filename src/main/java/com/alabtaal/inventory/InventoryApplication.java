package com.alabtaal.inventory;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		Application.launch(JavaFXApplication.class, args);
//		SpringApplication.run(InventoryApplication.class, args);
	}

}
