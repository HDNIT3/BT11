package vn.iostar.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.model.Customer;

@RestController
@EnableMethodSecurity
public class CustomerController {
	final private List<Customer> customers = List.of(
			Customer.builder().id("001").name("Nguyễn Hữu Trung").email("trungspkt@gmail.com").build(),
			Customer.builder().id("002").name("Hữu Trung").email("trunghuu@gmail.com").build());

	@GetMapping("/hello")
	public String hello() {
		return "hello is Guest";
	}

	@GetMapping("/customers/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Customer> getCustomerList() {
		return this.customers;
	}

	@GetMapping("/customers/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Customer getCustomerList(@PathVariable("id") String id) {
		return this.customers.stream().filter(customer -> customer.getId().equals(id)).findFirst()
				.orElse(null);
	}
}