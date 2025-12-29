package com.lib.serviceimpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.lib.entity.User;
import com.lib.enums.UserType;
import com.lib.exception.InvalidOperationException;
import com.lib.repository.UserRepository;
import com.lib.service.MembershipService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

	private final UserRepository userRepository;

	@Override
	public boolean isMembershipValid(User user) {
		return user.getMembershipEndTime() != null && user.getMembershipEndTime().isAfter(LocalDateTime.now());
	}

	@Override
	public void startMembership(User user, int months) {
		if (months <= 0) {
			throw new InvalidOperationException("Membership months must be greater than zero");
		}
		LocalDateTime now = LocalDateTime.now();
		if (user.getMembershipEndTime() != null && user.getMembershipEndTime().isAfter(now)) {

			user.setMembershipEndTime(user.getMembershipEndTime().plusMonths(months));

		} else {
			// New membership
			user.setMembershipStartTime(now);
			user.setMembershipEndTime(now.plusMonths(months));
		}
		user.setUserType(UserType.VALID);
		userRepository.save(user);
	}

	@Override
	public void renewMembership(User user, int months) {

	    if (months <= 0) {
	        throw new InvalidOperationException("Renewal months must be greater than zero");
	    }

	    LocalDateTime now = LocalDateTime.now();

	    if (user.getMembershipEndTime() == null ||
	        user.getMembershipEndTime().isBefore(now)) {

	        throw new InvalidOperationException("Cannot renew expired membership. Start new membership instead.");
	    }

	    user.setMembershipEndTime(user.getMembershipEndTime().plusMonths(months));

	    userRepository.save(user);
	}
	
	@Override
	public long getRemainingMembershipDays(User user) {
		 if (user.getMembershipEndTime() == null) {
		        return 0;
		    }

		    LocalDateTime now = LocalDateTime.now();

		    if (user.getMembershipEndTime().isAfter(now)) {
		        return ChronoUnit.DAYS.between(now, user.getMembershipEndTime());
		    }

		    return 0;
	}

	@Override
	public void expireMembership(User user) {

	    user.setMembershipEndTime(LocalDateTime.now());
	    user.setUserType(UserType.GUEST);

	    userRepository.save(user);

	}

}
