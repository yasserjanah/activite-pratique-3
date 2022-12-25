package me.janah.billingservice.web;

import lombok.AllArgsConstructor;
import me.janah.billingservice.MyConsulConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
@AllArgsConstructor
public class ConsulConfigRestController {
    private MyConsulConfig myConsulConfig;
    //@Value("${token.accessTokenTimeout}")
    //private long accessTokenTimeout;
    //@Value("${token.refreshTokenTimeout}")
    //private long refreshTokenTimeout;

    private MyVaultConfig myVaultConfig;

    @GetMapping(path = "/myConfig")
    public Map<String, Object> myConfig(){
        return Map.of(
                "myConsulConfig", myConsulConfig,
                "myVaultConfig", myVaultConfig
        );
    }

}
