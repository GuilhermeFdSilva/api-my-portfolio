package br.com.francaguilherme.myportfolio.models;

import jakarta.persistence.*;

/**
 * <p>
 *     Classe que representa o administrador do sistema. Esta classe utiliza a anotação {@link Entity} para ser mapeada
 *     para o banco de dados  em uma tabela de mesmo nome (admin).
 * </p>
 *
 * <p>
 *     Ela também utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 *     gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 *     Os atributos de um administrador são:
 *     <ul>
 *         <li>{@link #id}: Identificador único do objeto;</li>
 *         <li>{@link #password}: Senha administrativa do sistema.</li>
 *     </ul>
 * </p>
 *
 * @see Entity
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 */
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    /**
     * Obtém o ID do administrador.
     * @return O ID do administrador.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do administrador.
     * @param id O novo ID do administrador.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém a senha do administrador.
     * @return A senha do administrador.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define a senha do administrador.
     * @param password A nova senha do administrador.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
