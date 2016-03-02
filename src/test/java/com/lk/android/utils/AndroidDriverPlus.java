package com.lk.android.utils;

import java.net.URL;
import java.util.Set;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

@SuppressWarnings("rawtypes")
public class AndroidDriverPlus extends AndroidDriver {

	private AndroidDriverPlus driver;
	
	public void setDriver(AndroidDriverPlus driver){
		this.driver = driver;
	}
	
	

	public AndroidDriverPlus(URL remoteAddress, Capabilities desiredCapabilities) {
		super(remoteAddress, desiredCapabilities);
	}

	@Override
	public WebDriver context(String name) {
		// TODO Auto-generated method stub
		return super.context(name);
	}

	@Override
	public String getContext() {
		// TODO Auto-generated method stub
		return super.getContext();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getContextHandles() {
		// TODO Auto-generated method stub
		return super.getContextHandles();
	}

	// 向左滑动
	public void swipeToLeft(int times) throws InterruptedException {
		
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		for (int i = 1; i <= times; i++) {
			driver.swipe(width * 9 / 10, height / 2, width / 10, height / 2, 500);
			System.out.println("向左滑动了" + i + "次");
			Thread.sleep(1000);
		}
	}

	// webview页面切换时，需要加这个方法
	public String pageShift() {
		String currentHandle = driver.getWindowHandle();
		Set<String> allHandles = driver.getWindowHandles();

		for (String s : allHandles) {
			if (!s.equals(currentHandle)) {
				driver.switchTo().window(s);
			}
		}
		return currentHandle;
	}

	public void clearText(WebElement element) {

		String text = element.getText();
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);// 123

		for (int i = 0; i < text.length(); i++) {
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_DEL);// 67
		}
	}

}
