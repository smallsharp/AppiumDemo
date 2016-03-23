package com.lk.android.testSuite;

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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lk.android.common.Activity;
import com.lk.android.pages.HomePage;


@SuppressWarnings("rawtypes")
public class Demo {
	
	private static AndroidDriver driver;
	
	@Parameters({"udid","port"})
	
	@BeforeClass
	public void setup(String udid,String port) throws MalformedURLException {

		System.out.println("****测试开始，正在初始化数据****");
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps/Troad");
		File app = new File(appDir, "atroad3.5.0.apk");
		if (!app.exists()) {
			System.out.println("apk不存在");
		}

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("unicodeKeyboard", "True");
		dc.setCapability("resetKeyboard", "True");
		dc.setCapability("platformName", "Android");
//		dc.setCapability("deviceName", Device_Name);
		dc.setCapability("udid", udid);
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("app", app.getAbsolutePath());
		System.out.println("udid:"+udid+"  "+"Port:"+port);
		driver = new AndroidDriver(new URL("http://127.0.0.1:"+port+"/wd/hub"), dc);// AppiumServer的地址
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("初始化完成...");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void a_welcome() throws InterruptedException {
		System.out.println("开始执行：a_welcome()");
		Thread.sleep(5000);
		if (driver.currentActivity().equals(Activity.SplashActivity) && driver.findElementById(HomePage.logo).isDisplayed()) {
			swipeToLeft();
		}
		driver.findElement(By.name("立即开启")).click();
		driver.findElement(By.name("我")).click();
		driver.findElement(By.name("我")).click();
		Thread.sleep(2000);
	}
	
	
	
	
	
	public static void swipeToLeft() throws InterruptedException {

		System.out.println("执行：swipeToLeft");
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		for (int i = 1; i <= 3; i++) {
			driver.swipe(width * 9 / 10, height / 2, width / 20, height / 2, 500);
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