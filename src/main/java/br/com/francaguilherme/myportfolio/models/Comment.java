package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

@Entity(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, insertable = false, updatable = false)
    private Long id;
    @Column(length = 30, nullable = false)
    private String nick;
    @Column(nullable = false)
    private String message;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int up;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int down;
    @ManyToOne
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
