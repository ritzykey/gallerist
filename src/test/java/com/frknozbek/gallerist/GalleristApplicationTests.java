package com.frknozbek.gallerist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.frknozbek.service.IAccountService;

@SpringBootTest
class GalleristApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetAccountServiceFromCtx() {
		IAccountService accountService = applicationContext.getBean(IAccountService.class);

		System.out.println(accountService.sayHello());
	}

}
