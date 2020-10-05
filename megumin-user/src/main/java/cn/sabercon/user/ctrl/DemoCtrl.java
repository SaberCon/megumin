package cn.sabercon.user.ctrl;

import cn.sabercon.common.rest.ServiceController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@ServiceController("test")
public class DemoCtrl {

    @GetMapping
    public void test() {

    }

    @PostMapping
    public String test2() {
        return "hello";
    }
}
