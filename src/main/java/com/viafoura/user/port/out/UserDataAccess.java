package com.viafoura.user.port.out;

import com.viafoura.user.domain.User;

import java.util.List;

public interface UserDataAccess {

    User saveUser(User user);
    void deleteUser(Long id);
    User findById(Long id);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
    void deleteAll();
    boolean existsByEmailIdNot(Long id, String email);
}
