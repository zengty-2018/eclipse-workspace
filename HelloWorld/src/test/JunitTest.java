import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JunitTest {
	HelloWorld helloworld = new HelloWorld();
	
	@Before
	public void setUp() throws Exception {
		helloworld.hello();
	}

	@Test
	public void test() {
		assertEquals(1, 1);
	}

}
