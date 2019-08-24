package it.lucariccitelli.scheduledrestcaller.core.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author luc created on 16 ago 2019
 *
 */
public class GlobalAuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("admin");
	}

}
