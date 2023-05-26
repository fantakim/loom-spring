package com.scumm.loom.controller;

import com.scumm.loom.dto.user.CreateUserDto;
import com.scumm.loom.dto.user.UpdateUserDto;
import com.scumm.loom.dto.user.UserDto;
import com.scumm.loom.dto.common.PageRequest;
import com.scumm.loom.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

    @PostMapping()
    public UserDto createUser(@RequestBody @Valid CreateUserDto dto) {
        var user = userService.create(dto);
        return mapper.map(user, UserDto.class);
    }

    @PutMapping(value="/{id}")
    public UserDto updateUser(@PathVariable("id") long id, @RequestBody @Valid UpdateUserDto dto) {
        var user = userService.update(id, dto);
        return mapper.map(user, UserDto.class);
    }

    @DeleteMapping(value="/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
    }

    @GetMapping(value="/{id}")
    public UserDto getUser(@PathVariable("id") long id){
        var user = userService.find(id);
        return mapper.map(user, UserDto.class);
    }

    @GetMapping()
    public Page<UserDto> getAllUsers(PageRequest pageRequest){
        var users = userService.findAll(pageRequest.of());

        return users.map(user -> mapper.map(user, UserDto.class));
    }

    @GetMapping("/search")
    public Page<UserDto> searchUsers(String keyword, PageRequest pageRequest) {
        var users = userService.search(keyword, pageRequest.of());

        return users.map(user -> mapper.map(user, UserDto.class));
    }
}
