package org.example.cayenne.persistent;

import org.example.cayenne.persistent.auto._Artist;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Artist extends _Artist {

    private static final long serialVersionUID = 1L;
    protected static final String DEFAULT_DATE_FORMAT = "yyyyMMdd";

    public void setDateIfBirthString(String yearMonthDay) {
        if (yearMonthDay == null) {
            setDateOfBirthDate(null);
            return;
        }
        LocalDate localDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
            localDate = LocalDate.parse(yearMonthDay,formatter);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(
                    "A date arguments must be in format '" + DEFAULT_DATE_FORMAT
                            + "':" + yearMonthDay);
        }
        setDateOfBirthDate(localDate);
    }


}
