package com.fedex.ZDServer.cucumber;


import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fedex.ziptodest.ZDServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZDServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
public abstract class SpringBootBaseIT {

	  // @LocalServerPort
	   //protected int port = 8080;

}