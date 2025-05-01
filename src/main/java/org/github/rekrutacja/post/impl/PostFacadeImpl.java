package org.github.rekrutacja.post.impl;

import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.github.rekrutacja.post.Post;
import org.github.rekrutacja.post.PostFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostFacadeImpl implements PostFacade {

  @Autowired
  private PostServiceImpl postServiceImpl;

  @Override
  public Post[] getAllPosts(){
    return postServiceImpl.getAllPosts();
  }

  @Override
  public void getAllPostsAndWriteToFiles() throws FileSaveException {
      Post[] posts = postServiceImpl.getAllPosts();
      postServiceImpl.writeDataToFiles(posts);
  }

}
