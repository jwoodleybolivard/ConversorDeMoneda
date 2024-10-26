package com.woodley.conversorDeMoneda.utils;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {

    // Método para convertir monedas
    public double convert(String baseCurrency, String targetCurrency, double amount) throws Exception {
        if (!isValidCurrencyCode(baseCurrency) || !isValidCurrencyCode(targetCurrency)) {
            throw new IllegalArgumentException("Código de moneda no soportado.");
        }

        JsonObject jsonResponse = ApiRequest.sendRequest(baseCurrency, targetCurrency, amount);
        if ("success".equals(jsonResponse.get("result").getAsString())) {
            return jsonResponse.get("conversion_result").getAsDouble();
        } else {
            throw new Exception("Error en la conversión: " + jsonResponse.get("error-type").getAsString());
        }
    }

    // Método para obtener códigos de monedas soportadas
    public String[] getSupportedCodes() throws Exception {
        JsonObject jsonResponse = ApiRequest.sendSupportedCodesRequest();
        if ("success".equals(jsonResponse.get("result").getAsString())) {
            List<String> codesList = new ArrayList<>();
            jsonResponse.get("supported_codes").getAsJsonArray().forEach(item -> {
                codesList.add(item.getAsJsonArray().get(0).getAsString() + " - " + item.getAsJsonArray().get(1).getAsString());
            });
            return codesList.toArray(new String[0]);
        } else {
            throw new Exception("Error al obtener los códigos de moneda: " + jsonResponse.get("error-type").getAsString());
        }
    }

    private boolean isValidCurrencyCode(String code) {
        try {
            String[] supportedCodes = getSupportedCodes();
            for (String supportedCode : supportedCodes) {
                if (supportedCode.startsWith(code)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
