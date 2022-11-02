package ru.nsu.valikov;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * {datetime: {title : description}}
 */
public class RecordBook implements Book {
    private Map<LocalDateTime, String> records;


    public void addRecord(String title, String description) {
        LocalDateTime currentDate = LocalDateTime.now();
    }

    public void showRecords() {

    }

    public void showRecords(LocalDateTime from, LocalDateTime to, List<String> titleList) {
        for (var title : titleList) {

        }
    }

    public void deleteRecord(String title) {

    }


}
