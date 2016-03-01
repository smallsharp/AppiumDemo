package com.lk.android.test;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Demo {

	@SuppressWarnings("rawtypes")
	private static AndroidDriver driver;
	
	
	@BeforeClass
	public void setup() throws MalformedURLException {

		System.out.println("****测试开始，正在初始化数据****");
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps/Troad");
		File app = new File(appDir, "atroad3.5.0.apk");
		if (!app.exists()) {
			System.out.println("apk不存在");
		}

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "41ad3630");
		capabilities.setCapability("platformVersion", "5.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);// AppiumServer的地址
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("初始化完成...");
	}

	
	@Test
	public void a_welcome() throws InterruptedException {
		System.out.println("开始执行：a_welcome()");
		Thread.sleep(5000);
		swipeToLeft();
		driver.findElement(By.name("立即开启")).click();
		driver.findElement(By.name("签到")).click();
		driver.findElement(By.name("签到")).click();
		Thread.sleep(2000);
	}
	
	
	
	
	
	public static void swipeToLeft() throws InterruptedException {

		System.out.println("执行：swipeToLeft");
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		for (int i = 1; i <= 3; i++) {
			driver.swipe(width * 9 / 10, height / 2, width / 10, height / 2, 500);
			System.out.println("向左滑动了" + i + "次");
			Thread.sleep(1000);
		}
	}
	
	
	@AfterClass
	public void teardown() {
		System.out.println("****测试结束，正在清理数据****");
		driver.quit();
	}
	
}