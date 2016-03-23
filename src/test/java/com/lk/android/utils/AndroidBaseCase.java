package com.lk.android.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AndroidBaseCase {

	protected static AndroidDriverPlus ad;
	protected ExcelReader reader;
	protected ApplicationContext ac = null;

	public static AndroidDriverPlus getDriver() {
		return ad;
	}

	@SuppressWarnings("rawtypes")
	
	@BeforeClass
	public void setup() {
		
		IPlatForm<?> platform = new AndroidPlatform(); // 实现接口的对象
		AndroidDriverPlus.driver = (AndroidDriverPlus) platform.CreateDriver();//初始化driver
		ad = AndroidDriverPlus.driver;
		reader = new ExcelReader(System.getProperty("user.dir")+"/assets/data.xls");//初始化测试数据
		if (ac == null) {
			ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
	}

	@AfterClass
	public void teardown() {
		System.out.println("测试完成，正在清理数据...");
		ad.quit();
	}

}
