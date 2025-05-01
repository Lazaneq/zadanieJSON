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
import org.github.rekrutacja.post.FileSaveConfiguration;
import org.github.rekrutacja.post.Post;
import org.github.rekrutacja.post.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.web.client.RestTemplate;

public class PostServiceImplTest {

  private PostServiceImpl postServiceImpl;
  private RestTemplate restTemplate;
  private ObjectMapper objectMapper;
  private FileSaveConfiguration fileSaveConfiguration;

  @BeforeEach
  void setUp() {
    restTemplate = mock(RestTemplate.class);
    fileSaveConfiguration = mock(FileSaveConfiguration.class);
    objectMapper = new ObjectMapper();
    postServiceImpl = new PostServiceImpl(restTemplate, objectMapper, fileSaveConfiguration);
  }

  @Test
  public void shouldGetAllPosts(){
    Post[] posts = { new Post(1, 1, "Title", "Body")};
    when(restTemplate.getForObject(anyString(), eq(Post[].class))).thenReturn(posts);

    Post[] result = postServiceImpl.getAllPosts();

    assertNotNull(result);
    assertEquals(1, result.length);
    assertEquals("Title", result[0].getTitle());
  }

  @Test
  public void shouldSavePostInExactFormat(@TempDir Path tempDir) throws IOException {
    Post[] post = { new Post(1, 1, "Title", "Body")};

    when(fileSaveConfiguration.getOutputFolder()).thenReturn(tempDir.toString());
    when(fileSaveConfiguration.getExtension()).thenReturn(".json");

    postServiceImpl.writeDataToFiles(post);

    Path expectedFile = tempDir.resolve(post[0].getPostId() + ".json");

    assertTrue(Files.exists(expectedFile));
  }

}
