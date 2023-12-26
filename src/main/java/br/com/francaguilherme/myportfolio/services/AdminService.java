package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidPasswordException;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    public boolean validatePassword(String password) throws Exception {
        Admin admin = repository.findById(1L).orElse(null);

        if (admin == null) {
            throw new Exception();
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, admin.getPassword());
    }

    public Admin setPassword(String oldPassword, String newPassword) {
        try {
            if (validatePassword(oldPassword)) {
                Admin mainAdmin = repository.findById(1L).orElse(null);

                if (mainAdmin == null) {
                    throw new Exception();
                }

                mainAdmin.setPassword(new BCryptPasswordEncoder().encode(newPassword));
                return repository.save(mainAdmin);
            }
        } catch (Exception e) {
            throw new InvalidPasswordException();
        }
        return null;
    }
}
