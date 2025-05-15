package org.github.rekrutacja.post.impl;

import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.github.rekrutacja.post.Post;
import org.github.rekrutacja.post.PostFacade;
import org.github.rekrutacja.post.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostFacadeImpl implements PostFacade {

  private static final Logger logger = LoggerFactory.getLogger(PostFacadeImpl.class);
  private final PostService postService;

  public PostFacadeImpl(PostService postService) {
    this.postService = postService;
  }

  @Override
  public Post[] getAllPosts() {
    logger.info("Pobieranie wszystkich postów");
    Post[] posts = postService.getAllPosts();
    logger.info("Pobrano {} postów", posts.length);
    return posts;
  }

  @Override
  public void getAllPostsAndWriteToFiles() throws FileSaveException {
    logger.info("Rozpoczęcie operacji pobierania i zapisywania postów do plików");
    try {
      Post[] posts = postService.getAllPosts();
      logger.info("Pobrano {} postów, rozpoczęcie zapisywania do plików", posts.length);
      postService.writeDataToFiles(posts);
      logger.info("Pomyślnie zapisano wszystkie posty do plików");
    } catch (Exception e) {
      logger.error("Wystąpił błąd podczas pobierania lub zapisywania postów", e);
      throw e;
    }
  }
}