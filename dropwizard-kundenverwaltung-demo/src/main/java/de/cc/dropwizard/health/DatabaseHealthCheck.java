package de.cc.dropwizard.health;

import io.dropwizard.db.DataSourceFactory;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {
	private final DataSourceFactory dataSourceFactory;

	public DatabaseHealthCheck(DataSourceFactory dataSourceFactory) {
		this.dataSourceFactory = dataSourceFactory;
	}

	@Override
	public HealthCheck.Result check() throws Exception {
		if (dataSourceFactory.getCheckConnectionOnConnect()) {
			return HealthCheck.Result.healthy();
		} else {
			return HealthCheck.Result.unhealthy("Database "
					+ dataSourceFactory.getUrl() + " is not available");
		}
	}
}
