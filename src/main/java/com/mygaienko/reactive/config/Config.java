package com.mygaienko.reactive.config;

import com.aerospike.client.Host;
import com.aerospike.client.async.EventLoops;
import com.aerospike.client.async.NioEventLoops;
import com.mygaienko.reactive.repo.CarRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractReactiveAerospikeDataConfiguration;
import org.springframework.data.aerospike.repository.config.EnableReactiveAerospikeRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableReactiveAerospikeRepositories(basePackageClasses = CarRepo.class)
public class Config extends AbstractReactiveAerospikeDataConfiguration {

    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host("127.0.0.1", 3000));
    }

    @Override
    protected String nameSpace() {
        return "test";
    }

    @Override
    protected EventLoops eventLoops() {
        return new NioEventLoops();
    }

}
