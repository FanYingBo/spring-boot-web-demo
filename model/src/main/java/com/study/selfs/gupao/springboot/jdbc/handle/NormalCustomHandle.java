package com.study.selfs.gupao.springboot.jdbc.handle;


import com.study.selfs.gupao.springboot.jdbc.domain.NormalCustom;
import com.study.selfs.gupao.springboot.jdbc.repository.NormalCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class NormalCustomHandle {

    @Autowired
    private NormalCustomRepository normalCustomRepository;


    public Mono<ServerResponse> save(ServerRequest serverRequest){

        Mono<NormalCustom> normalCustomMono = serverRequest.bodyToMono(NormalCustom.class);
        // 转换工作
        Mono<Boolean> mapMono = normalCustomMono.map(normalCustomRepository::save);
        // 这里的异步还是同一个线程处理
        System.out.printf("[Thread：%s]\n",Thread.currentThread().getName());
        return ServerResponse.ok().body(mapMono,Boolean.class);
    }

    public Mono<ServerResponse> findAllCustomRouter(ServerRequest serverRequest){
        List<NormalCustom> allNormalCustom = normalCustomRepository.findAllNormalCustom();
        Flux<NormalCustom> normalCustomFlux = Flux.fromIterable(allNormalCustom);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(normalCustomFlux,NormalCustom.class);

    }

    public Flux<NormalCustom> findAllCustom(){
        List<NormalCustom> allNormalCustom = normalCustomRepository.findAllNormalCustom();
        Flux<NormalCustom> normalCustomFlux = Flux.fromIterable(allNormalCustom);
        return normalCustomFlux;
    }

}
