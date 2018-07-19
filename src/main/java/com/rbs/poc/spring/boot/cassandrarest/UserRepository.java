package com.rbs.poc.spring.boot.cassandrarest;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //
    @Query("select * from user where id = :id")
    @Async
    Flux<User> listByCassId(@Param("id")Integer id);

    @Query("select * from user where id = :id")
    @Async
    Flux<User> listByCassIdF(@Param("id")Integer id);

    @Query("select * from user where id = :id")
    @Async
    CompletableFuture<User> listByCassIdCF(@Param("id")Integer id);

    @Query("select * from user ")
    @Async
    Flux<User> listAll();
}
