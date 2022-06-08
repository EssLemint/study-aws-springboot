package com.lemint.book.springboot.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  @DisplayName("hello 리턴")
  public void hello() throws Exception {
    String hello = "hello";

    mvc.perform(get("/hello"))
        .andExpect(status().isOk())
        .andExpect(content().string(hello));
  }

  @Test
  public void helloDto_리턴된다() throws Exception{
    String name = "hello";
    int amount = 1000;

    mvc.perform(get("/hello/dto")
                  .param("name", name)
                  .param("amount", String.valueOf(amount)))
        .andExpect(status().isOk());

  }
}