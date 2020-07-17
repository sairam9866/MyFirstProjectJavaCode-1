package com.fedex.ziptodest.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.model.User;

/**
 * 
 * @author 3786636
 *
 */

@RunWith(SpringRunner.class)
public class UserTest {

	@Test
	public void testUserModel() {
		User user = new User("1", "me", "12345", "Me@mymail", "important", true, false, false);

		user.setSortation(false);
		assertFalse(user.getSortation());
		System.out.println("Sortation:" + user.getSortation());
		System.out.println("user:" + user);
		
		
		user.setVisionManagement(true);
		assertTrue(user.getVisionManagement());
		
		user.setRegUser(true);
		assertTrue(user.getRegUser());
		
		user.setDeptNum("123456");
		assertEquals("123456", user.getDeptNum());
		
		user.setGivenName("Test Mphasis");
		assertEquals("Test Mphasis", user.getGivenName());
		
		user.setMail("Test@fedex.com");
		assertEquals("Test@fedex.com", user.getMail());
		
		user.setRole("Sort");
		assertEquals("Sort", user.getRole());
		
		user.setUserId("12");
		assertEquals("12", user.getUserId());
		
		
	}
}
