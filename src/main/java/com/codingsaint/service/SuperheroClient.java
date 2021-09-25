package com.codingsaint.service;

import com.codingsaint.configurations.CommandCenterConstants;
import domain.Superhero;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.codingsaint.configurations.CommandCenterConstants.*;

@Client(SUPERHERO_URL)
@Header(name = "Accept", value = HEADER_ACCEPT)
public interface SuperheroClient {
    @Get("superheroes")
    Flux<Superhero> superheroes();

    @Get("superhero/{id}")
    Mono<Superhero> superheroesById(Long id);

    @Post("/superhero")
    Publisher<Superhero> create( @Body  Superhero superhero);

    @Put("superhero")
    Publisher<Superhero> update( Superhero superhero);

    @Delete("superhero/{id}")
    Publisher<Long> delete(Long id);
}

