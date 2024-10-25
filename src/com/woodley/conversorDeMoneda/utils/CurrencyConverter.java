package com.woodley.conversorDeMoneda.utils;

import com.google.gson.JsonObject;

public class CurrencyConverter {

    // Método para convertir monedas
    public double convert(String baseCurrency, String targetCurrency, double amount) throws Exception {
        JsonObject jsonResponse = ApiRequest.sendRequest(baseCurrency, targetCurrency, amount);

        // Verificar el resultado de la respuesta
        if ("success".equals(jsonResponse.get("result").getAsString())) {
            return jsonResponse.get("conversion_result").getAsDouble();
        } else {
            throw new Exception("Error en la conversión: " + jsonResponse.get("error-type").getAsString());
        }
    }
}
