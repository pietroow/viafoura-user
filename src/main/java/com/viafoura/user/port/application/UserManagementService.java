package com.viafoura.user.port.application;

import com.viafoura.user.adapter.out.api.ReqresApiClient;
import com.viafoura.user.adapter.out.api.dto.UserData;
import com.viafoura.user.domain.User;
import com.viafoura.user.exception.custom.EmailAlreadyExistsException;
import com.viafoura.user.port.in.ManageUserUseCase;
import com.viafoura.user.port.in.web.dto.UserCreateDTO;
import com.viafoura.user.port.in.web.dto.UserUpdateRequestDTO;
import com.viafoura.user.port.in.web.mapper.UserMapper;
import com.viafoura.user.port.out.UserDataAccess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserManagementService implements ManageUserUseCase {

    private final UserDataAccess userDataAccess;
    private final UserMapper userMapper;
    private final ReqresApiClient reqresApiClient;

    @Override
    public User createUser(UserCreateDTO userDTO) {
        User user = userMapper.userRequestDTOToUser(userDTO);

        if (userDataAccess.existsByEmail(userDTO.email())) {
            throw new EmailAlreadyExistsException("User email already exist");
        }

        return userDataAccess.saveUser(user);
    }

    @Override
    public User updateUser(Long id, UserUpdateRequestDTO userUpdateDTO) {
        User userFound = userDataAccess.findById(id);

        if (userDataAccess.existsByEmailIdNot(id, userUpdateDTO.email())) {
            throw new EmailAlreadyExistsException("User email already in use");
        }


        userMapper.updateEntityFromDto(userUpdateDTO, userFound);
        return userDataAccess.saveUser(userFound);
    }

    @Override
    public void deleteUser(Long id) {
        userDataAccess.deleteUser(id);
    }

    @Override
    public List<UserData> listUsers() {
        return userDataAccess.getAllUsers()
                .stream()
                .map(user -> {
                    String avatarUrl = reqresApiClient.fetchAvatarUrl(user.getId().intValue());
                    return userMapper.entityToUserData(user, avatarUrl);
                }).toList();
    }

    @Override
    public UserData findById(Long id) {
        User user = userDataAccess.findById(id);
        String avatarUrl = reqresApiClient.fetchAvatarUrl(user.getId().intValue());
        return userMapper.entityToUserData(user, avatarUrl);
    }

}
