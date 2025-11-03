package com.conference.conferenceservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="conference.params")
public record CustomerConfigParams(int x,int y) {
}
