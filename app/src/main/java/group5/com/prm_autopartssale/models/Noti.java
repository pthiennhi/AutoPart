package group5.com.prm_autopartssale.models;

public class Noti {
  private String title;
  private String content;

  public Noti() {
  }

  public Noti(String title, String content) {
    this.title = title;
    this.content = content;
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
}
