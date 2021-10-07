import java.time.LocalDateTime;

public class UserDaoImpl implements UserDao{

    private int id = 0;
    private String userName;
    private String uuid;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String email;
    private Boolean verified;

    public UserDaoImpl(int id,
                       String userName,
                       String uuid,
                       LocalDateTime created,
                       LocalDateTime modified,
                       String email) {
        this.id = id;
        this.userName = userName;
        this.uuid = uuid;
        this.created = created;
        this.modified = modified;
        this.email = email;
    }

    public UserDaoImpl(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public LocalDateTime getModified() {
        return modified;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public Boolean getVerified() {
        return verified;
    }

    @Override
    public void setVerified(Boolean value) {
        verified = value;
    }
}
