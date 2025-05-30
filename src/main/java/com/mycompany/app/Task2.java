package com.mycompany.app;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Task2 {
    public static void Task2void(){
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://api.ipify.org/?format=json");

            String json = driver.findElement(By.tagName("pre")).getText();

            String ip = json.replaceAll(".*\"ip\"\\s*:\\s*\"([^\"]+)\".*", "$1");

            System.out.println("IP of this device is:"+ ip);
        } finally {
            driver.quit();
        }
    }
}
