package com.lk.android.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.lk.android.actions.User;
import com.lk.android.actions.Welcome;


public class AndroidInit {
	
	protected static AndroidDriverPlus ad;
	protected static Welcome welcome;
	protected static User user;
	
	public void initDriver() throws MalformedURLException {

		System.out.println("正在初始化数据......");
		
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "TRoadOn-3.6.0d.apk");
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
//		System.out.println("Device_ID:"+Device_ID+"  "+"Port:"+Port);
		ad = new AndroidDriverPlus(new URL("http://127.0.0.1:4723/wd/hub"), dc);// AppiumServer的地址
		ad.setDriver(ad);
		
//		welcome = new Welcome(ad);
		user = new User(ad);
		
		ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("初始化完成...");
	}
	
}
