package com.woodley.conversorDeMoneda.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest {

    private static final String API_KEY = "50cbe2d23360c1a85710a7a3";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

    // Método para enviar una solicitud a la API y obtener la respuesta en formato JSON
    public static JsonObject sendRequest(String baseCurrency, String targetCurrency, double amount) throws Exception {
        String requestUrl = API_URL + API_KEY + "/pair/" + baseCurrency + "/" + targetCurrency + "/" + amount;
        HttpURLConnection request = (HttpURLConnection) new URL(requestUrl).openConnection();
        request.connect();

        JsonObject jsonResponse = JsonParser.parseReader(new InputStreamReader(request.getInputStream())).getAsJsonObject();
        return jsonResponse;
    }
}
