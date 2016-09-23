package dev_comp;

import java.sql.Timestamp;

/**
 * Created by a.kutakov on 22.09.2016.
 *
 */
public class Joke {
  private long id;
  private long category;
  private Timestamp created;
  private String title;
  private String content;
  private String content_type;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getCategory() {
    return category;
  }

  public void setCategory(long category) {
    this.category = category;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreate(Timestamp created) {
    this.created = created;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getContent_type() {
    return content_type;
  }

  public void setContent_type(String content_type) {
    this.content_type = content_type;
  }
}
