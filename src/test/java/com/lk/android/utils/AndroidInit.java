package com.lk.android.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AndroidInit {
	
	protected static AndroidDriverPlus ad;
	public void initDriver() throws MalformedURLException {

		System.out.println("正在初始化数据...");
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
		dc.setCapability("deviceName", "41ad3630");
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("app", app.getAbsolutePath());
		ad = new AndroidDriverPlus(new URL("http://127.0.0.1:4723/wd/hub"), dc);// AppiumServer的地址
		ad.setDriver(ad);
		
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		System.out.println("初始化完成...");
	}
	
}
