package com.gatewayserver.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtil {

    private static final String TRANSACTION_ID = "Transaction-Id";

    public String getTransactionId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(TRANSACTION_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(TRANSACTION_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setTransactionId(ServerWebExchange exchange, String transactionId) {
        return this.setRequestHeader(exchange, TRANSACTION_ID, transactionId);
    }

}
