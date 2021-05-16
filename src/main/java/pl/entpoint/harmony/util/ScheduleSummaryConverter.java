package pl.entpoint.harmony.util;

import pl.entpoint.harmony.entity.pojo.SimpleSchedule;
import pl.entpoint.harmony.entity.schedule.ScheduleRecord;
import pl.entpoint.harmony.entity.schedule.ScheduleSummary;
import pl.entpoint.harmony.entity.schedule.enums.ScheduleType;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Mateusz Dąbek
 * @created 30.11.2020
 */
public class ScheduleSummaryConverter {

    private ScheduleSummaryConverter() {
    }

    public static List<SimpleSchedule> convert(List<ScheduleSummary> summaryList) {
        List<SimpleSchedule> simpleSchedules = new ArrayList<>();
        if (summaryList == null) {
            throw new IllegalArgumentException("Lista nie może być pusta.");
        }

        // Sortowanie po nazwisku
        summaryList.sort(new Comparator<>() {
            private final Collator c = Collator.getInstance(new Locale("pl", "PL"));

            @Override
            public int compare(ScheduleSummary o1, ScheduleSummary o2) {
                return c.compare(o1.getSimpleEmployee().getFullName(), o2.getSimpleEmployee().getFullName());
            }
        });

        for (ScheduleSummary summary: summaryList) {
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.DOSTEPNOSC,
                    extractRecordsByType(ScheduleType.DOSTEPNOSC, summary.getScheduleRecords())));
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.DYSPOZYCYJNOSC,
                    extractRecordsByType(ScheduleType.DYSPOZYCYJNOSC, summary.getScheduleRecords())));
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.DZWONIENIE,
                    extractRecordsByType(ScheduleType.DZWONIENIE, summary.getScheduleRecords())));
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.GRAFIK,
                    extractRecordsByType(ScheduleType.GRAFIK, summary.getScheduleRecords())));
            // TODO dodać w ustawieniach opcje wyłączenia dla innych projektów statusu Jitsi
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.JITSI,
                    extractRecordsByType(ScheduleType.JITSI, summary.getScheduleRecords())));
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.OBECNOSC,
                    extractRecordsByType(ScheduleType.OBECNOSC, summary.getScheduleRecords())));
            simpleSchedules.add(new SimpleSchedule(summary.getSimpleEmployee(), ScheduleType.ZALOGOWANIE,
                    extractRecordsByType(ScheduleType.ZALOGOWANIE, summary.getScheduleRecords())));
        }
        return simpleSchedules;
    }

    private static List<ScheduleRecord> extractRecordsByType(ScheduleType type, List<ScheduleRecord> records) {
        return records.stream()
                .filter(r -> r.getTypes().equals(type))
                .collect(Collectors.toList());
    }
}
