package com.example.gym;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DodawanieElementow {
//    String insert = "insert into event(id, title, description) values (" + i + ", 'Nazwa" + i + "', 'Opis" + i + "');";
    public static void main(String[] args) {
        String filename = "data.sql";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i <= 100; i++) {
                stringBuilder.append(String.format("insert into event(id, title, description) values (%d, 'Nazwa%d', 'Opis%d');\n", i,i,i));
            }
            writer.write(stringBuilder.toString());

            System.out.println("Polecenia INSERT zostały zapisane do pliku " + filename);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu do pliku: " + e.getMessage());
        }
    }
}
