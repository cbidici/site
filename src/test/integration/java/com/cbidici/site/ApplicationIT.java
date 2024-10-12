package com.cbidici.site;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("integration")
class ApplicationIT {

	@Test
	void contextLoads() {
	}

}
