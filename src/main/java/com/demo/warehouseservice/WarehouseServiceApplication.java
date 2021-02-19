package com.demo.warehouseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

@SpringBootApplication
public class WarehouseServiceApplication {

	public static void main(String[] args) throws FileNotFoundException {
		SpringApplication.run(WarehouseServiceApplication.class, args);
//		String fileName = "inventory.json";
//		ClassLoader classLoader = WarehouseServiceApplication.class.getClassLoader();
//
//		File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
//		System.out.println(file.exists());
//		LoadDatabase.processFile(new FileInputStream(file));
	}
}
