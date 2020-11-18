package cn.sabercon.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * spring 上下文环境工具类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
public class ContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext context;

    private static Environment environment;

    private static final Queue<Consumer<ApplicationContext>> CALL_BACKS = new ConcurrentLinkedQueue<>();

    private static boolean isContextReady;

    /**
     * 针对某些需要 context 的初始化方法提交回调, 回调会在 context 初始化后执行, 如果提交时已初始化完成则会立即执行
     */
    public static void addCallBack(Consumer<ApplicationContext> callback) {
        if (isContextReady) {
            callback.accept(context);
        } else {
            CALL_BACKS.add(callback);
        }
    }

    public static boolean isProd() {
        return Arrays.stream(environment.getActiveProfiles()).anyMatch("prod"::equalsIgnoreCase);
    }

    public static boolean isNotProd() {
        return !isProd();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) context.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return context.getBean(requiredType);
    }

    public static <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
        assertEnvironmentInjected();
        return environment.getProperty(key, targetType, defaultValue);
    }

    public static String getProperty(String key, String defaultValue) {
        assertEnvironmentInjected();
        return environment.getProperty(key, defaultValue);
    }

    public static String getProperty(String key) {
        assertEnvironmentInjected();
        return environment.getProperty(key);
    }

    private static void assertEnvironmentInjected() {
        Assert.notNull(environment, "environment is not ready yet");
    }

    private static void assertContextInjected() {
        Assert.notNull(context, "application context is not ready yet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        environment = applicationContext.getBean(Environment.class);
        isContextReady = true;
        while (!CALL_BACKS.isEmpty()) {
            CALL_BACKS.poll().accept(context);
        }
    }

    @Override
    public void destroy() throws Exception {
        context = null;
        environment = null;
    }
}
