package com.ecommerce.order.ecommerce.ecomemrceservice.microservices;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.DateRangeDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.OrderDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.ProductDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.WalletDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.OrderCart;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Product;

@FeignClient(url = "http://localhost:8710/order", value="order-service")
public interface OrderInterface {

	  @RequestMapping(method = RequestMethod.POST,value="/product")
		public ResponseEntity<String> createProduct(@RequestBody  ProductDTO produtcDto );
	
	 
	 @RequestMapping(method = RequestMethod.GET,value="/productsbyname")
	public List<Product> getProductDetailsByProductName(@RequestParam String productName  ) ;
	
	  @RequestMapping(method = RequestMethod.GET,value="/productsbytype")
		public List<Product> getProductDetailsByProductType( @RequestParam String productType  );
	 
	  @RequestMapping(method = RequestMethod.POST,value="/addtocart")
		public ResponseEntity<String> addItemsToCart( @RequestBody List<OrderDTO> orderDTO  ) throws Exception ;
	   
	  @RequestMapping(method = RequestMethod.POST,value="/findbymonth")
		public ResponseEntity<Set<OrderCart>> findOrdersByMonths( @RequestBody DateRangeDTO dateRangeDTO  ) throws Exception;
	   
	  @RequestMapping(method = RequestMethod.POST,value="/addmoneytowallet")
		public ResponseEntity<EcommerceUser> moneyTransferToWallet( @RequestBody WalletDTO walletDTO  ) throws Exception ;
	  
}
