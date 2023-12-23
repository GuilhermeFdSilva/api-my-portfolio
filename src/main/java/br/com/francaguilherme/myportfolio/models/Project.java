package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, insertable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String name_gh;
    @Column(nullable = false)
    private String image;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    @ManyToOne
    private Language main_language;
    private String[] tools;
    @Column(nullable = false)
    private String link_gh;
    private String link_pg;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private byte[] readme;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName_gh() {
        return name_gh;
    }

    public void setName_gh(String name_gh) {
        this.name_gh = name_gh;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getMain_language() {
        return main_language;
    }

    public void setMain_language(Language main_language) {
        this.main_language = main_language;
    }

    public String[] getTools() {
        return tools;
    }

    public void setTools(String[] tools) {
        this.tools = tools;
    }

    public String getLink_gh() {
        return link_gh;
    }

    public void setLink_gh(String link_gh) {
        this.link_gh = link_gh;
    }

    public String getLink_pg() {
        return link_pg;
    }

    public void setLink_pg(String link_pg) {
        this.link_pg = link_pg;
    }

    public byte[] getReadme() {
        return readme;
    }

    public void setReadme(byte[] readme) {
        this.readme = readme;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
