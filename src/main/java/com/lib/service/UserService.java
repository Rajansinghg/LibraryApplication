package com.lib.service;

import java.util.List;

import com.lib.entity.User;

public interface UserService {

	User createUser(User user);

	User updateUser(User user);

	boolean deleteUser(Long userId);

	User getUserById(Long userId);

	List<User> getAllUsers();
}
