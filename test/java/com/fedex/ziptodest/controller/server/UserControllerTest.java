package com.fedex.ziptodest.controller.server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.model.CurrentUser;
import com.fedex.ziptodest.model.User;
import com.fedex.ziptodest.utils.server.ZDConstants;

@RunWith(SpringRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private CurrentUser currentuser;

	@Test
	public void testGetUserForSortationRole() {
		Mockito.doReturn(ZDConstants.SORTATION).when(currentuser).getRole();
		User user = userController.getUser();
		assertEquals(true, user.getSortation());
	}

	@Test
	public void testGetUserForMeasurementRole() {
		Mockito.doReturn(ZDConstants.VISION_MANAGEMENT_ROLE).when(currentuser).getRole();
		User user = userController.getUser();
		assertEquals(true, user.getVisionManagement());
	}

	/*@Test
	public void testGetUserForLinehaulRole() {
		Mockito.doReturn(ZipToDestConstants.PGH_LINEHAUL_ROLE).when(currentuser).getRole();
		User user = userController.getUser();
		assertEquals(true, user.getPghlhl());
	}*/

	@Test
	public void testGetUserForDefaultRole() {
		Mockito.doReturn("DefaultRole").when(currentuser).getRole();
		User user = userController.getUser();
		assertEquals(true, user.getRegUser());
	}
	

}
