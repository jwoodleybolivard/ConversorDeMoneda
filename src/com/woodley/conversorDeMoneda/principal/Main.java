package com.woodley.conversorDeMoneda.principal;

import com.woodley.conversorDeMoneda.utils.CurrencyConverter;
import com.woodley.conversorDeMoneda.utils.ConversionHistory;
import com.woodley.conversorDeMoneda.utils.ApiRequest;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();
        ConversionHistory history = new ConversionHistory();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Menú de opciones ===");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Mostrar historial de conversiones");
            System.out.println("3. Ayuda");
            System.out.println("4. Listar códigos de moneda soportados");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\n***************************************************************");
                System.out.println("Entrada inválida. Por favor, ingresa un número.");
                scanner.next(); // Limpiar la entrada invalida
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Ingresa el valor a convertir: ");
                        double amount = scanner.nextDouble();
                        System.out.print("Ingresa la moneda de origen (ej. USD): ");
                        String baseCurrency = scanner.next().toUpperCase();
                        System.out.print("Ingresa la moneda de destino (ej. EUR): ");
                        String targetCurrency = scanner.next().toUpperCase();

                        double result = converter.convert(baseCurrency, targetCurrency, amount);
                        System.out.println("\n***************************************************************");
                        System.out.println("Resultado: " + amount + " " + baseCurrency + " son equivalentes a " + result + " " + targetCurrency);
                        history.addEntry(baseCurrency, targetCurrency, amount, result);
                    } catch (InputMismatchException e) {
                        System.out.println("\n***************************************************************");
                        System.out.println("Entrada inválida. Por favor, ingresa un número válido.");
                        scanner.next(); // Limpiar la entrada invalida
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n***************************************************************");
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("\n***************************************************************");
                        System.out.println("Error al convertir: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\n***************************************************************");
                    history.displayHistory();
                    break;
                case 3:
                    System.out.println("\n***************************************************************");
                    System.out.println("Ayuda: Ingresa las siglas de las monedas usando códigos ISO 4217.");
                    System.out.println("Ejemplos de códigos: USD (Dólar estadounidense), EUR (Euro), JPY (Yen japonés), GBP (Libra esterlina), AUD (Dólar australiano), CAD (Dólar canadiense), CHF (Franco suizo), CNY (Yuan chino), SEK (Corona sueca), NZD (Dólar neozelandés).");
                    break;
                case 4:
                    try {
                        String[] supportedCodes = converter.getSupportedCodes();
                        System.out.println("\n***************************************************************");
                        System.out.println("Códigos de moneda soportados:");
                        for (String code : supportedCodes) {
                            System.out.println(code);
                        }
                    } catch (Exception e) {
                        System.out.println("\n***************************************************************");
                        System.out.println("Error al obtener los códigos de moneda: " + e.getMessage());
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("\n***************************************************************");
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("\n***************************************************************");
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
