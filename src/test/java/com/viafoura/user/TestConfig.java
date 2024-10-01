package com.viafoura.user;

import com.viafoura.user.adapter.out.api.ReqresApiClient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {

    @Bean
    @Primary
    public ReqresApiClient mockReqresApiClient() {
        return Mockito.mock(ReqresApiClient.class);
    }

}