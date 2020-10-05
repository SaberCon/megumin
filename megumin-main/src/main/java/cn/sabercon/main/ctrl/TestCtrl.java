package cn.sabercon.main.ctrl;

import cn.sabercon.common.anno.ServiceController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@ServiceController("test")
public class TestCtrl {

    @GetMapping
    public void test() {

    }

    @PostMapping
    public String test2() {
        return "hello";
    }
}
