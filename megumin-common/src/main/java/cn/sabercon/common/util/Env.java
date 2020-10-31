package cn.sabercon.common.util;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 环境相关判断
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
public class Env implements EnvironmentAware {

    /**
     * 当前环境
     */
    private static String[] profiles;

    private static final String PROD = "prod";

    public static boolean isProd() {
        return Arrays.stream(profiles).anyMatch(PROD::equalsIgnoreCase);
    }

    @Override
    public void setEnvironment(Environment environment) {
        profiles = environment.getActiveProfiles();
    }
}
