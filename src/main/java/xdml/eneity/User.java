package xdml.eneity;

import java.time.Instant;

public class User {
    Integer id;
    String username;
    String encryptedPassword;
    String avatar;
    Instant createdAt;
    Instant updatedAt;

//    public User(Integer id, String username) {
//        this.id = id;
//        this.username = username;
////        this.avatar="";
////        this.createdAt=Instant.now();
////        this.updatedAt=Instant.now();
//    }

    public User(Integer id, String username, String encryptedPassword) {
        this.id = id;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.avatar ="";
        this.createdAt=Instant.now();
        this.updatedAt=Instant.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
