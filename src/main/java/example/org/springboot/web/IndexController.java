package example.org.springboot.web;

import example.org.springboot.config.auth.dto.SessionUser;
import example.org.springboot.service.posts.PostsService;
import example.org.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {  // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장가능. postsService.fAD()로 가져온 결과를
        model.addAttribute("posts", postsService.findAllDesc());      // posts로 index.mustache에 전달함.

        SessionUser user = (SessionUser) httpSession.getAttribute("user");  // COA2US 에서 로그인 성공시 httpSe~~user")에서 값을 가져옴

        if (user != null) { // 세션에 저장된 값이 있을 때만 model에 userName 등록. 없으면 로그인 버튼이 보이게
            model.addAttribute("userName", user.getName());
        }

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
