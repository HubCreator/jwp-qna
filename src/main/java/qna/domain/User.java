package qna.domain;

import qna.UnAuthorizedException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    public static final GuestUser GUEST_USER = new GuestUser();
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false, length = 20, unique = true)
    private String userId;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Column(name = "email", length = 50)
    private String email;

    public User(final String userId, final String password, final String name, final String email) {
        this(null, userId, password, name, email);
    }

    public User(final Long id, final String userId, final String password, final String name, final String email) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    protected User() {

    }

    public void update(final User loginUser, final User target) {
        if (!this.matchUserId(loginUser.userId)) {
            throw new UnAuthorizedException();
        }

        if (!this.matchPassword(target.password)) {
            throw new UnAuthorizedException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    private boolean matchUserId(final String userId) {
        return this.userId.equals(userId);
    }

    public boolean matchPassword(final String targetPassword) {
        return this.password.equals(targetPassword);
    }

    public boolean equalsNameAndEmail(final User target) {
        if (Objects.isNull(target)) {
            return false;
        }

        return this.name.equals(target.name) &&
                this.email.equals(target.email);
    }

    public boolean isGuestUser() {
        return false;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id +
                ", userId='" + this.userId + '\'' +
                ", password='" + this.password + '\'' +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                '}';
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}
