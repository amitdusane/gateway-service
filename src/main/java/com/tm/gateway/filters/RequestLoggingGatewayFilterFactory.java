package com.tm.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestLoggingGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestLoggingGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingGatewayFilterFactory.class);

    public RequestLoggingGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String requestPath = exchange.getRequest().getURI().getPath();
            logger.info("Request Path: {}", requestPath);

            return chain.filter(exchange).then(
                    exchange.getResponse().setComplete().doOnSuccess(aVoid ->
                            logger.info("Response completed for: {}", requestPath)
                    )
            );
        };
    }

    // Configuration class for the filter (optional)
    public static class Config {
        // Add configuration properties if needed
    }
}
