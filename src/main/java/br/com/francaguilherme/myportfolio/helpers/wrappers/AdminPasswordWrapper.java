package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.Admin;

public class AdminPasswordWrapper {
    private Admin oldPassword;
    private Admin newPassword;

    public Admin getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(Admin oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Admin getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(Admin newPassword) {
        this.newPassword = newPassword;
    }
}
