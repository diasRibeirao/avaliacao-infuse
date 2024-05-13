package br.com.infuse.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utils {

    public static LocalDateTime stringToLocalDateTime(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        try {
            return LocalDateTime.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data inválido: " + data, e);
        }
    }
}
