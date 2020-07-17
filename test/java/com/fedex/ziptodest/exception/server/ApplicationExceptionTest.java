/*package com.fedex.ziptodest.exception.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class ApplicationExceptionTest {

	ApplicationException ApplicationException = new ApplicationException();

	Throwable t = new Throwable();

	ApplicationException ApplicationException1 = new ApplicationException("code", "msg");

	ApplicationException ApplicationException2 = new ApplicationException("msg", t);

	ApplicationException ApplicationException3 = new ApplicationException(null);

	@Test

	public void ApplicationExceptionTest_positive() {

		ApplicationException.setCode("code");

		assertEquals("code", ApplicationException.getCode());

	}

	@Test

	public void ApplicationExceptionTest_negitive() {

		ApplicationException.setCode("code");

		assertNotEquals("codes", ApplicationException.getCode());

	}

}
*/