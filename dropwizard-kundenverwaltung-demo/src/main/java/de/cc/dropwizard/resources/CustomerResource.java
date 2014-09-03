package de.cc.dropwizard.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;

import de.cc.dropwizard.kundenverwaltung.dao.CustomerDAO;
import de.cc.dropwizard.pojo.Customer;
import de.cc.dropwizard.view.CustomerView;

@Path("/customeradmin")
public class CustomerResource {
	private final CustomerDAO customerDAO;

	public CustomerResource(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Path("/customer")
	@POST
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Customer createCustomer(Customer customer) {
		return customerDAO.create(customer);
	}

	@Path("/customer")
	@GET
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> listCustomer() {
		return customerDAO.findAll();
	}

	@Path("/customer/{customerId}")
	@GET
	@UnitOfWork
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getPerson(@PathParam("customerId") LongParam customerId) {
		return findSafely(customerId.get());
	}

	private Customer findSafely(long customerId) {
		final Optional<Customer> customer = customerDAO.findById(customerId);
		if (!customer.isPresent()) {
			throw new NotFoundException("No such user.");
		}
		return customer.get();
	}

	@GET
	@Path("/customer/{customerId}/view_freemarker")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public CustomerView getCustomerViewFreemarker(
			@PathParam("customerId") LongParam customernId) {
		return new CustomerView(CustomerView.Template.FREEMARKER,
				findSafely(customernId.get()));
	}

	@GET
	@Path("/customer/{customerId}/view_mustache")
	@UnitOfWork
	@Produces(MediaType.TEXT_HTML)
	public CustomerView getCustomerViewMustache(
			@PathParam("customerId") LongParam customerId) {
		return new CustomerView(CustomerView.Template.MUSTACHE,
				findSafely(customerId.get()));
	}
}
