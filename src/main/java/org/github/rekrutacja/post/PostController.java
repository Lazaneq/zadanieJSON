package org.github.rekrutacja.post;

import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
  private static final Logger logger = LoggerFactory.getLogger(PostController.class);
  private final PostFacade postFacade;

  public PostController(final PostFacade postFacade) {
    this.postFacade = postFacade;
  }

  /**
   * Endpoint zwracający listę wszystkich postów
   */
  @GetMapping
  public ResponseEntity<Post[]> getPosts() {
    logger.info("Otrzymano żądanie pobrania wszystkich postów");
    Post[] posts = postFacade.getAllPosts();
    logger.info("Zwracam {} postów", posts.length);
    return ResponseEntity.ok(posts);
  }

  /**
   * Endpoint /posts/write zapisujący posty do dedykowanych plików po id_posta
   */
  @GetMapping("/write")
  public ResponseEntity<String> getPostsAndWriteToFiles() throws FileSaveException {
    logger.info("Otrzymano żądanie zapisania postów do plików");
    postFacade.getAllPostsAndWriteToFiles();
    logger.info("Posty zostały pomyślnie zapisane do plików");
    return ResponseEntity.ok("Posty zostały pomyślnie zapisane do plików");
  }
}