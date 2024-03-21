package com.example.lab5spring;
import com.example.lab5spring.entity._Employee_;
import com.opencsv.CSVWriter;

import java.io.StringWriter;
import java.util.List;

public class CsvGenerator {

    public static byte[] generateCsvFromTeachers(List<_Employee_> teachers) throws Exception {
        try (StringWriter stringWriter = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(stringWriter)){

            String[] headerRecord = {"ID", "Imię", "Nazwisko", "Stan", "Rok urodzenia", "Wynagrodzenie", "Grupa"};
            csvWriter.writeNext(headerRecord);

            for (_Employee_ teacher : teachers) {
                String[] dataRecord = {
                        String.valueOf(teacher.getId()),
                        teacher.getImie(),
                        teacher.getNazwisko(),
                        teacher.getStan(),
                        String.valueOf(teacher.getRokUrodzenia()),
                        String.valueOf(teacher.getWynagrodzenie()),
                        String.valueOf(teacher.getGrupyByGrupa().getId())
                };
                csvWriter.writeNext(dataRecord);
            }

            return stringWriter.toString().getBytes();
        }
        catch (Exception e){
            throw new Exception("Błąd generowania pliku CSV", e);
        }
    }
}
