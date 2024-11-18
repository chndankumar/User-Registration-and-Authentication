package com.edtech.userRegistration.Mapper;

import com.edtech.userRegistration.Dto.UserDto;
import com.edtech.userRegistration.Entity.Users;

public class UserMapper {

    public static Users mapToUser(UserDto userDto, Users user){
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto mapToUserDto(Users user, UserDto userDto){
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
