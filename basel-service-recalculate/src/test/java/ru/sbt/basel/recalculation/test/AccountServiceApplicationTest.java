package ru.sbt.basel.recalculation.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ru.sbt.basel.recalculation.RecalculationApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RecalculationApplication.class)
@WebAppConfiguration
public class AccountServiceApplicationTest {

	@Test
	public void contextLoads() {

	}

}
