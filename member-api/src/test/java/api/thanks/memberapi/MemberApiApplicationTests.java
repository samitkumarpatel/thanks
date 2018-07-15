package api.thanks.memberapi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Base64;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class MemberApiApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void decode(){
		byte[] b = Base64.getDecoder().decode("Y2hhbmdlbWU=");
		String expectedString = new String(b);
		Assert.assertEquals("changeme",expectedString);
	}
}
