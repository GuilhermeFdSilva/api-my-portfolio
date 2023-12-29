package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.Admin;

/**
 * Wrapper para encapsular senhas administrativas.
 * Fornece tanto a senha antiga quanto a nova dirante a operação de alterar senha.
 */
public class AdminPasswordWrapper {
    private Admin oldPassword;
    private Admin newPassword;

    /**
     * Obtém a senha antiga
     * @return A senha antiga.
     */
    public String getOldPassword() {
        return oldPassword.getPassword();
    }

    /**
     * Define a senha antiga.
     * @param oldPassword A senha antiga a ser definida.
     */
    public void setOldPassword(Admin oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * Obtém a senha nova.
     * @return A senha nova.
     */
    public String getNewPassword() {
        return newPassword.getPassword();
    }

    /**
     * Define a nova senha.
     * @param newPassword A nova senha a ser definida.
     */
    public void setNewPassword(Admin newPassword) {
        this.newPassword = newPassword;
    }
}
