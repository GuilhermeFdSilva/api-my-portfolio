package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.entities.Admin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Classe utilizada para receber dois objetos, um {@link Admin} e outro de tipo {@link #type}, que é definido
 *     durante a inicialização desta classe.
 * </p>
 *
 * <p>
 *     Ela também utiliza as anotações {@link Getter}, {@link Setter}, {@link NoArgsConstructor} e
 *     {@link AllArgsConstructor} do {@link lombok} para gerar automaticamente os getters e setters de todos os
 *     atributos da classe, um construtor sem argumentos e um construtor com todos os argumentos da classe.
 * </p>
 *
 * <p>
 *     {@link #admin} representa o usuario {@link Admin} utilizado para validação de credenciais. {@link #type}
 *     representa o objeto que sera utilizado na operação (definido na inicialização do objeto).
 * </p>
 *
 * @param <T> Tipo do objeto a ser encapsulado.
 *
 * @see Admin
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see AllArgsConstructor
 * @see lombok
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminWrapper<T> {
    @NotNull(message = "O campo 'admin' não pode ser nulo")
    private Admin admin;

    @NotNull(message = "O campo 'type' não pode ser nulo")
    private T type;
}
