package org.github.rekrutacja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.github.rekrutacja.Model.Post;
import org.github.rekrutacja.Service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class PostServiceTest {

  private PostService postService;
  private RestTemplate restTemplate;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    restTemplate = mock(RestTemplate.class);
    objectMapper = new ObjectMapper();
    postService = new PostService(restTemplate, objectMapper);
  }

  @Test
  public void shouldGetAllPosts(){
    Post[] posts = { new Post(1, 1, "Title", "Body")};
    when(restTemplate.getForObject(anyString(), eq(Post[].class))).thenReturn(posts);

    Post[] result = postService.getAllPosts();

    assertNotNull(result);
    assertEquals(1, result.length);
    assertEquals("Title", result[0].getTitle());
  }

  @Test
  public void shouldSavePostInJsonFormat() throws IOException {
    Post[] post = { new Post(1, 1, "Title", "Body")};

    postService.writeDataToFile(post);

    assertTrue(Files.exists(Path.of("postsData/" + post[0].getPostId() + ".json")));
  }

}
