package it.lucariccitelli.scheduledrestcaller.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.lucariccitelli.scheduledrestcaller.core.security.GlobalAuditorAwareImpl;

/**
 * @author luc created on 16 ago 2019
 *
 */
@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class PersistenceConfig {

	@Bean
	AuditorAware<String> auditorProvider() {
		return new GlobalAuditorAwareImpl();
	}

}
