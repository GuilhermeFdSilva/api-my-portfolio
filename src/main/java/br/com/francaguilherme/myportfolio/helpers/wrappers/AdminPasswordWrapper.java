package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.entities.Admin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 *     Classe utilizada para receber dois objetos {@link Admin} das requisições.
 * </p>
 *
 * <p>
 *     Ela também utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 *     gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 *     {@link #oldAdmin} repersenta o administrador antigo, para validação. {@link #newAdmin} representa o administrador
 *     com as novas credenciais.
 * </p>
 *
 * @see Admin
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 */
@Getter
@Setter
@NoArgsConstructor
public class AdminPasswordWrapper {
    @NotNull(message = "O campo 'oldAdmin' não pode ser nulo")
    private Admin oldAdmin;

    @NotNull(message = "O campo 'newAdmin' não pode ser nulo")
    private Admin newAdmin;
}
