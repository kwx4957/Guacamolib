package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner run(TopicRepository topicRepository) throws Exception{
//		return (ApplicationArguments args) ->{
//			List<Topic> topics = Arrays.asList(
//					Topic.builder().title("엉덩이는 2개인가 1개인가").content("내용").firstOption("엉1").secondOption("엉2").password("password").build(),
//					Topic.builder().title("여름인가 겨울인가").content("내용").firstOption("여름").secondOption("겨울").password("password").build(),
//					Topic.builder().title("밤 vs 낮").content("내용").firstOption("밤").secondOption("낮").password("password").build(),
//					Topic.builder().title("치킨 vs 피자").content("내용").firstOption("치킨").secondOption("피자").password("password").build()
//			);
//			topicRepository.saveAll(topics);
//		};
//	}
}
