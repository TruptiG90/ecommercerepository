package com.ecommerce.order.ecommerce.ecomemrceservice.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Date;
import javax.xml.bind.JAXBException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.xml.sax.SAXException;

import com.ecommerce.order.ecommerce.ecomemrceservice.dto.DateRangeDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.LinkAccountDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.OrderDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.OrderStatus;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.ProductDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.dto.WalletDTO;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Account;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.CartItem;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.EcommerceUser;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.OrderCart;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Product;
import com.ecommerce.order.ecommerce.ecomemrceservice.entity.Wallet;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.IncorrectAccountException;
import com.ecommerce.order.ecommerce.ecomemrceservice.exception.UserNotFoundException;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.AccountRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.CartItemRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.EcommerceUserRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.OrderCartRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.ProductRepository;
import com.ecommerce.order.ecommerce.ecomemrceservice.repository.WalletRepository;
import com.sun.xml.bind.v2.runtime.reflect.ListIterator;

@Service
public class OrderService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	OrderCartRepository orderCartRepository;
	
	@Autowired
	EcommerceUserRepository ecommerceUserRepository;
		
	@Autowired
	WalletRepository walletRepository;
	
	
	public List<Product> getProductByName(String  productName) {
		List<Product> listOfProducts = new ArrayList<Product>();
        if(productName != null ) 
        	listOfProducts.add(productRepository.findByproductNameContaining(productName));
             return listOfProducts;
	}
	
	public List<Product> getProductByType(String  productType) {
		List<Product> listOfProducts = new ArrayList<Product>();
        if(productType != null ) 
        	listOfProducts.addAll( productRepository.findByproductTypeContaining(productType));
        return listOfProducts;
	}
	
	public ResponseEntity<String> createProduct(@RequestBody ProductDTO produtcDto) {
		   
			  productRepository.save(getProduct(produtcDto));
			  return new ResponseEntity("Product Saved successfully",HttpStatus.OK);
	}

	 public Product getProduct(ProductDTO productDTO) {
		 Product p = new Product();
		 p.setProductName(productDTO.getProductName() );
		 p.setProductPrice(productDTO.getProductPrice());
		 p.setProductType(productDTO.getProductType());
		 return p;
	 }

	public ResponseEntity<String> createOrder(List<OrderDTO> orderDTO) throws Exception {
	  
		Set<CartItem> cartItems = new HashSet<>();
		OrderCart order = new OrderCart();
		Iterator<OrderDTO> inputItems = orderDTO.iterator();
        long orderTotal = 0;
        Product p;
        EcommerceUser user = null;
       boolean deductFromWallet =false;
		while(inputItems.hasNext()) {
			CartItem cartItem = new CartItem();
			OrderDTO inputOrder = (OrderDTO)inputItems.next();
		   String name =  inputOrder.getProductName();
		   Long userId = inputOrder.getUserId();
		   user = ecommerceUserRepository.findById(userId).orElse(null);
		 
		    System.out.println("  name  "+name);
		   System.out.println("   productRepository.findByproductNameContaining(name)   "+ productRepository.findByproductNameContaining(name));
		    p =  productRepository.findByproductNameContaining(name);
		    orderTotal +=  p.getProductPrice() * inputOrder.getProductQuantity();
		    cartItem.setProduct(p);
		    cartItems.add(cartItem);
		    cartItem.setTotalPrize(orderTotal);
		    cartItem.setQuantity(inputOrder.getProductQuantity());
		    
		    cartItemRepository.save(cartItem);
		}
		  Wallet wallet = user.getWallet();
		   long walletBalance = wallet.getWalletBalance();
		   Account account = wallet.getLinkedAccount();
			if((deductFromWallet && walletBalance < orderTotal) || (!deductFromWallet && account.getAccountBalance() < orderTotal)) throw new
			IncorrectAccountException("You do not have suficient balance to place this order"
					 	);
			if(deductFromWallet) {
				walletBalance = walletBalance - orderTotal;
				walletRepository.save(wallet);
			}else {
				   long accountBalance =  account.getAccountBalance() - orderTotal;
				   account.setAccountBalance(accountBalance); 
				   accountRepository.save(account);
			}

		order.setCartItems(cartItems);
		order.setCreatedOn(getcurrentDate());
		order.setOrderStatus("placed");
		order.setCreatedBy(user);
		orderCartRepository.save(order);
		return new ResponseEntity("The order created by you, order total price is "+orderTotal,HttpStatus.OK);

	}
private Date getcurrentDate()
{
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	System.out.println(formatter.format(date));	
	return date;
}

public ResponseEntity<Set<OrderCart>> findOrdersByDateRange(DateRangeDTO dateRangeDTO) {

	Set<OrderCart> orderSet = orderCartRepository.findByCreatedOnBetween (dateRangeDTO.getStartDate(),dateRangeDTO.getEndDate());
	 return new  ResponseEntity	(orderSet,HttpStatus.OK);
}

public ResponseEntity<EcommerceUser> addMoneyToWallet(WalletDTO walletDTO) {
	
	return null;
}

public ResponseEntity<EcommerceUser> addMoneyToWallet(LinkAccountDTO linkAccountDTO) {
	
	Optional<Account> a  = accountRepository.findById(linkAccountDTO.getAccountId());
	Account account = a.get();
	
	Optional<EcommerceUser> e = ecommerceUserRepository.findById(linkAccountDTO.getEcommerceUserId());
	EcommerceUser ecommerceUser = e.get();
	Wallet w =ecommerceUser.getWallet();
	w.setLinkedAccount(account);
	return new ResponseEntity(ecommerceUserRepository.save(ecommerceUser),HttpStatus.OK);
}

}
