package com.lcwd.user.service.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;
    private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelService hotelService;
    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get user from database with the help of userRepository
       User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id does not exist!!" + userId));
       //fetch rating given by the user from rating service
        //http://localhost:8083/ratings/users/a1a526c7-2769-4e8b-9aa1-a79f7f0a5cbc calling rating using this url
       Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{}",ratingsOfUser);
        List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList=ratings.stream().map(rating -> {
           //api call to hotel service to get the hotel
            //http://localhost:8081/users/a1a526c7-2769-4e8b-9aa1-a79f7f0a5cbc
           //ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
           Hotel hotel= hotelService.getHotel(rating.getHotelId());
           //logger.info("response status code: {}",forEntity.getStatusCode());
           //set the hotel to rating
           rating.setHotel(hotel);
           //return the rating
           return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
       return user;
    }
}
