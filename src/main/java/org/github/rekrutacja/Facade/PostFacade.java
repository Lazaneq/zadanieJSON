package org.github.rekrutacja.Facade;

import org.github.rekrutacja.Exceptions.FileSaveException;
import org.github.rekrutacja.Model.Post;
import org.github.rekrutacja.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostFacade {

  @Autowired
  private PostService postService;

  public Post[] getAllPosts(){
    return postService.getAllPosts();
  }

  public void getAllPostsAndWriteToFile() throws FileSaveException {
      Post[] posts = postService.getAllPosts();
      postService.writeDataToFile(posts);

  }

}
