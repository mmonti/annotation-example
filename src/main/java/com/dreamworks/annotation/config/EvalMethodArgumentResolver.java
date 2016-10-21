package com.dreamworks.annotation.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmonti on 10/20/16.
 */
public class EvalMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

    private Environment environment;
    private Map<String, Resolvable> resolvables = new HashMap<>();

    /**
     *
     * @param beanFactory
     * @param environment
     */
    public EvalMethodArgumentResolver(final ConfigurableBeanFactory beanFactory, final Environment environment) {
        super(beanFactory);
        this.environment = environment;
        this.add(new ExtendedRequestHeaderMethodArgumentResolver(beanFactory));
        this.add(new ExtendedRequestParamMethodArgumentResolver(beanFactory, true));
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(RequestParam.class)) {
            RequestParam requestParam = methodParameter.getParameterAnnotation(RequestParam.class);
            return new NamedValueInfo(requestParam.value(), requestParam.required(), requestParam.defaultValue());
        }
        RequestHeader requestHeader = methodParameter.getParameterAnnotation(RequestHeader.class);
        return new NamedValueInfo(requestHeader.value(), requestHeader.required(), requestHeader.defaultValue());
    }

    @Override
    protected Object resolveName(String name, MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws Exception {
        Resolvable resolvable = getResolvable(methodParameter);
        Object resolvedName = resolvable.resolve(name, methodParameter, nativeWebRequest);
        if (resolvedName == null) {
            resolvedName = this.environment.resolvePlaceholders(name);
            if (resolvedName != null) {
                resolvedName = resolvable.resolve(resolvedName.toString(), methodParameter, nativeWebRequest);
            }
        }
        return resolvedName;
    }

    /**
     *
     * @param methodParameter
     * @return
     */
    private Resolvable getResolvable(final MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestParam.class) ?
                    this.resolvables.get(RequestParam.class.getCanonicalName()) : this.resolvables.get(RequestHeader.class.getCanonicalName());
    }

    @Override
    protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
        getResolvable(parameter).handleMissingValue(name, parameter);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Eval.class);
    }

    public void add(final Resolvable resolvable) {
        this.resolvables.put(resolvable.getResolvableName(), resolvable);
    }
}
