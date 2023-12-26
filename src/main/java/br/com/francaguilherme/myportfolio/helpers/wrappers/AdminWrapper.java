package br.com.francaguilherme.myportfolio.helpers.wrappers;

import br.com.francaguilherme.myportfolio.models.Admin;

public class AdminWrapper<T> {
    private Admin admin;
    private T type;

    public AdminWrapper(Admin admin, T type) {
        this.admin = admin;
        this.type = type;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
