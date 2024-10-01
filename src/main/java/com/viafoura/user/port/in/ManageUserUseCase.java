package com.viafoura.user.port.in;

import com.viafoura.user.adapter.out.api.dto.UserData;
import com.viafoura.user.domain.User;
import com.viafoura.user.port.in.web.dto.UserCreateDTO;
import com.viafoura.user.port.in.web.dto.UserUpdateRequestDTO;

import java.util.List;

public interface ManageUserUseCase {

    User createUser(UserCreateDTO user);
    User updateUser(Long id, UserUpdateRequestDTO user);
    void deleteUser(Long id);
    List<UserData> listUsers();
    UserData findById(Long id);

}
