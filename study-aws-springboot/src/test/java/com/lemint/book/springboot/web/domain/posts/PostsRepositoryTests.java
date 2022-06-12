package com.lemint.book.springboot.web.domain.posts;

import com.lemint.book.springboot.domain.posts.Posts;
import com.lemint.book.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTests {

  @Autowired
  PostsRepository postsRepository;

  @After
  public void cleanup(){
    postsRepository.deleteAll();
  }

  @Test
  public void saveAndGet() {

    String title = "title";
    String content = "content";

    postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("author")
        .build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);

  }

  @Test
  public void BaseEntity_등록() {
    LocalDateTime now = LocalDateTime.of(2022,6,12,0,0,0);
    postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

    List<Posts> postsList = postsRepository.findAll();

    Posts posts = postsList.get(0);

    System.out.println(">>>>>>>>> createDate = " + posts.getCreatedDate() +
        ", modifiedDate = " + posts.getModifiedDate());

    assertThat(posts.getCreatedDate()).isAfter(now);
    assertThat(posts.getModifiedDate()).isAfter(now);
  }
}
