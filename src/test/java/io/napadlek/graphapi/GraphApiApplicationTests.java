package io.napadlek.graphapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	public void contextLoads() {
		// given
		String testedPath = "/country/city/name";
		// when
		String restResult = restTemplate.getForEntity(testedPath, String.class).getBody();
		// then
		assertThat(restResult).isEqualTo("countrycityname");
	}

}
