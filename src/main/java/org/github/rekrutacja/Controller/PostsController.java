package org.github.rekrutacja.Controller;

import org.github.rekrutacja.Exceptions.FileSaveException;
import org.github.rekrutacja.Facade.PostFacade;
import org.github.rekrutacja.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostsController {

  @Autowired
  private PostFacade postFacade;

  @Description("Endpoint zwracający listę wszystkich postów")
  @GetMapping
  public ResponseEntity<Post[]> getPosts() {
    Post[] posts = postFacade.getAllPosts();

    return ResponseEntity.ok(posts);
  }

  @Description("Endpoint /posts/write zapisujący posty do dedykowanych plików po id_posta")
  @GetMapping("/write")
  public void getPostsAndWriteToFiles() throws FileSaveException {
    postFacade.getAllPostsAndWriteToFiles();
  }
}
