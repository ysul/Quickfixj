package com.home.initiator.app;

import com.home.initiator.spring.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.support.AbstractApplicationContext;
import quickfix.ConfigError;

public class InitiatorLauncher {

    public static void main(String[] args) throws ConfigError {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(Config.class);
        registerShutdownHook(appContext);
    }

    private static void registerShutdownHook(ApplicationContext appContext) {
        ((AbstractApplicationContext)appContext).registerShutdownHook();
    }
}
