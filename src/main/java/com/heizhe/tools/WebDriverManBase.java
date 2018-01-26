package com.heizhe.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

public abstract class WebDriverManBase {
	private static WebDriver driver;
	static{
		 System.out.println("执行静态方法快");
		 PhantomJsDriverManager.getInstance().setup();
		 System.out.println("执行静态方法快done");
//		 ChromeDriverManager.getInstance().setup();
//		 driver = new ChromeDriver();
	}
	
	protected static WebDriver getDriver() {
		if(driver==null){
			driver = new PhantomJSDriver();
		}
        return driver;
    }
	/**
	 * 退出访问端
	 */
	public static void teardown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
