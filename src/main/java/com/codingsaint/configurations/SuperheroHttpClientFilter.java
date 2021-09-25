package com.codingsaint.configurations;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Filter;
import io.micronaut.http.filter.ClientFilterChain;
import io.micronaut.http.filter.HttpClientFilter;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Filter( "/**")
public class SuperheroHttpClientFilter implements HttpClientFilter {
    private static final Logger logger= LoggerFactory.getLogger(SuperheroHttpClientFilter.class);
    @Override
    public Publisher<? extends HttpResponse<?>> doFilter(MutableHttpRequest<?> request, ClientFilterChain chain) {
        var header= request.getHeaders();
        logger.debug("Adding HTTP Headers to client ");
        header.add("User-Agent", "Coding Saint HTTP Client") ;
        header.add("Accept", "application/json");
        String GUID= UUID.randomUUID().toString();
        header.add("TRACKING_ID", GUID);
        logger.info("Request with id {} , url {} , body {} to superheroes API ", GUID, request.getUri(), request.getBody());

        return chain.proceed(request);
    }
}
