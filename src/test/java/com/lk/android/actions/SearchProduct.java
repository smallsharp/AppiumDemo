package com.lk.android.actions;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lk.android.utils.AndroidInit;

import io.appium.java_client.android.AndroidKeyCode;

public class SearchProduct extends AndroidInit{
	

	private String str;

	public void search(String str) {
		this.str = str;// 下面需要用到
		ad.findElement(By.name("首页")).click();
		ad.findElement(By.id("com.scienvo.app.troadon:id/tv_search")).click();// 点击：首页搜索框
		ad.findElement(By.id("com.scienvo.app.troadon:id/set_edittext")).sendKeys(str);
		ad.pressKeyCode(AndroidKeyCode.ENTER); // 这里的ad没法初始化，必要要放到Main中才能用
	}

	//判断是否找到商品
	public Boolean searchResult() {
		boolean result = false;
		try {
			WebElement element = ad.findElement(By.id("com.scienvo.app.troadon:id/title"));
			if (element.isDisplayed()) {
				result = true;
				System.out.println(str + "的搜索结果:" + result);
				element.click();
			}
		} catch (Exception e) {
			System.out.println("没有找到：" + str + "相关的商品");
			e.printStackTrace();
			return false;
		}
		return result;
	}

	public void gottaBuy() throws InterruptedException {

		Thread.sleep(500);
		Set<String> contextNames = ad.getContextHandles();
		for (String context : contextNames) {
			System.out.println(context);

			if (context.contains("WEBVIEW")) {
				ad.context("WEBVIEW");
				System.out.println("将当前页面切换成：WebView");
			}
		}

		try {
			String xpath = "//*[@data-productid=" + str + "]";// String xpath =
																// "//*[@data-productid=23979]";
			ad.findElementByXPath(xpath).click();
			System.out.println("点击：立即购买");
			ad.pageShift();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void submitOrder() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println(ad.getTitle());// 获取当前页面的title

		try {
			WebElement addr = ad.findElementByXPath("//*[@id=\"diliverAddress\"]");// 输入地址
			addr.sendKeys("地址：明珠路1018号");
			Thread.sleep(500);
		} catch (Exception e) {
			System.out.println("找不到这个地址啊,或者不需要这个地址");
			e.printStackTrace();
		}

		// 获取旅客人数,所有出行人
		WebElement num = ad.findElementByXPath("//*[@id=\"mPriceList\"]/div/div/div[1]/div[2]");
		System.out.println("得到的是：" + num.getText());

		int n = Integer.parseInt(num.getText());

		for (int i = 0; i < n; i++) {
			// 获取旅客,根据上面旅客人数，有几个添加几个
			@SuppressWarnings("unchecked")
			List<WebElement> personNum = ad
					.findElementsByXPath("//*[@class=\"per-list-title fl traveller-item-title\"]");
			System.out.println("personNum.size():" + personNum.size());
			personNum.get(i).click();

			Thread.sleep(5000);
			@SuppressWarnings("unchecked")
			List<WebElement> choosePerson = ad.findElementsByXPath("//*[@class=\"list-content-item\"]");
			System.out.println("choosePerson.size():" + choosePerson.size());
			choosePerson.get(i).click();

			// 点击保存联系人
			ad.findElementByXPath("//*[@id=\"travellerSaveBtn\"]").click();
			Thread.sleep(500);

		}

		ad.context("NATIVE_APP");// 切换回到native_app模式
		System.out.println("将当前页面切换成：Native_App");

		ad.findElement(By.name("订单确认")).click();// 点击：订单确认
		System.out.println("点击：订单确认");

		WebElement submitOrder = ad.findElement(By.name("提交订单"));
		submitOrder.click();// 点击：提交订单
		System.out.println("点击：提交订单");
	}

	// 直售需要先选择日期
	public void chooseDate() throws InterruptedException {
		Thread.sleep(5000);
		try {
			WebElement date = ad.findElementByXPath("//*[@class=\"c-date click-able brb c-select-day\"]");
			date.click();

		} catch (Exception e) {
			e.printStackTrace();
		}

		ad.findElementByXPath("//*[@id=\"nextStep\"]").click();// 点击：下一步

	}

	public void pay() throws InterruptedException {
		  
		  ad.findElement(By.name("银联支付")).click();
		  System.out.println("点击：银联支付");
		  
		  ad.findElement(By.name("去付款")).click();
		  System.out.println("点击：去付款");
		  
		  // 进入支付环境，点击：下一步 Thread.sleep(5000);
		  ad.findElement(By.name("下一步")).click();
		  System.out.println("点击：下一步");
		  
		  ad.findElement(By.name("免费获取")).click();
		  System.out.println("点击：免费获取");
		  
		  WebElement code = ad.findElement(By.name("短信验证码"));
		  code.sendKeys("123456");// 输入：验证码
		  
		  ad.findElement(By.name("确认付款")).click();
		  System.out.println("点击：确认付款");
		  
		  ad.findElement(By.name("返回商户")).click();
		  System.out.println("点击：返回商户");
		 }



}
