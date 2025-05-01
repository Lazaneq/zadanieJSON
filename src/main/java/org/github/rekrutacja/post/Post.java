package org.github.rekrutacja.post;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {

private int userId;
@JsonProperty("id")
private int postId;
private String title;
private String body;

  public Post() {
  }

  public Post(final int userId, final int postId, final String title, final String body) {
    this.userId = userId;
    this.postId = postId;
    this.title = title;
    this.body = body;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(final int userId) {
    this.userId = userId;
  }

  public int getPostId() {
    return postId;
  }

  public void setPostId(final int postId) {
    this.postId = postId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(final String body) {
    this.body = body;
  }
}
