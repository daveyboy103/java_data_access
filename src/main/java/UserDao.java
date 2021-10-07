import java.time.LocalDateTime;

public interface UserDao {
    int getId();
    String getUserName();
    String getUuid();
    LocalDateTime getCreated();
    LocalDateTime getModified();
    String getEmail();
    Boolean getVerified();
    void setVerified(Boolean value);
}

