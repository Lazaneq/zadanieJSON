package org.github.rekrutacja.post;

import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.springframework.stereotype.Service;

@Service
public interface PostFacade {

  Post[] getAllPosts();
  void getAllPostsAndWriteToFiles() throws FileSaveException;

}
