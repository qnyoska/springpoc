package com.rbs.poc.spring.boot.cassandrarest;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepositoryCassandra extends CassandraRepository<User, Integer> {
}
