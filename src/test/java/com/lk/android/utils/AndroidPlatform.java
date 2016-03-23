package com.lk.android.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * 
 * Android平台的初始化方法:AndroidPlatform
 * @author Administrator
 * @param <T>
 */

public class AndroidPlatform<T> implements IPlatForm<T>{

	@SuppressWarnings("unchecked")
	public T CreateDriver() {
		System.out.println("正在初始化数据,请稍后...");
		File classpathRoot = new File(System.getProperty("user.dir"));//获取工程目录
		File appDir = new File(classpathRoot,"apps");//获取文件目录
		File app = new File(appDir, "TRoadOn-3.6.0d.apk");//获取文件
		
		if(!app.exists()){
			System.out.println("指定的apk文件不存在");
		}
		
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.BROWSER_NAME, "");
		dc.setCapability("unicodeKeyboard", "True");//隐藏键盘，支持中文输入
		dc.setCapability("resetKeyboard", "True");	
		dc.setCapability("platformName", "Android");
		dc.setCapability("deviceName", "41ad3630");//adb devices
		dc.setCapability("platformVersion", "5.0");
		dc.setCapability("app", app.getAbsolutePath());
		//这两个属性和绝对路径指定一个即可
		dc.setCapability("app-package", "com.scienvo.app.troadon");
		dc.setCapability("app-activity", "com.scienvo.app.module.SplashActivity");	
		
		//******************************************************************************************
		
		AndroidDriverPlus driver =null;
		try {
			driver = new AndroidDriverPlus(new URL("http://127.0.0.1:4723/wd/hub"), dc); //AppiumServer的地址
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("初始化完成，准备执行测试用例...");

		return (T) driver;
	}
}
