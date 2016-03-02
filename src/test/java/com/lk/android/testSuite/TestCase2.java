package com.lk.android.testSuite;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.lk.android.actions.SearchProduct;
import com.lk.android.pages.Personal;
import com.lk.android.utils.AndroidInit;
import com.lk.android.utils.ExcelDataReader;

import io.appium.java_client.android.AndroidKeyCode;

public class TestCase2 extends AndroidInit{
	
	
	@BeforeClass
	public void init() throws MalformedURLException{
		System.out.println("开始测试：TestCase2");
	}
	
	
	@Test(description="商品id:19264")
	public void test_order_001() throws InterruptedException {
		SearchProduct sp = new SearchProduct();
		sp.search("19264");
		
		boolean result = sp.searchResult();
		if (result == true) {
			sp.gottaBuy();
			sp.submitOrder();
			// sp.pay();
		}
	}
	
	@Test
	public void test_order_002() throws InterruptedException {
		System.out.println("开始执行：test_order002()");
		// 切换到首页
		ad.startActivity("com.scienvo.app.troadon", "com.scienvo.app.troadon.MainActivity");
		SearchProduct sp = new SearchProduct();
		sp.search("19289");
		boolean result = sp.searchResult();
		if (result == true) {
			sp.gottaBuy();
			sp.chooseDate();
			sp.submitOrder();
			// sp.pay();
		}
	}
	
	
	
}
