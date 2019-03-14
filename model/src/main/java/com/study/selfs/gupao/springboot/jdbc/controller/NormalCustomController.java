package com.study.selfs.gupao.springboot.jdbc.controller;


import com.study.selfs.gupao.springboot.jdbc.domain.NormalCustom;
import com.study.selfs.gupao.springboot.jdbc.handle.NormalCustomHandle;
import com.study.selfs.gupao.springboot.jdbc.repository.NormalCustomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@Controller
public class NormalCustomController {

    private NormalCustomRepository normalCustomRepository;

    private NormalCustomHandle normalCustomHandle;

    public NormalCustomController(NormalCustomRepository normalCustomRepository, NormalCustomHandle normalCustomHandle) {
        this.normalCustomRepository = normalCustomRepository;
        this.normalCustomHandle = normalCustomHandle;
    }
//
//    @GetMapping("/web/mvc/user/save")
//    public Boolean save(@RequestParam("name")String  name,@RequestParam("age") Integer age){
//        NormalCustom normalCustom = new NormalCustom();
//        normalCustom.setName(name);
//        normalCustom.setAge(age);
//        return normalCustomRepository.save(normalCustom);
//    }

    @PostMapping("/web/mvc/user/save")
    public Boolean save(@Valid @RequestBody NormalCustom normalCustom){
        System.out.printf("[Thread：%s] %s\n",Thread.currentThread().getName(),normalCustom);
        switch (normalCustom.getLocation()) {
            case 1:
                return normalCustomRepository.jdbcSave(normalCustom);
            case 2:
                return normalCustomRepository.transactionSave(normalCustom);
            default:
                return normalCustomRepository.save(normalCustom);
        }
    }

    @RequestMapping("/web/flux/user/list")
    public String userList(Model model){
        Flux<NormalCustom> customs = normalCustomHandle.findAllCustom();
        IReactiveDataDriverContextVariable dataDriverContextVariable = new ReactiveDataDriverContextVariable(customs,1,1);
        model.addAttribute("customs",dataDriverContextVariable);
        return "userindex";
    }
}
