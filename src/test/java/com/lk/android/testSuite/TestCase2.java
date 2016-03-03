package com.lk.android.testSuite;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.lk.android.actions.Login;
import com.lk.android.actions.User;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.ExcelReader;

/**
 * @author Administrator
 * @简介：登录、登出
 */

public class TestCase2 extends AndroidInit{
	
	public ExcelReader excel;
	
	@BeforeClass
	public void init() throws MalformedURLException{
		super.initDriver();
		excel = new ExcelReader(System.getProperty("user.dir")+"/assets/data.xls");
	}
	
	@Test(description="欢迎页面")
	public void test_welcome() throws InterruptedException{
		System.out.println("开始执行：test_welcome()");
		welcome.run();

	}
	
	
	@Test(description="登录")
	public void test_loginRight() throws InterruptedException{
		
		System.out.println("开始执行：test_loginRight()");
		boolean result = false;
		
		ad.findElement(By.name("我")).click();
		ad.findElement(By.name("点击登录")).click();
		
		Login login = new Login(ad);
		login.run(excel.getValue("login","account","tc2"), excel.getValue("login","password","tc2"));
		
		Thread.sleep(4000);
		System.out.println("currentActivity:"+ad.currentActivity());
		if(ad.currentActivity().equals(".MainActivity")){
			result = true;
		}
		Assert.assertTrue(result);
	}
	
	@Test(description="修改昵称")
	public void test_editNickName() throws InterruptedException{
		User user = new User(ad);
		user.editNickName();
	}
	
	@Test(description="修改邮箱")
	public void test_editMail() throws InterruptedException{
		User user = new User(ad);
		user.editMail();;
	}
	
	
	
	@AfterClass
	public void quit(){
		System.out.println("测试结束，正在清理数据...");
		ad.quit();
	}
	
}
