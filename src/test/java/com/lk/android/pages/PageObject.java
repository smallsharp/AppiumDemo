package com.lk.android.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.lk.android.utils.AndroidBaseCase;
import com.lk.android.utils.AndroidDriverPlus;

public class PageObject {
	
	protected static AndroidDriverPlus ad = AndroidBaseCase.getDriver();
		
 	
	public static WebElement getElementById(String id) {
		
		WebElement element = ad.findElement(By.id(id));
		return element;
		
	}
	
	public static WebElement getElementByName(String name) {
		
		WebElement element = ad.findElement(By.name(name));
		return element;
		
	}
	


}
