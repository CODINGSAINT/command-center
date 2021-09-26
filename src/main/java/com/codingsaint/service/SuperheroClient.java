package com.codingsaint.service;

import com.codingsaint.configurations.CommandCenterConstants;
import domain.Superhero;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.codingsaint.configurations.CommandCenterConstants.*;

@Client(id = "superHeroes")
@Header(name = "Accept", value = HEADER_ACCEPT)
public interface SuperheroClient {
    @Get("/rx/superheroes")
    Flux<Superhero> superheroes();

    @Get("/rx/superhero/{id}")
    Mono<Superhero> superheroesById(Long id);

    @Post("/rx/superhero")
    Publisher<Superhero> create( @Body  Superhero superhero);

    @Put("/rx/superhero")
    Publisher<Superhero> update( Superhero superhero);

    @Delete("/rx/superhero/{id}")
    Publisher<HttpResponse<Long>> delete(Long id);
}

