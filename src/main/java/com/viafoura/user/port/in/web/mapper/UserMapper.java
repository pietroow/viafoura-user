package com.viafoura.user.port.in.web.mapper;

import com.viafoura.user.adapter.out.api.dto.UserData;
import com.viafoura.user.domain.User;
import com.viafoura.user.port.in.web.dto.UserCreateDTO;
import com.viafoura.user.port.in.web.dto.UserUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User userRequestDTOToUser(UserCreateDTO dto);

    void updateEntityFromDto(UserUpdateRequestDTO dto, @MappingTarget User entity);

    @Mapping(source = "avatarUrl", target = "avatar")
    UserData entityToUserData(User user, String avatarUrl);
}