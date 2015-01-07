package org.move2dbit.citas.interceptors;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@LogTracing
public class LogInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
	public Object logMethodEntry(InvocationContext ctx) throws Exception {
        logger.entering(ctx.getTarget().getClass().getName(),ctx.getMethod().getName());
	    return ctx.proceed();
	 }
    
}
