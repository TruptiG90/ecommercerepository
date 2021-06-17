package com.ecommerce.order.ecommerce.ecomemrceservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.DateRangeDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.OrderDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.ProductDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.CartItem;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.OrderCart;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Product;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EcommerceTestController {
	
	   @Autowired
	    private MockMvc mvc;
	   
	   @MockBean
	   private OrderService orderService;
	   
	      
	   private  static Product product;
		/*
		 * private static CartItem cartItem; private static OrderCart orderCart;
		 */
	   
	   private  static ProductDTO productDto;
		
	   private static List<Product> productList ;
	   
	   private static  Set<OrderCart> orderSet;
		  private static List<OrderDTO> orderDTO; 
		  private static OrderDTO orderrDTO;
		  
			
		 private Date startDate;
		 private Date endDate;
	   
		  private static CartItem cartItem; 
		  private static OrderCart orderCart;
		 
		  static String start = "01/02/2012 00:00:00";
		  static String end = "02/03/2015 00:00:00";
		  
		  static DateFormat formatter ; 
		  
		  static DateRangeDTO dateRangeDTO;

		
	
	   @BeforeAll
	   public static void setup() throws ParseException {
				 
		     product = new Product(); 
			 product.setProductName("Parle");
			 product.setProductPrice(100);
			 product.setProductType("Biscuit");
			 product.setProductId(1L);
			 
			 productList = Arrays.asList(product); 
			 
		 productDto = new ProductDTO(); 
		 productDto.setProductName("Parle");
		 productDto.setProductPrice(100);
		 productDto.setProductType("Biscuit");
		 
		 orderDTO = new ArrayList();
		 orderrDTO = new OrderDTO();
		 orderrDTO.setProductName(product.getProductName());
		 orderrDTO.setProductQuantity(100);
		 orderDTO.add(orderrDTO);
		 
		 orderSet = new HashSet();
		  formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  Date  startDate = (Date)formatter.parse(start); 
		  Date  endDate = (Date)formatter.parse(end);
			 
		  dateRangeDTO = new DateRangeDTO();
		  dateRangeDTO.setStartDate(startDate);
		  dateRangeDTO.setEndDate(endDate);
		  
		   }
		
		  @Test 
		  public void testUserCreateMethod() throws Exception {
			  
		  Mockito.when(orderService.createProduct(productDto))
		  .thenReturn(new ResponseEntity ("Product Saved successfully",HttpStatus.OK));

		    MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/order/product")
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(asJsonString(product).getBytes(StandardCharsets.UTF_8))
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
		 
		
		
		  
		  @Test public void testGetProductByName() throws Exception {
		  
		  
		  when(orderService.getProductByName(product.getProductName())).
		  thenReturn(productList);
		  
		  
		  MvcResult result =
		  mvc.perform(MockMvcRequestBuilders.get("/order/productsbyname")
		  .contentType(MediaType.APPLICATION_JSON)
		  .content(product.getProductName().getBytes(StandardCharsets.
		  UTF_8)) .accept(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().isBadRequest())
		 // .andExpect(content().contentType(productList)).andReturn();
		  .andReturn();

		  
		  }
		  
		  @Test public void testGetProductByType() throws Exception {
		  
		  
		  when(orderService.getProductByName(product.getProductType())).
		  thenReturn(productList);
		  
		  
		  MvcResult result =
		  mvc.perform(MockMvcRequestBuilders.get("/order/productsbytype")
		  .contentType(MediaType.APPLICATION_JSON)
		.content(product.getProductName().getBytes(StandardCharsets.
		  UTF_8)) .accept(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().isBadRequest())
		  .andReturn();


		//  .andExpect(content().equals(productList)).andReturn();
		  
		  
		  }
		  
			
			  @Test public void testAddToCart() throws Exception {
			  when(orderService.createOrder(orderDTO)).thenReturn(new
			  ResponseEntity("The order created by you, order total price is "+100,
			  HttpStatus.OK));
			  
			  MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/order/addtocart")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(asJsonString(orderDTO).getBytes(StandardCharsets.UTF_8))
			  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(MockMvcResultMatchers.status().isOk()) .andReturn();
			  System.out.print("------------------"+result.getResponse().getContentAsString
			  ());
			  
			  }
			 
		  
		  @Test public void findOrdersByMonths() throws Exception {
		  when(orderService.findOrdersByDateRange(dateRangeDTO)).thenReturn(new
		  ResponseEntity(orderSet,
		  HttpStatus.OK)); 
		  MvcResult result =
		  mvc.perform(MockMvcRequestBuilders.post("/order/findbymonth")
		  .contentType(MediaType.APPLICATION_JSON)
		  .content(asJsonString(dateRangeDTO).getBytes(StandardCharsets.UTF_8))
		  .accept(MediaType.APPLICATION_JSON))
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andReturn();
		  System.out.print("------------------"+result.getResponse().getContentAsString
		  ());
		  
		  }
		  
		  private static Date getcurrentDate() { SimpleDateFormat formatter = new
		  SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); Date date = new Date();
		  System.out.println(formatter.format(date)); return date; }
		 
			  
}