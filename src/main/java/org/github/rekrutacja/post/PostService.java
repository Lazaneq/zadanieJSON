package org.github.rekrutacja.post;

import org.github.rekrutacja.post.Exceptions.FileSaveException;

public interface PostService {
  Post[] getAllPosts();
  void writeDataToFiles(Post[] posts) throws FileSaveException;
}
