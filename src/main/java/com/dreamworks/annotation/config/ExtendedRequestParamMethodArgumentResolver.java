package com.dreamworks.annotation.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;

import javax.servlet.ServletException;

/**
 * Created by mmonti on 10/20/16.
 */
public class ExtendedRequestParamMethodArgumentResolver
        extends RequestParamMethodArgumentResolver implements Resolvable {

    /**
     *
     * @param beanFactory
     * @param useDefaultResolution
     */
    public ExtendedRequestParamMethodArgumentResolver(final ConfigurableBeanFactory beanFactory,
                                                      final boolean useDefaultResolution) {
        super(beanFactory, useDefaultResolution);
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
    public void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
        super.handleMissingValue(name, parameter);
    }


    @Override
    public String getResolvableName() {
        return RequestParam.class.getName();
    }
}
