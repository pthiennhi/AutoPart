package group5.com.prm_autopartssale.models;

public class ResponseMessageCreateCustomer {
  private String message;
  private String status;

  public ResponseMessageCreateCustomer() {
  }

  public ResponseMessageCreateCustomer(String message, String status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
