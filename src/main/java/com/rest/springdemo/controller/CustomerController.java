package com.rest.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.springdemo.entity.Customer;
import com.rest.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){	
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		
		Customer customer = customerService.getCustomer(id);
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer not found: " + id);
		}
		
		return customer;
	}
	
	@PostMapping("/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		
		customer.setId(0);
		
		customerService.saveCustomer(customer);
		
		return customer;
	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@DeleteMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable int id) {
		
		Customer customer = customerService.getCustomer(id);
		
		if(customer == null) {
			throw new CustomerNotFoundException("Customer not found: " + id);
		}
		customerService.deleteCustomer(id);
		
		return "Deleted customer id - " + id;
	}
}
