package com.lcwd.user.service;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.ratingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Autowired
//	private ratingService RatingService;
//	@Test
//	void createRating(){
//		Rating rating=Rating.builder().rating(10).userId("").hotelId("").remark("This is testing").build();
//		Rating savedRating=RatingService.createRating(rating);
//		System.out.println("new rating created");
//	}

}
