package com.lk.android.testSuite;

import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lk.android.actions.Login;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.ExcelReader;

/**
 * @author Administrator
 * @简介：登录、登出
 */

public class OldCase1 extends AndroidInit{
	
	private ExcelReader excel;
	private static Logger logger;
	
	@BeforeClass
	public void init() throws MalformedURLException{
		super.initDriver();//在父类中初始化driver
		excel = new ExcelReader(System.getProperty("user.dir")+"/assets/data.xls");
		logger = Logger.getLogger(OldCase1.class);
	}
	
	@Test
	public void test_welcome() throws InterruptedException{
		welcome.run();
	}
	
	@Test(description="输入：正确的用户名、错误的密码",dependsOnMethods="test_welcome")
	public void test_loginWrong(){
				
		boolean result = false;
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("点击登录")).click();
		
		Login login = new Login(ad);
		excel.getSheet("login");
		login.loginFailed(excel.getValue("account","tc1"), excel.getValue("password","tc1"));
		
		//错误的账号密码应该登录不成功，如果登录成功，则页面跳转到main
		logger.info("currentActivity:"+ad.currentActivity());
		if(!ad.currentActivity().equals(".MainActivity")){
			result = true;
		}
		Assert.assertTrue(result);
	}
	
	
	@Test(description="输入：正确的用户名、正确的密码",dependsOnMethods="test_welcome")
	public void test_loginRight() throws InterruptedException{
		
		boolean result = false;
		Login login = new Login(ad);
		
		excel.getSheet("login");
		login.loginFailed(excel.getValue("account","tc2"), excel.getValue("password","tc2"));
		
		//如果出现用户昵称，则判断登录成功
		if(ad.findElement(By.id("com.scienvo.app.troadon:id/user_name")).isDisplayed()){
			result = true;
		}
		Assert.assertTrue(result);
	}
	
	@Test(description="退出登录")
	public void test_loginOut() throws InterruptedException{
		
		System.out.println("开始执行：test_loginOut()");
		boolean result = false;
		
		ad.swipeUpUntilFind("设置");
		ad.findElement(By.name("设置")).click();
		ad.findElement(By.name("退出登录")).click();
		ad.findElement(By.name("是")).click();
		
		if(ad.findElement(By.name("点击登录")).isDisplayed()){
			result = true;
		}
		
		Assert.assertTrue(result);
	}
	
	
	@AfterClass
	public void quit(){
		System.out.println("测试结束，正在清理数据...");
		ad.quit();
	}
	
}
