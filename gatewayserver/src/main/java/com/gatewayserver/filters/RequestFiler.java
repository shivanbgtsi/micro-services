package com.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Order(1)
@Component
public class RequestFiler implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFiler.class);

    @Autowired
    private FilterUtil filterUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if(getTransactionId(headers)){
            logger.debug("Transaction Id "+ filterUtil.getTransactionId(headers));
        }else {
            String transactionId = generateTransactionId();
            logger.debug("Transaction Id generated "+ transactionId);
            filterUtil.setTransactionId(exchange,transactionId);
        }
        return chain.filter(exchange);
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    private boolean getTransactionId(HttpHeaders httpHeaders){
        return filterUtil.getTransactionId(httpHeaders) != null;
    }
}
