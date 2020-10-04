package cn.sabercon.user;

import cn.sabercon.common.enums.type.SortEnum;
import cn.sabercon.user.ctrl.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@SpringBootTest
class CommonTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void test() {
        Demo demo = new Demo();
        demo.setDate(LocalDateTime.now());
        demo.setName("hello");
        demo.setSort(SortEnum.ASC);
        mongoTemplate.save(demo);
        Demo demo1 = mongoTemplate.findById(demo.getId(), Demo.class);
        System.out.println(demo1);
    }
}
