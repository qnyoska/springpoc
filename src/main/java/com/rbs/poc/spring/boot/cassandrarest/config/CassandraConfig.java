package com.rbs.poc.spring.boot.cassandrarest.config;

import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
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
        cluster.setPoolingOptions(new PoolingOptions().setMaxQueueSize(50000)
                .setMaxConnectionsPerHost(HostDistance.LOCAL, 1000)
                .setMaxConnectionsPerHost(HostDistance.REMOTE, 1000)
                .setMaxRequestsPerConnection(HostDistance.LOCAL, 1000)
                .setMaxRequestsPerConnection(HostDistance.REMOTE, 1000)
                .setMaxQueueSize(1000)
                .setCoreConnectionsPerHost(HostDistance.LOCAL, 1000)
                .setCoreConnectionsPerHost(HostDistance.REMOTE, 1000));
        //cluster.setContactPoints("127.0.0.1");
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
