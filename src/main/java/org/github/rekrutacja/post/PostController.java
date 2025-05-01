package org.github.rekrutacja.post;

import org.github.rekrutacja.post.Exceptions.FileSaveException;
import org.github.rekrutacja.post.impl.PostFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired
  private PostFacadeImpl postFacadeImpl;

  @Description("Endpoint zwracający listę wszystkich postów")
  @GetMapping
  public ResponseEntity<Post[]> getPosts() {
    Post[] posts = postFacadeImpl.getAllPosts();

    return ResponseEntity.ok(posts);
  }

  @Description("Endpoint /posts/write zapisujący posty do dedykowanych plików po id_posta")
  @GetMapping("/write")
  public void getPostsAndWriteToFiles() throws FileSaveException {
    postFacadeImpl.getAllPostsAndWriteToFiles();
  }
}
