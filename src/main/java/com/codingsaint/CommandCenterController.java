package com.codingsaint;

import com.codingsaint.service.CommandCenterService;
import com.codingsaint.service.SuperheroClient;
import domain.Superhero;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@ExecuteOn(TaskExecutors.IO)
public class CommandCenterController {
    private static final Logger logger = LoggerFactory.getLogger(CommandCenterController.class);

    private final CommandCenterService service;
    private final SuperheroClient client;

    public CommandCenterController(CommandCenterService commandCenterService, SuperheroClient client) {
        this.service = commandCenterService;
        this.client = client;
    }

    @Get("superhero/{id}")
    public Mono<Superhero> superheroesById(Long id) {

        //return service.superheroesById(id);
        return client.superheroesById(id);

    }

    @Post("/superhero")
    Mono<Superhero> create(@Valid Superhero superhero) {
        logger.info("Call to add a new saviour {} ", superhero);
      //  return Mono.from(service.create(superhero));
        return Mono.from(client.create(superhero));
    }

    @Put("superhero")
    Mono<Superhero> update(@Valid Superhero superhero) {
       // return Mono.from(service.update(superhero));
        return Mono.from(client.update(superhero));
    }

    @Delete("superhero/{id}")
    Mono<HttpResponse<?>> delete(@NotNull Long id) {
       /* return Mono
                .from(service.delete(id))
                .map(deleted -> deleted > 0 ? HttpResponse.noContent() : HttpResponse.notFound());*/

        return Mono
                .from(client.delete(id));
    }


}
