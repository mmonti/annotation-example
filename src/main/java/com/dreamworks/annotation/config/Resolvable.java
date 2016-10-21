package com.dreamworks.annotation.config;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.ServletException;

/**
 * Created by mmonti on 10/20/16.
 */
public interface Resolvable {

    /**
     *
     * @param key
     * @param methodParameter
     * @param nativeWebRequest
     * @return
     * @throws Exception
     */
    Object resolve(final String key, final MethodParameter methodParameter,
                   final NativeWebRequest nativeWebRequest) throws Exception;

    /**
     *
     * @param name
     * @param parameter
     * @throws ServletException
     */
    void handleMissingValue(final String name, final MethodParameter parameter) throws ServletException;

    /**
     *
     * @return
     */
    String getResolvableName();
}
