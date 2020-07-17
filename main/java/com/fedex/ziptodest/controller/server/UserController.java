package com.fedex.ziptodest.controller.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fedex.ziptodest.model.CurrentUser;
import com.fedex.ziptodest.model.User;
import com.fedex.ziptodest.utils.server.ZDConstants;

@RestController
@RequestMapping("/")
public class UserController {
	@Autowired
	private CurrentUser currentuser;

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser() {
		Boolean sortation = false;
		Boolean vm = false;
		Boolean regUser = false;

		if (currentuser.getRole().equalsIgnoreCase(ZDConstants.SORTATION)) {
			sortation = true;
		} else if (currentuser.getRole().equalsIgnoreCase(ZDConstants.VISION_MANAGEMENT_ROLE)) {
			vm = true;
		} else {
			regUser = true;
		}

		return new User(currentuser.getUserId(), currentuser.getGivenName(), currentuser.getDeptNum(),
				currentuser.getMail(), currentuser.getRole(), sortation, vm, regUser);

	}

}
