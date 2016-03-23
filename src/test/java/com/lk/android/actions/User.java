package com.lk.android.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.lk.android.pages.Address;
import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidBaseCase;
import com.lk.android.utils.AndroidDriverPlus;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class User {

	private AndroidDriverPlus ad = AndroidBaseCase.getDriver();

	public User(AndroidDriverPlus ad) {
		this.ad = ad;
	}

	WebElement userlink = null;

	// 修改个人信息：编辑昵称
	@SuppressWarnings("unchecked")
	public void editNickName() {

		boolean result = false;
		userlink = ad.findElement(By.name("个人信息"));
		userlink.click();
		List<WebElement> list = ad.findElementsById("com.scienvo.app.troadon:id/value");// 修改个人信息页面的，列表字段

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String currentTime = sdf.format(new Date());

		// 点个人信息后，进入个人信息编辑界面，包含：昵称
		if (ad.findElement(By.name("昵称")).isDisplayed()) {

			WebElement nick = list.get(0);// 昵称
			nick.clear();
			nick.sendKeys("我是一个粉刷匠" + currentTime);// 昵称后面加一个时间，确保不会出现因为重复昵称，导致保存失败；

			ad.findElement(By.id("com.scienvo.app.troadon:id/btn_nav_right")).click();// 点击：保存
			// 保存成功后，会回到个人页面，个人页面包含昵称
			if (ad.findElement(By.id("com.scienvo.app.troadon:id/user_name")).isDisplayed()) {
				result = true;
			}
		}

		Assert.assertTrue(result);
	}

	// 修改个人信息：编辑邮箱
	@SuppressWarnings("unchecked")
	public void editMail() {
		boolean result = false;
		userlink = ad.findElement(By.name("个人信息"));
		userlink.click();
		List<WebElement> list = ad.findElementsById("com.scienvo.app.troadon:id/value");// 修改个人信息页面的，列表字段
		// 点个人信息后，进入个人信息编辑界面，包含：邮箱
		if (ad.findElement(By.name("邮箱")).isDisplayed()) {
			WebElement mail = list.get(1);// 邮箱
			mail.clear();
			mail.sendKeys("5555@6666.com");
			ad.findElement(By.id("com.scienvo.app.troadon:id/btn_nav_right")).click();// 点击：保存

			// 保存成功后，会回到个人页面，个人页面包含昵称
			if (ad.findElement(By.id("com.scienvo.app.troadon:id/user_name")).isDisplayed()) {
				result = true;
			}
		}

		Assert.assertTrue(result);
	}

	// 修改个人信息：出生日期
	@SuppressWarnings("unchecked")
	public void editBirthday() {

		userlink = ad.findElement(By.name("个人信息"));
		userlink.click();
		List<WebElement> list = ad.findElementsById("com.scienvo.app.troadon:id/value");// 修改个人信息页面的，列表字段
		if (ad.findElement(By.name("出生日期")).isDisplayed()) {
			WebElement birthday = list.get(3);
			birthday.click();
			
			WebElement year = ad.findElement(By.id("com.scienvo.app.troadon:id/year"));
			System.out.println("year.size:"+year.getSize());
			

		}

	}

	/**
	 * 修改收货地址（登陆状态下）
	 * 
	 * @param name
	 * @param postcode
	 * @param phone
	 * @param address
	 */
	public void editAddress(String name, String phone, String postcode, String provience,String city,String distinct,String address) {
		
		boolean result = false;
		
		// 有昵称，表示登陆状态下
		if (ad.findElement(By.id(Personal.nickName)).isDisplayed()) {

			Address.getElementByName("收货地址").click();// 点击：收货地址
			Address.getElementById(Address.ADD).click();// 点击：添加
			
			Address.getElementById(Address.RECIPIENT).sendKeys(name);// 输入：收货人
			Address.getElementById(Address.PHONE).sendKeys(phone);// 输入：电话
			Address.getElementById(Address.POSTCODE).sendKeys(postcode);// 输入：邮编
			// 点击：省份，滑动到 安徽省，并点击
			Address.getElementById(Address.PROVINCE).click();
			ad.scrollToExact(provience);
			Address.getElementByName(provience).click();
			
			// 点击：市，滑动到 六安市，并点击
			Address.getElementById(Address.CITY).click();
			ad.scrollToExact(city);
			Address.getElementByName(city).click();

			// 点击：金安区
			Address.getElementById(Address.DISTINCT).click();
			Address.getElementByName(distinct).click();
			// 输入：详细地址 保存
			Address.getElementById(Address.ADDRESS).sendKeys(address);
			Address.getElementById(Address.SAVE_BTN).click();
			
			//保存成功，会回到地址管理列表页
			if (Address.getElementByName("地址管理").isDisplayed()) {

				@SuppressWarnings("unchecked")
				//获得所有的联系人组件container，并取第一个，因为添加后，会排在第一个
				List<WebElement> container = ad.findElements(By.id("com.scienvo.app.troadon:id/container"));
				List<WebElement> value = container.get(0).findElements(By.id("com.scienvo.app.troadon:id/value"));
				
				String phone_list = value.get(0).getText().toString().substring(4);//列表中的：手机号
				String postcode_list = value.get(1).getText().toString();//列表中的：邮编
				String address_list = value.get(2).getText().toString();//列表中的：地址
				
				String address_new = provience + city + distinct + address;//列表中，拼接出来的地址
				
				System.out.println("address_list:"+address_list);
				System.out.println("postcodeNum:"+postcode_list);
				System.out.println("phone_list:"+phone_list);
				System.out.println("address_new:"+address_new);
				
				if (postcode_list.equals(postcode) && phone_list.equals(phone) && address_list.equals(address_new)) {
					result = true;
				}
				
//				List<WebElement> names = ad.findElements(By.id("com.scienvo.app.troadon:id/v21_addresslist_user_name"));//收件人的名称

/*				for (WebElement webElement : names) {
					
					if (webElement.getText().equals(name)) {

						WebElement menu = Address.getElementById(Address.MENU);
						int x = menu.getLocation().getX();
						int y = menu.getLocation().getY();
						System.out.println("Location:" + menu.getLocation());
						menu.click();

						TouchAction action = new TouchAction(ad).press(x, y + 160).release();
						ad.performTouchAction(action);
						
						ad.findElement(By.name("确定")).click();
						
						break;
					} else {
						System.out.println("找不到！！！");
					}
				}*/
				
				ad.pressKeyCode(AndroidKeyCode.BACK);

			}
		}

		Assert.assertTrue(result);

	}

}
