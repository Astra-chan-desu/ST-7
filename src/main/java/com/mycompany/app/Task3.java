package com.mycompany.app;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Task3 {
    public static void Task3void() {
          WebDriver driver = new ChromeDriver();
        try {
            // 2. Открываем JSON‐API
            String url = "https://api.open-meteo.com/v1/forecast"
                       + "?latitude=56&longitude=44"
                       + "&hourly=temperature_2m,rain"
                       + "&forecast_days=1"
                       + "&timezone=Europe%2FMoscow";
            driver.get(url);

            // 3. Получаем текст body (это чистый JSON)
            WebElement body = driver.findElement(By.tagName("body"));
            String jsonText = body.getText();

            // 4. Парсим JSON через json-simple
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonText);
            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            // 5. Печатаем таблицу
            System.out.printf("%-3s %-20s %-12s %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            System.out.println("-----------------------------------------------------------");
            int n = times.size();
            for (int i = 0; i < n; i++) {
                String dt   = times.get(i).toString();
                String temp = temps.get(i).toString();
                String rain = rains.get(i).toString();
                System.out.printf("%-3d %-20s %-12s %-10s%n", i + 1, dt, temp, rain);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
