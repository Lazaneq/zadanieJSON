package org.github.rekrutacja.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.github.rekrutacja.Exceptions.FileSaveException;
import org.github.rekrutacja.Model.Post;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostService {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  @Value("${file.extension}")
  private String fileExtension;

  public PostService(final RestTemplate restTemplate, final ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  public Post[] getAllPosts(){
    return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
  }

  public void writeDataToFile(Post[] posts) throws FileSaveException {
    Path directory = Path.of("postsData");
    try{
      if(Files.notExists(directory)){
        Files.createDirectory(directory);
      }
      for (Post post : posts) {
        objectMapper.writeValue(directory.resolve(post.getPostId() + String.valueOf(fileExtension)).toFile(), post);
      }
    }catch (IOException e){
      throw new FileSaveException(e);
      }
    }
  }

