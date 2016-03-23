package com.lk.android.testSuite;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.lk.android.actions.Login;
import com.lk.android.actions.User;
import com.lk.android.actions.Welcome;
import com.lk.android.utils.AndroidBaseCase;

public class NewTestCase2 extends AndroidBaseCase {

	// private static Logger logger = Logger.getLogger(NewTestCase2.class);

	@Test(description = "欢迎页面")
	public void test_welcome() {

		Welcome welcome = (Welcome) ac.getBean("welcome");
		try {
			welcome.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(description = "登录")
	public void test_loginRight() {
		ad.findElement(By.name("我")).click();
		Login login = new Login(ad);

		reader.getSheet("login");
		login.loginSuccess(reader.getValue("account", "tc2"), reader.getValue("password", "tc2"));
	}

	@Test
	public void test_editAddress() {

		User user = new User(ad);
		reader.getSheet("address");

		user.editAddress(reader.getValue("收件人", "tc1"), reader.getValue("电话", "tc1"), reader.getValue("邮编", "tc1"),"安徽省","六安市","金安区",
				reader.getValue("详细地址", "tc1"));
	}

	@Test(description = "修改昵称")
	public void test_editNickName() {
		User user = new User(ad);
//		user.editNickName();
		user.editBirthday();
	}

	@Test(description = "修改邮箱")
	public void test_editMail() {
		User user = new User(ad);
		user.editMail();
	}

}
