package com.lib.service;

import com.lib.entity.User;

public interface MembershipService {
	
	boolean isMembershipValid(User user);

	void startMembership(User user, int months);

	void renewMembership(User user, int months);

	long getRemainingMembershipDays(User user);

	void expireMembership(User user);
}
