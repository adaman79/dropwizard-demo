package de.cc.dropwizard.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.base.Optional;

import de.cc.dropwizard.pojo.Template;

public class TemplateHealthCheck extends HealthCheck{

	private final Template template;
	
	public TemplateHealthCheck(Template template) {
		this.template = template;
	}
	@Override
	protected Result check() throws Exception {
		 template.render(Optional.of("woo"));
		 template.render(Optional.<String>absent());
		 return Result.healthy();
	}

}
