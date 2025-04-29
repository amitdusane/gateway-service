package com.tm.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RequestLoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestLoggingGatewayFilterFactory.Config> {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingGatewayFilterFactory.class);

    public RequestLoggingGatewayFilterFactory() {
        super(Config.class);
        logger.info("RequestLoggingGatewayFilterFactory initialized");
    }

    @Override
    public GatewayFilter apply(Config config) {
        logger.info("RequestLoggingGatewayFilterFactory apply() method invoked");
        return (exchange, chain) -> {
            String requestPath = exchange.getRequest().getURI().getPath();
            logger.info("Request Path: {}", requestPath);
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> logger.info("Response completed for: {}", requestPath))
            );
        };
    }

    public static class Config {
        // Add custom configurations here if needed
    }
}

