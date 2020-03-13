package com.yimrim.dbwetterapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException, JSONException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        while (true) {
            String Ort = "Wasbek";
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + Ort + "&units=metric&appid=ea83cbef0fe44057e30baa8b0741c8b1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            //  System.out.println(content);

            JSONObject jo = new JSONObject(content.toString());
            JSONObject jom = jo.getJSONObject("main");
            double Temperatur = jom.getDouble("temp");
            JSONObject joc = jo.getJSONObject("wind");
            double windms = joc.getDouble("speed");
            double windkm = windms * 3.6;
            System.out.println("Aktuelle Temperatur: " + Temperatur + "°C");
            System.out.println("Aktuelle Windgeschwindigkeit: " + windms + " m/S");

            dbconnection dbcon = new dbconnection();
            dbcon.insert(Temperatur, windms);
            System.out.println("Übertragung Erfolgreich!");

            TimeUnit.MINUTES.sleep(10);


        }


    }
}
