package de.cc.dropwizard.view;

import io.dropwizard.views.View;

import java.util.List;

import de.cc.dropwizard.pojo.Customer;

public class ListCustomerView extends View{
	private final List<Customer> customers;
	
	public ListCustomerView(String template, List<Customer> customers) {
		super(template);
		this.customers = customers;
	}

	public List<Customer> getCustomers() {
		return customers;
	}
}
