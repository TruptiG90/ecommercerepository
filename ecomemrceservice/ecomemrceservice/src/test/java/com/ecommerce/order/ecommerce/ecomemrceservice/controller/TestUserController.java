package com.ecommerce.order.ecommerce.ecomemrceservice.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ecommerce.order.ecommerce.ecomemrceservice.entity.User;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class TestUserController {
	
	   @Autowired
	    private MockMvc mvc;
	   
	   @MockBean
	   private UserService userService;
	   
	      
	   private  static User user;
	
	   @BeforeAll
	   public static void setup() {
		   user = new User(); 
		   user.seteMail("mail@mail.com");
			  user.setFirstName("fName"); user.setLastName("lName");
			  user.setPassword("password"); user.setPhoneNo(123456789);
			  user.setUserName("fl");
			  user.setUserId(1L);
		   
	   }
		
		  @Test public void testUserCreateMethod() throws Exception {
		  
		 
				  
		  Mockito.when(userService.createUserRecord(user)).
		  thenReturn(new String("You have successfully registered yourself"));

		    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/application/user")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(asJsonString(user).getBytes(StandardCharsets.UTF_8))
		            .accept(MediaType.APPLICATION_JSON)).
		    		andExpect(MockMvcResultMatchers.status().isOk())
		            .andReturn();
		 
		    Assertions.assertThat(result).isNotNull();
		  
	   }
		 
		  public static String asJsonString(final Object obj) {
		        try {
		            return new ObjectMapper().writeValueAsString(obj);
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
		    }
		  
			
			  @Test public void testLoginMethod() throws Exception {
			  
			 			  
			  when(userService.loginToApplication(user.getUserName(),user.getPassword())).
			  thenReturn(true);
			  
			  
			  MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/application/login")
			            .contentType(MediaType.APPLICATION_JSON)
			            .content(asJsonString(user).getBytes(StandardCharsets.UTF_8))
			            .accept(MediaType.APPLICATION_JSON))
			    		.andExpect(MockMvcResultMatchers.status().isBadRequest())
			    		.andExpect(content().string("Incorrect Username/Password Provided"))
			            .andReturn();
			  
			  
			  }
			  
				/*
				 * @Test public void testGetUserById() throws Exception {
				 * when(userService.getUserRecord(1L)).thenReturn(user); Long userId =
				 * user.getUserId(); MvcResult result =
				 * mvc.perform(MockMvcRequestBuilders.get("/application/getUser/1L")
				 * .contentType(MediaType.APPLICATION_JSON)
				 * .content(asJsonString(user).getBytes(StandardCharsets.UTF_8))
				 * .accept(MediaType.APPLICATION_JSON))
				 * .andExpect(MockMvcResultMatchers.status().isOk()) .andReturn();
				 * System.out.print("------------------"+result.getResponse().getContentAsString
				 * ());
				 * 
				 * }
				 */
			 
}
