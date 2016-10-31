package com.home.acceptor.app;

import com.home.acceptor.spring.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import quickfix.ConfigError;

public class AcceptorLauncher {

    public static void main(String[] args) throws ConfigError {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(Config.class);
        registerShutdownHook(appContext);
    }

    private static void registerShutdownHook(ApplicationContext appContext) {
        ((AbstractApplicationContext)appContext).registerShutdownHook();
    }
}
