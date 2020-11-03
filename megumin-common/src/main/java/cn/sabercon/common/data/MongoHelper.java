package cn.sabercon.common.data;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * mongo 操作协助类
 *
 * @author SaberCon
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class MongoHelper {

    @Delegate
    private final MongoTemplate template;
}
