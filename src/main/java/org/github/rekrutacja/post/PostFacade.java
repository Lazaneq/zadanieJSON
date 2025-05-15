package org.github.rekrutacja.post;

import org.github.rekrutacja.post.Exceptions.FileSaveException;

public interface PostFacade {

  Post[] getAllPosts();
  void getAllPostsAndWriteToFiles() throws FileSaveException;

}
