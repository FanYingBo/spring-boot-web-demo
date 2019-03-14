package com.study.selfs.gupao.springboot.jdbc.webflux;


import com.study.selfs.gupao.springboot.jdbc.handle.NormalCustomHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebFluxConfiguration {


    @Bean
    public RouterFunction<ServerResponse> save(NormalCustomHandle normalCustomHandle){
        return route(POST("/web/flux/user/save"),normalCustomHandle ::save);
    }

    @Bean
    public RouterFunction<ServerResponse> queryAll(NormalCustomHandle normalCustomHandle){
        return route(GET("/web/flux/users/"),normalCustomHandle::findAllCustomRouter);
    }

}
