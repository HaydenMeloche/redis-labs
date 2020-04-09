package com.redislabs.university.RU102J.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.setup.Environment;
import redis.clients.jedis.JedisPool;

public class JedisFactory {
    @JsonProperty
    private String host;

    @JsonProperty
    private Integer port;

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public JedisPool build(Environment environment) {
        final JedisPool pool = new JedisPool(getHost(), getPort());

        return pool;
    }
}
