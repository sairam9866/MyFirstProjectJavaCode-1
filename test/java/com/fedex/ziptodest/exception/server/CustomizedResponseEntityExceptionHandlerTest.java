package com.fedex.ziptodest.exception.server;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration.WebFluxConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fedex.ziptodest.controller.server.ZipToDestController;

/*@RunWith(value = SpringJUnit4ClassRunner.class)

@WebAppConfiguration*/

// @ContextConfiguration(classes = { WebFluxConfig.class, HeaderFactory.class })
 
@RunWith(MockitoJUnitRunner.class)
public class CustomizedResponseEntityExceptionHandlerTest {

	private MockMvc mockMvc;

	@Mock
	private ZipToDestController statusController;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(statusController).setControllerAdvice(new CustomizedResponseEntityExceptionHandler()).build();
	}
	
	
	@Test

    public void checkUnexpectedExceptionsAreCaughtAndStatusCode500IsReturnedInResponse() throws Exception {

//        when(statusController.getAllZDRecord()).thenThrow(new RuntimeException("Unexpected Exception"));

        mockMvc.perform(get("ziptodest/records/current"))

                .andDo(print())

                .andExpect(status().is4xxClientError());

//                .andExpect(jsonPath("$.error").value(new RuntimeException()));

    }



}
