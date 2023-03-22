/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author FPTshop
 */
public class DateDAO {

    public String parseDate(LocalDateTime localDateTime, String format) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }
    
}
