package com.woodley.conversorDeMoneda.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private List<String> history;

    public ConversionHistory() {
        history = new ArrayList<>();
    }

    // Agregar una entrada al historial de conversiones
    public void addEntry(String baseCurrency, String targetCurrency, double amount, double result) {
        String entry = String.format("%s: Convertido %f %s a %f %s",
                LocalDateTime.now(), amount, baseCurrency, result, targetCurrency);
        history.add(entry);
    }

    // Mostrar el historial de conversiones
    public void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
        } else {
            System.out.println("Historial de Conversiones:");
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }
}
