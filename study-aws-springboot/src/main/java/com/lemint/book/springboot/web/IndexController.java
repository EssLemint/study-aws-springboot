package com.lemint.book.springboot.web;

import com.lemint.book.springboot.config.auth.LoginUser;
import com.lemint.book.springboot.config.auth.dto.SessionUser;
import com.lemint.book.springboot.service.posts.PostsService;
import com.lemint.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.method.HandlerTypePredicate;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
  private final PostsService postsService;
  private final HttpSession httpSession;

  @GetMapping("/")
  public String index(Model model, @LoginUser SessionUser user) {
    model.addAttribute("posts", postsService.findAllDesc());
    /**
     * 앞서 작성된 CustomerOAuthUserService에서 로그인 성공 시 세션에서 SessionUser를 저장하게 구현
     * 로그인을 성공하면 httpSession.getAttribute("user") 을가져와서 SessionUser에 담는다.
     *
     * 1. SessionUser user = (SessionUser) httpSession.getAttribute("user");
     * 선언으로 진행 -> 자주 쓰일 예정  -> 리팩토링 : 메소드 인자로 세션 값을 바로 받을 수 있게 변경
     * config.auth @LoginUser
     *
     * SessionUser user = (SessionUser) httpSession.getAttribute("user"); 기존 코드
     * */



    if(user != null) {
      model.addAttribute("userName", user.getName());
    }
    return "index";
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save";
  }

  @GetMapping("/posts/update/{id}")
  public String postsUpdate(@PathVariable Long id, Model model){
    PostsResponseDto dto = postsService.findById(id);
    model.addAttribute("post", dto);

    return "posts-update";
  }
}
