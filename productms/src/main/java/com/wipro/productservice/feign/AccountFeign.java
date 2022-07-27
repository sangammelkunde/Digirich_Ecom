package com.wipro.productservice.feign;

import java.util.List;

import com.wipro.productservice.model.AccountCreationStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.wipro.productservice.model.Account;


@FeignClient(name = "account-ms", url = "${feign.url-account-service}")
public interface AccountFeign {

	/**
	 * @param token
	 * @param customerId
	 * @param account
	 * @return AccountCreationStatus
	 */
	@PostMapping("/createAccount/{customerId}")
	public AccountCreationStatus createAccount(@RequestHeader("Authorization") String token,
                                               @PathVariable String customerId, @RequestBody Account account);

	/**
	 * @param token
	 * @param customerId
	 * @return List<Account>
	 */
	@GetMapping("/getAccounts/{customerId}")
	public List<Account> getCustomerAccount(@RequestHeader("Authorization") String token,
			@PathVariable String customerId);

}
