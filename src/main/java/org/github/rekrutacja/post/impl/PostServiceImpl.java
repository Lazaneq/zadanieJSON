package org.github.rekrutacja.post.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.github.rekrutacja.post.FileSaveConfiguration;
import org.github.rekrutacja.post.Post;
import org.github.rekrutacja.post.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostServiceImpl implements PostService {

  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;
  private final FileSaveConfiguration fileSaveConfiguration;

  private final static String posts_url = "https://jsonplaceholder.typicode.com/posts";

  public PostServiceImpl(final RestTemplate restTemplate, final ObjectMapper objectMapper,
      final FileSaveConfiguration fileSaveConfiguration) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.fileSaveConfiguration = fileSaveConfiguration;
  }

  @Override
  public Post[] getAllPosts(){
    return restTemplate.getForObject(posts_url, Post[].class);
  }

  @Override
  public void writeDataToFiles(final Post[] posts) throws FileSaveException {
    Path directory = Path.of(fileSaveConfiguration.getOutputFolder());
    try{
      if(Files.notExists(directory)){
        Files.createDirectory(directory);
      }
      for (Post post : posts) {
        objectMapper.writeValue(directory.resolve(post.getPostId() + fileSaveConfiguration.getExtension()).toFile(), post);
      }
    }catch (IOException e){
      throw new FileSaveException(e);
      }
    }

  }

