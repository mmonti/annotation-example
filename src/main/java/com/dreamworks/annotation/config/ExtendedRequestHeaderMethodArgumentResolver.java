package com.dreamworks.annotation.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

/**
 * Created by mmonti on 10/20/16.
 */
public class ExtendedRequestHeaderMethodArgumentResolver
        extends RequestHeaderMethodArgumentResolver implements Resolvable{

    /**
     *
     * @param beanFactory
     */
    public ExtendedRequestHeaderMethodArgumentResolver(final ConfigurableBeanFactory beanFactory) {
        super(beanFactory);
    }

    /**
     *
     * @param key
     * @param methodParameter
     * @param nativeWebRequest
     * @return
     * @throws Exception
     */
    public Object resolve(final String key, final MethodParameter methodParameter,
                          final NativeWebRequest nativeWebRequest) throws Exception {

        return super.resolveName(key, methodParameter, nativeWebRequest);
    }

    @Override
    public void handleMissingValue(String name, MethodParameter parameter) throws ServletRequestBindingException {
        super.handleMissingValue(name, parameter);
    }

    @Override
    public String getResolvableName() {
        return RequestHeader.class.getName();
    }
}
