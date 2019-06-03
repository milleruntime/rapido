package app;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import config.RapidoConfig;

@SpringBootApplication
public class Rapido {
    private static Logger log = LoggerFactory.getLogger(Rapido.class);
    private static RapidoConfig config;

    public static void main(String[] args) {
        SpringApplication.run(Rapido.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            log.info("Booting up application server");
            log.info("Loading configuration file from " + RapidoConfig.filePath);
            config = new RapidoConfig();
            log.info("Started on " + new Date(ctx.getStartupDate()));
        };
    }

    public static final RapidoConfig getConfig() {
        return config;
    }

    public static void printBeans(ApplicationContext ctx) {
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            log.info(beanName);
        }
    }
}