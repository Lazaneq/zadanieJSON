package org.github.rekrutacja.post.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.github.rekrutacja.post.FileSaveConfiguration;
import org.github.rekrutacja.post.Post;
import org.github.rekrutacja.post.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

  private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
  private final WebClient webClient;
  private final ObjectMapper objectMapper;
  private final FileSaveConfiguration fileSaveConfiguration;
  
  public PostServiceImpl(final WebClient.Builder webClientBuilder, final ObjectMapper objectMapper,
      final FileSaveConfiguration fileSaveConfiguration) {
    this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    this.objectMapper = objectMapper;
    this.fileSaveConfiguration = fileSaveConfiguration;
  }

  @Override
  public Post[] getAllPosts() {
    logger.debug("Wykonywanie zapytania HTTP do zewnętrznego API po posty");
    return webClient.get()
        .uri("/posts")
        .retrieve()
        .bodyToMono(Post[].class)
        .doOnError(error -> logger.error("Wystąpił błąd podczas pobierania postów z API", error))
        .block();
  }

  @Override
  public void writeDataToFiles(final Post[] posts) throws FileSaveException {
    Path directory = Path.of(fileSaveConfiguration.getOutputFolder());
    logger.debug("Rozpoczęcie zapisywania {} postów do katalogu: {}", posts.length, directory);
    
    try {
      if(Files.notExists(directory)) {
        logger.info("Tworzenie katalogu wyjściowego: {}", directory);
        Files.createDirectory(directory);
      }
      
      for (Post post : posts) {
        Path filePath = directory.resolve(post.getPostId() + fileSaveConfiguration.getExtension());
        logger.debug("Zapisywanie postu o ID {} do pliku: {}", post.getPostId(), filePath);
        objectMapper.writeValue(filePath.toFile(), post);
      }
    } catch (IOException e) {
      logger.error("Błąd podczas zapisywania plików", e);
      throw new FileSaveException(e);
    }
  }
}