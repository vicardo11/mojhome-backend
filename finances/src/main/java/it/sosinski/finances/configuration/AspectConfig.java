package it.sosinski.finances.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.sosinski.aspectlibrary.logger.MethodAroundAspect;

/**
 * Class providing beans for aspects from AspectDirectory
 */
@Configuration
public class AspectConfig {

	@Bean
	public MethodAroundAspect methodAroundAspect() {
		return new MethodAroundAspect();
	}

}