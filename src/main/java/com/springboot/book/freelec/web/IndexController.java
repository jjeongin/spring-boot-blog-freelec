package com.springboot.book.freelec.web;

import com.springboot.book.freelec.config.auth.LoginUser;
import com.springboot.book.freelec.config.auth.dto.SessionUser;
import com.springboot.book.freelec.service.posts.PostsService;
import com.springboot.book.freelec.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession; // for user login

    // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체 저장.
    // 여기서는 postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달.
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
        // return "src/main/resources/templates/" + return text + ".mustache"
        // (by mustache starter)
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
