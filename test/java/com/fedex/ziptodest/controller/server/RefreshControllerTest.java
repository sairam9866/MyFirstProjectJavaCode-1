package com.fedex.ziptodest.controller.server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RefreshControllerTest {

	@InjectMocks
	RefreshController refreshController;
	
	@Test
	public void testRefreshController(){
		ResponseEntity<String> response = refreshController.refresh("sortx");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Refreshed", response.getBody());
	}
}
