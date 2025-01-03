package com.example.prova;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ConselhoService extends AsyncTask<Void, Void, String> {

         @Override
        protected String doInBackground(Void... voids) {
                StringBuilder resposta = new StringBuilder();

                try {
                        URL url = new URL("https://api.adviceslip.com/advice");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setRequestProperty("Content-type", "application/json");
                        connection.setRequestProperty("Accept", "application/json");
                        connection.setDoOutput(true);
                        connection.setConnectTimeout(5000);
                        connection.connect();

                        Scanner scanner = new Scanner(url.openStream());
                        scanner.useDelimiter("\\A");
                        while (scanner.hasNext()) {
                                resposta.append(scanner.next());
                        }
                } catch (MalformedURLException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                return resposta.toString();
        }
}
