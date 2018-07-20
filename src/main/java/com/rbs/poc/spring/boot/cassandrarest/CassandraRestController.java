package com.rbs.poc.spring.boot.cassandrarest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CassandraRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositoryCassandra userRepositoryCassandra;

    @RequestMapping("/masscreate")
    public @ResponseBody Flux<User> massCreateUser(@RequestParam(value="id", defaultValue="1") String id) {
        int start = Integer.valueOf(id);
        for (int i = start ; i < start+1000000; i++) {
           userRepositoryCassandra.save(new User (i, ""+i));
        }
        return null;
    }

    @RequestMapping("/getsync")
    public @ResponseBody User getUserSync(@RequestParam(value="id", defaultValue="1") String id) {
        return userRepositoryCassandra.findById(Integer.valueOf(id)).orElse(new User(0,"nulla"));
    }

    @RequestMapping("/getflux")
    public @ResponseBody Flux<User> getUserAsyncFlux(@RequestParam(value="id", defaultValue="1") String id) throws Exception {
        //return userRepository.listByCassIdF(Integer.valueOf(id));
        return userRepository.listByCassIdF(new Random().nextInt(Integer.valueOf(id)));
    }

    @RequestMapping("/getfluxmass")
    public @ResponseBody Flux<User> getUserAsyncFlux() throws Exception {
        return userRepository.listAll();
    }

    @RequestMapping("/getfluxrandom")
    public @ResponseBody Flux<User> getUserAsyncFluxRandom(@RequestParam(value="id", defaultValue="1") String id) throws Exception {
        return userRepository.listByCassIdF(new Random().nextInt(Integer.valueOf(id)));
    }

}