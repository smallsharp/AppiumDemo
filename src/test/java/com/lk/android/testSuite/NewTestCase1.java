package com.lk.android.testSuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.lk.android.actions.Login;
import com.lk.android.actions.Welcome;
import com.lk.android.utils.AndroidBaseCase;
import io.appium.java_client.android.AndroidKeyCode;


public class NewTestCase1 extends AndroidBaseCase{
	
//	private static Logger logger = Logger.getLogger(NewTestCase1.class);
	
	Login login = null;

	@Test
	public void test_welcome() {
//		Welcome welcome = new Welcome(ad);
		Welcome welcome = (Welcome) ac.getBean("welcome");//需要在applicationContext中添加bean标签
		try {
			welcome.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test(description="输入：正确的用户名、错误的密码")
	public void test_loginWrong() {
		ad.findElement(By.name("我")).click();
		
		login = (Login) ac.getBean("login");
		reader.getSheet("login");
		login.loginFailed(reader.getValue("account","tc1"), reader.getValue("password","tc1"));
		
		ad.pressKeyCode(AndroidKeyCode.BACK);
	}
	
	@Test(description="输入：正确的用户名、正确的密码")
	public void test_loginRight() {
		ad.findElement(By.name("我")).click();
		
		login = (Login) ac.getBean("login");
		reader.getSheet("login");
		login.loginSuccess(reader.getValue("account","tc2"), reader.getValue("password","tc2"));
		
	}
	
	@Test(description="退出登录")
	public void test_loginOut() throws InterruptedException{
		
		login.loginOut();
		
	}
}
