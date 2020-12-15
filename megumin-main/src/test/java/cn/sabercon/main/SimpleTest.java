package cn.sabercon.main;

import cn.sabercon.common.domian.BaseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.MethodInvocationRecorder;

/**
 * @author SaberCon
 * @since 1.0.0
 */
class SimpleTest {

    @Test
    void test() throws Exception {
        MethodInvocationRecorder.Recorded<BaseEntity> recorded = MethodInvocationRecorder.forProxyOf(BaseEntity.class);
        recorded.record(BaseEntity::getCtime).getPropertyPath().ifPresent(System.out::println);
    }
}
