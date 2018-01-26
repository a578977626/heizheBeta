package com.heizhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})//如果不加这行就要配置数据源
public class HeizheApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeizheApplication.class, args);
	}
}
