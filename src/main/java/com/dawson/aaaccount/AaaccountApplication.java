package com.dawson.aaaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.avos.avoscloud.AVOSCloud;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@MapperScan("com.dawson.aaaccount.domain")
public class AaaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AaaccountApplication.class, args);
		AVOSCloud.initialize("mcoshkm3fro2kef3j4wtkxyid7k6o7zga85g4wjj0c4fc1tw","fh1oohucqgtrv4572ldsswyn9y7udnuw59el6it3xqxjdlpp","kh5s7v43g6i7ivls88q5u86lm92xfrsom47l99t7ifphapcd");
		AVOSCloud.setDebugLogEnabled(true);

		//		test();
	}
	
	private static void test() {
		System.out.println("hello jd");
		
		ObjectMapper mapp =new ObjectMapper();		
 		Map<String ,Object> maps=new HashMap<>();
		
		maps.put("name", "jd");
		List<String> ros=new ArrayList<>();
		ros.add("admin");
		ros.add("stu");
		maps.put("ros",ros);
		
 
	}
}
