package Erp.database;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Test;

public class jdbcConnTest {
	
	@Test
	public void testGetConnection() {
		System.out.printf("%s()%n","testGetConnection");
		Connection con =jdbcConn.getConnection();
		System.out.println("con > " + con);
		Assert.assertNotNull(con);
	}

}
