package example.org.springboot.web;

import example.org.springboot.service.posts.PostsService;
import example.org.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model) {  // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장가능. postsService.fAD()로 가져온 결과를
        model.addAttribute("posts", postsService.findAllDesc());    // posts로 index.mustache에 전달함.
        return "index"; // src/main/resources/templates/index.mustache 로 전환되어 View Resolver가 처리함.
    }

    @GetMapping("/posts/save")
    public  String savePosts() {

        return "posts-save";    // call posts-save.mustache
    }

    @GetMapping("/posts/update/{id}")
    public String updatePosts(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
