package com.ecommerce.order.ecommerce.ecomemrceservice.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.DateRangeDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.LinkAccountDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.OrderDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.ProductDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.WalletDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.OrderCart;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Product;
import com.ecommerce.order.ecommerce.ecomemrceservice.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	 @Autowired
	 OrderService orderService;

	
	 @PostMapping("/product")
		public ResponseEntity<String> createProduct(@RequestBody  ProductDTO produtcDto ) {
			 return orderService.createProduct(produtcDto);
		}
	 
	 @GetMapping("/productsbyname")
	public List<Product> getProductDetailsByProductName(@RequestParam String productName  ) {
			 return orderService.getProductByName(productName);
	}
	 @GetMapping("/productsbytype")
		public List<Product> getProductDetailsByProductType( @RequestParam String productType  ) {
	    return orderService.getProductByType(productType);
		}
	 @PostMapping("/addtocart")
		public ResponseEntity<String> addItemsToCart( @RequestBody List<OrderDTO> orderDTO  ) throws Exception {
	    return orderService.createOrder(orderDTO);
		}
	 @PostMapping("/findbymonth")
		public ResponseEntity<Set<OrderCart>> findOrdersByMonths( @RequestBody DateRangeDTO dateRangeDTO  ) throws Exception {
	    return orderService.findOrdersByDateRange(dateRangeDTO);
		}
	 
	 @PostMapping("/linkbankaccount")
		public ResponseEntity<EcommerceUser> linkBankAccountToEcommApplication( @RequestBody LinkAccountDTO linkAccountDTO  ) throws Exception {
	    return orderService.addMoneyToWallet(linkAccountDTO);
		}
	 
	 @PostMapping("/addmoneytowallet")
		public ResponseEntity<EcommerceUser> moneyTransferToWallet( @RequestBody WalletDTO walletDTO  ) throws Exception {
	    return orderService.addMoneyToWallet(walletDTO);
		}
}
