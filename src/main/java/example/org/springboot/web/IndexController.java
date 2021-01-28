package example.org.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; // src/main/resources/templates/index.mustache 로 전환되어 View Resolver가 처리함.
    }

    @GetMapping("/posts/save")
    public  String savePosts() {
        return "posts-save";    // call posts-save.mustache
    }
}
