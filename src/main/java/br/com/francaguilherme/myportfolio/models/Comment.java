package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

/**
 * Representa um comentário feito em um Projeto {@link Project}.
 * Esta classe armazena informações do comentário, incluindo:
 *  - Nome de quem comentou (nick);
 *  - Mensagem do comentário (message);
 *  - Votos positivos (up);
 *  - Votos negativos (down);
 *  - ID do projeto comentado (project).
 */
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

    /**
     * Obtém o ID do comentário.
     * @return O ID do comentário.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do comentário.
     * @param id O novo ID do comentário.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do usuário no comentário.
     * @return O nome do usuário.
     */
    public String getNick() {
        return nick;
    }

    /**
     * Define o nome do usuário no comentário.
     * @param nick Novo nome de usuário do comentário.
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Obtém a mensagem do comentário.
     * @return A mensagem do comentário.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define a mensagem do comentário.
     * @param message A nova mensagem do comentário.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Obtém a quantidade de votos positivos.
     * @return A quantidade de votos positivos.
     */
    public int getUp() {
        return up;
    }

    /**
     * Define a quantidade de votos positivos.
     * @param up A nova quantidade de votos positivos.
     */
    public void setUp(int up) {
        this.up = up;
    }

    /**
     * Obtém a quantidade de votos negativos.
     * @return A quantidade de votos negativos.
     */
    public int getDown() {
        return down;
    }

    /**
     * Define a quantidade de votos negativos.
     * @param down A nova quantidade de votos negativos.
     */
    public void setDown(int down) {
        this.down = down;
    }

    /**
     * Obtém o Projeto que foi comentado.
     * @return O Projeto que foi comentado.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Define o Projeto que foi comentado.
     * @param project Projeto comentado.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Incrementa o número de votos positivos (up) para este comentário.
     */
    public void incrementUpVote() {
        up++;
    }

    /**
     * Decrementa o número de votos positivos (up) para este comentário,
     * caso o valor seja maior que 0.
     */
    public void decrementUpVote() {
        if (up > 0){
            up--;
        }
    }

    /**
     * Incrementa o número de votos negativos (down) para este comentário.
     */
    public void incrementDownVote() {
        down++;
    }

    /**
     * Decrementa o número de votos negativos (down) para este comentário,
     * caso o valor seja maior que 0.
     */
    public void decrementDownVote() {
        if (down > 0) {
            down--;
        }
    }
}
