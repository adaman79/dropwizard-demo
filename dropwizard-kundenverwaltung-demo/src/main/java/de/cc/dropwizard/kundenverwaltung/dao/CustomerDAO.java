package de.cc.dropwizard.kundenverwaltung.dao;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;

import de.cc.dropwizard.pojo.Customer;

public class CustomerDAO extends AbstractDAO<Customer> {
	public CustomerDAO(SessionFactory factory) {
		super(factory);
	}

	public Optional<Customer> findById(Long id) {
		return Optional.fromNullable(get(id));
	}

	public Customer create(Customer Customer) {
		return persist(Customer);
	}

	public List<Customer> findAll() {
		return list(namedQuery("de.cc.dropwizard.pojo.Customer.findAll"));
	}
}
