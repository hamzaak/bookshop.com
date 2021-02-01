package com.bookshop.shoppingcartms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bookshop.shoppingcartms.dto.AccountDTO;

@FeignClient(value = "account-ms", url = "http://accountservice:9002/api/accounts")
public interface IAccountServiceClient {
 
   @GetMapping(value = "/get/{id}")
   AccountDTO getAccount(@PathVariable("id") String id);
 
}