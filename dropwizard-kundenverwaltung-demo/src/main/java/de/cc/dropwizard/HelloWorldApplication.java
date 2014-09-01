package de.cc.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import de.cc.dropwizard.health.TemplateHealthCheck;
import de.cc.dropwizard.kundenverwaltung.dao.CustomerDAO;
import de.cc.dropwizard.pojo.Customer;
import de.cc.dropwizard.pojo.Template;
import de.cc.dropwizard.resources.CustomerResource;
import de.cc.dropwizard.resources.HelloWorldResource;
import de.cc.dropwizard.resources.ViewResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);

	}

	private final HibernateBundle<HelloWorldConfiguration> hibernateBundle = new HibernateBundle<HelloWorldConfiguration>(
			Customer.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(
				HelloWorldConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<HelloWorldConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(
					HelloWorldConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(hibernateBundle);
		bootstrap.addBundle(new ViewBundle());

	}

	@Override
	public void run(HelloWorldConfiguration configuration,
			Environment environment) throws Exception {
		final CustomerDAO customerDAO = new CustomerDAO(
				hibernateBundle.getSessionFactory());
		final Template template = configuration.buildTemplate();

		environment.healthChecks().register("template",
				new TemplateHealthCheck(template));

		environment.jersey().register(new HelloWorldResource(template));
		environment.jersey().register(new ViewResource());
		environment.jersey().register(new CustomerResource(customerDAO));

	}

}
