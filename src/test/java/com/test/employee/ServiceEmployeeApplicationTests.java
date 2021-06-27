package com.test.employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(value = "/application.properties")
class ServiceEmployeeApplicationTests {
	@Test
	public void contextLoads() {
		// este método usa el properties indicado en TestPropertySource y así se prueba
		// el caso de CORS desactivado.
	}

	@Test
	public void main() {
		// este método NO usa el properties indicado en TestPropertySource y así se prueba
		// el caso de CORS activado.
		com.app.employee.ServiceEmployeeApplication.main(new String[] {});
	}

}
