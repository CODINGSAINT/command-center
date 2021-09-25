package com.codingsaint.service;

import com.codingsaint.configurations.CommandCenterConstants;
import domain.Superhero;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Singleton;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.codingsaint.configurations.CommandCenterConstants.SUPERHERO_URL;

@Singleton
public class CommandCenterService {
    private static final Logger logger = LoggerFactory.getLogger(CommandCenterService.class);

    private final HttpClient client;

    public CommandCenterService(@Client(id="superHeroes") HttpClient client) {
        this.client = client;
    }


    public Flux<Superhero> superheroes() {
        logger.info("Fetching all of the superheroes in universe ");
        var uri = UriBuilder.of("superheroes").build();
        HttpRequest request = HttpRequest.GET(uri)
                .header("Accept", "application/json");
        return Flux.from(client.retrieve(request));
    }


    public Mono<Superhero> superheroesById(Long id) {
       // logger.info("Finding the saviour  id {} ", id);
        var uri= UriBuilder.of("superhero").path(String.valueOf(id)).build();
        HttpRequest req = HttpRequest.GET(uri)
                .header("Accept", "application/json");
        return Mono.from( client.retrieve(req));

    }

    public Publisher<Superhero> create(Superhero superhero) {
        logger.info("Adding a new saviour {} ", superhero);
       var uri= UriBuilder.of("superhero").build();
        HttpRequest req = HttpRequest.POST(uri, superhero)
                 .header("Accept", "application/json");
        var created = Mono.from( client.retrieve(req));
        return created;
    }

    public Publisher<Superhero> update(Superhero superhero) {
        logger.info("Updating the old saviour {} ", superhero);

        var uri= UriBuilder.of("superhero").build();
        HttpRequest req = HttpRequest.PUT(uri, superhero)
                .header("Accept", "application/json");
        var updated = Mono.from( client.retrieve(req));

        return updated;
    }

    public Publisher<Long> delete(Long id) {
        logger.info("Purging the saviour {} ", id);
        var uri= UriBuilder.of("superhero").path(String.valueOf(id)).build();
        HttpRequest req = HttpRequest.DELETE(uri.toString())
                 .header("Accept", "application/json");
        return Mono.from( client.exchange(req));

    }

}
