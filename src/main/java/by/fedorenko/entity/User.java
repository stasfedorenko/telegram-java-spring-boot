package by.fedorenko.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 4, message = "Password should have atleast 4 characters")
    @Column(name = "password")
    private String password;
    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, message = "First name should have atleast 2 characters")
    @Column(name = "first_name")
    private String firstName;
    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, message = "Last name should have atleast 2 characters")
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "Role should not be null")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @NotNull(message = "Status should not be null")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @NotEmpty(message = "Chat id should not be empty")
    @Pattern(regexp = "^(\\d){1,20}$",message = "Chat id should consists of numbers")
    @Column(name = "chat_id")
    private String chatId;

    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    private Set<Task> tasks = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
