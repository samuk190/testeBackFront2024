package com.teste.entrevista.staff.service;

import com.teste.entrevista.staff.model.User;

public interface UserService {
    User save(User user);
    boolean existsByUsername(String username);
}
