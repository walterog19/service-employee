package com.app.employee;

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
     
    }

    @Test
    public void main() {
      
        com.app.employee.ServiceEmployeeApplication.main(new String[] {});
    }

}