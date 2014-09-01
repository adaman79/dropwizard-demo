package de.cc.dropwizard.view;

import io.dropwizard.views.View;
import de.cc.dropwizard.pojo.Customer;

public class CustomerView extends View {
	private final Customer customer;

	public enum Template {
		FREEMARKER("freemarker/customer.ftl"), MUSTACHE(
				"mustache/customer.mustache");
		private String templateName;

		private Template(String templateName) {
			this.templateName = templateName;
		}

		public String getTemplateName() {
			return templateName;
		}
	}

	public CustomerView(CustomerView.Template template, Customer customer) {
		super(template.getTemplateName());
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}
}
