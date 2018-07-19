package com.rbs.poc.spring.boot.cassandrarest.config;

import com.rbs.poc.spring.boot.cassandrarest.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


@Configuration
@EnableCassandraRepositories(repositoryBaseClass = UserRepository.class)
public class CassandraConfig extends AbstractCassandraConfiguration {


    @Value("${cassandra.contactpoint.host}")
    private String cassandrahost;
    @Value("${cassandra.contactpoint.port}")
    private Integer cassandraport;

    @Override
    protected String getKeyspaceName() {
        return "users";
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster =
                new CassandraClusterFactoryBean();
        //cluster.setContactPoints("5.249.151.54");
        cluster.setContactPoints("10.0.0.97");
        //cluster.setContactPoints(cassandrahost);
        cluster.setPort(9042);
        //cluster.setPort(cassandraport);

        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping()
            throws ClassNotFoundException {
        return new CassandraMappingContext();
    }
}
