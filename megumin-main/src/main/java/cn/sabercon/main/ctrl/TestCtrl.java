package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import cn.sabercon.common.enums.type.Gender;
import cn.sabercon.main.domain.entity.User;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@ServiceController("test")
public class TestCtrl {

    @GetMapping("1")
    public void test1(@ApiParam("性别") Gender gender) {
        System.out.println(gender);
    }

    @GetMapping("2")
    public void test2(User user) {
        System.out.println(user);
    }

    @PostMapping("3")
    public void test3(@RequestBody User user) {
        System.out.println(user);
    }

    @GetMapping
    public User test() {
        return new User();
    }
}
