package com.heizhe.tools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.PhantomJsDriverManager;

public class PhantomJsTools extends WebDriverManBase{
	
	
	static{
		 PhantomJsDriverManager.getInstance().setup();
	}

public static Document getSouceCodeByPhantomJs1(String url){
		System.setProperty("phantomjs.binary.path","D:/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/bin/phantomjs.exe");// set phantomjs exe path  
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();  
		desiredCapabilities.setCapability("loadImages",false);  
		PhantomJSDriver driver = new PhantomJSDriver(desiredCapabilities);  
		driver.get(url);  
		String source = driver.getPageSource();
		driver.close();
		driver.quit();
		Document doc = Jsoup.parse(source);
		return doc;
	}

/**
 * 用phantomJs来获取动态页面
 * @param url
 * @return
 */
public static Document getSouceCodeByPhantomJs(String url){
	System.out.println("url: "+url);
	 
//	WebDriverWait wait = new WebDriverWait(driver, 30); // 30 seconds of timeout
//	 wait.until(ExpectedConditions.elementToBeClickable(searchButton));
	WebDriver driver = getDriver();
	/**
	 *不知道为什么，线程要延迟一些才不会抓到空数据，可能是上面的getDriver没有那么快打开phatomjs.exe这个文件吧
	 *但还是有遗漏的，只能再开个job处理头像为空的数据了
	 */
	try {Thread.sleep (500);} catch (InterruptedException ie){}	
	
	driver.get(url);
	String source = driver.getPageSource();
	
	Document doc = Jsoup.parse(source);
	return doc;
}

}
