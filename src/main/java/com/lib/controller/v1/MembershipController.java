package com.lib.controller.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lib.entity.User;
import com.lib.service.MembershipService;
import com.lib.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class MembershipController {
	private final UserService userService;
	private final MembershipService membershipService;

	@PostMapping("/start")
	public ResponseEntity<String> startMembership(@RequestParam Long userId, @RequestParam int months) {

		User user = userService.getUserById(userId);
		membershipService.startMembership(user, months);

		return ResponseEntity.ok("Membership started.");
	}

	@PostMapping("/renew")
	public ResponseEntity<String> renewMembership(@RequestParam Long userId, @RequestParam int months) {

		User user = userService.getUserById(userId);
		membershipService.renewMembership(user, months);

		return ResponseEntity.ok("Membership renewed.");
	}

	@GetMapping("/remaining-days/{userId}")
	public ResponseEntity<Long> getRemainingDays(@PathVariable Long userId) {

		User user = userService.getUserById(userId);
		long days = membershipService.getRemainingMembershipDays(user);

		return ResponseEntity.ok(days);
	}

	@PostMapping("/expire/{userId}")
	public ResponseEntity<String> expireMembership(@PathVariable Long userId) {

		User user = userService.getUserById(userId);
		membershipService.expireMembership(user);

		return ResponseEntity.ok("Membership expired !!");
	}
}
