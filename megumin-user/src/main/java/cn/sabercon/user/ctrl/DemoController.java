package cn.sabercon.user.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SaberCon
 * @since 1.0.0
 */
@RestController
public class DemoController {

    @GetMapping("test")
    public void test() {
        System.out.println("aaa");
    }
}
