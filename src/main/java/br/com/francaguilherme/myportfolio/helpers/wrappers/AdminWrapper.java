package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.Admin;

/**
 * Wrapper que encapsula um objeto {@link Admin} e um objeto do tipo T.
 * Frequentemente utilizado para fazer verificações de acesso a funções administrativas.
 *
 * @param <T> Tipo do objeto a ser encapsulado.
 */
public class AdminWrapper<T> {
    private Admin admin;
    private T type;

    /**
     * Construtor que inicializa o wrapper com um objeto {@link Admin} e um objeto do tipo T.
     * @param admin Objeto {@link Admin} a ser encapsulado.
     * @param type  Objeto do tipo T a ser encapsulado.
     */
    public AdminWrapper(Admin admin, T type) {
        this.admin = admin;
        this.type = type;
    }

    /**
     * Obtém o objeto {@link Admin} encapsulado.
     * @return O objeto Admin.
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * Define o objeto {@link Admin} a ser encapsulado.
     * @param admin O objeto Admin a ser definido.
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * Obtém o objeto do tipo T encapsulado.
     * @return O objeto do tipo T.
     */
    public T getType() {
        return type;
    }

    /**
     * Define o objeto do tipo T a ser encapsulado.
     * @param type O objeto do tipo T a ser definido.
     */
    public void setType(T type) {
        this.type = type;
    }
}
