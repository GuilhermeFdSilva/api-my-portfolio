package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

/**
 * Representa um projeto
 * Esta classe armazena informações do projeto, incluindo:
 * - Título do projeto (title);
 * - Link para imagem do projeto (image);
 * - Descrição do projeto (description);
 * - ID da linguagem de programação principal utilizada no projeto (main_language) {@link Language};
 * - Ferramentas utilizadas, linguagens (tools);
 * - Link para o GitHub (link_gh);
 * - Link para a página do projeto (link_pg);
 * - Readme do projeto (readme);
 * - Quantidade de Likes do projeto (likes).
 */
@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, insertable = false)
    private Long id;
    @Column(nullable = false)
    private String title;

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

    /**
     * Obtém o ID do projeto
     *
     * @return O ID do projeto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do projeto.
     *
     * @param id O novo ID do projeto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o título do projeto.
     *
     * @return O título do projeto.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Define o título do projeto.
     *
     * @param title O novo título do projeto.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtém link para a imagem do projeto.
     * @return O link para imagem do projeto.
     */
    public String getImage() {
        return image;
    }

    /**
     * Define o link para imagem do projeto.
     * @param image O novo link para imagem do projeto.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Obtéma a descrição do projeto.
     * @return A descrição do projeto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição do projeto.
     * @param description A nova descrição do projeto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtém a linguagem de programação principal do projeto.
     * @return A linguagem de programação principal.
     */
    public Language getMain_language() {
        return main_language;
    }

    /**
     * Define a linguagem de programação principal do projeto.
     * @param main_language A nova linguagem de programação principal.
     */
    public void setMain_language(Language main_language) {
        this.main_language = main_language;
    }

    /**
     * Obtém a lista de ferramentas utilizadas no projeto.
     * @return A lista de ferramentas utilizadas.
     */
    public String[] getTools() {
        return tools;
    }

    /**
     * Define a lista de ferramentas utilizadas no projeto.
     * @param tools A nova lista de ferramentas.
     */
    public void setTools(String[] tools) {
        this.tools = tools;
    }

    /**
     * Obtém o link para o GitHub do projeto.
     * @return O link para o GitHub do projeto.
     */
    public String getLink_gh() {
        return link_gh;
    }

    /**
     * Define o link para o GitHub do projeto.
     * @param link_gh O novo link para o GitHub do projeto.
     */
    public void setLink_gh(String link_gh) {
        this.link_gh = link_gh;
    }

    /**
     * Obtém o link para a página do projeto.
     * @return O link para a página do projeto.
     */
    public String getLink_pg() {
        return link_pg;
    }

    /**
     * Define o link para a página do projeto.
     * @param link_pg O novo link para a página do projeto.
     */
    public void setLink_pg(String link_pg) {
        this.link_pg = link_pg;
    }

    /**
     * Obtém o Readme do projeto.
     * @return O Readme do projeto.
     */
    public byte[] getReadme() {
        return readme;
    }

    /**
     * Define o Readme do projeto.
     * @param readme O novo Readme do projeto.
     */
    public void setReadme(byte[] readme) {
        this.readme = readme;
    }

    /**
     * Obtém a quantidade de likes do projeto.
     * @return A quantidade de likes do projeto.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Define a quantidade de likes do projeto
     * @param likes A nova quantidade de likes do projeto.
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Incrementa o número de likes para este projeto.
     */
    public void likeProject() {
        likes++;
    }

    /**
     * Decrementa o número de likes deste projeto, caso seja maior que 0.
     */
    public void dislikeProject() {
        if (likes > 0) {
            likes--;
        }
    }
}
