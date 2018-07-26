package com.dawson.aaaccount;

import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dawson.aaaccount.domain")
public class AaaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AaaccountApplication.class, args);
		double money = new Random().nextDouble() * 100;
		System.out.println(money);
		money = (double)Math.round(money * 100) / 100;
		System.out.println(money);
	}
}
