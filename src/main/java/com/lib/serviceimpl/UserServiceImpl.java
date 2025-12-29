package com.lib.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lib.entity.User;
import com.lib.enums.UserType;
import com.lib.exception.InvalidOperationException;
import com.lib.repository.UserRepository;
import com.lib.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;

	@Override
	public User createUser(User user) {

		user.setUserType(UserType.GUEST); // default
		user.setMembershipStartTime(null);
		user.setMembershipEndTime(null);

		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {

		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new InvalidOperationException("User not found"));

		existingUser.setName(user.getName());
		existingUser.setEmail(user.getEmail());

		return userRepository.save(existingUser);
	}

	@Override
	public boolean deleteUser(Long userId) {
		if (!userRepository.existsById(userId)) {
	        return false;
	    }
		userRepository.deleteById(userId);
		return true;
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElseThrow(() -> new InvalidOperationException("User not found"));
	}

	@Override
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

}
