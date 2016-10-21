package com.dreamworks.annotation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmonti on 10/20/16.
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public EvalMethodArgumentResolver evalParameterHandler(final ConfigurableBeanFactory configurableBeanFactory,
                                                           final Environment environment) {
        return new EvalMethodArgumentResolver(configurableBeanFactory, environment);
    }

    @Autowired
    private ConfigurableBeanFactory configurableBeanFactory;
    @Autowired
    private Environment environment;
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void initialize() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newHandlers = new ArrayList<>(argumentResolvers);
        newHandlers.add(0, evalParameterHandler(configurableBeanFactory, environment));

        requestMappingHandlerAdapter.setArgumentResolvers(newHandlers);
    }
}
