package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fedex.ziptodest.model.CurrentUser;

/**
 * 
 * @author 3786636
 *
 */

public class CurrentUserTest {
	CurrentUser currentUser = new CurrentUser();

	@Before
	public void addRecords() {
		currentUser.setUserId("01");
		currentUser.setGivenName("User1 Fedex");
		currentUser.setMail("user1@fedex.com");
		currentUser.setDeptNum("1234");
		currentUser.setRole("sortation");
	}

	@Test
	public void testCurrentUser() {
		assertEquals("01", currentUser.getUserId());
		assertEquals("User1 Fedex", currentUser.getGivenName());
		assertEquals("user1@fedex.com", currentUser.getMail());
		assertEquals("1234", currentUser.getDeptNum());
		assertEquals("sortation", currentUser.getRole());
	}
}
