package main.java.view;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class NewEventInput {

    @NotNull
    String name;
    @NotNull
    String date;
    @NotNull
    String cost;
    @NotNull
    String description;

    public static Date toDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");
        return formatter.parse(date);
    }

    public static Double toDouble(String cost) throws NumberFormatException {
        return Double.parseDouble(cost.replaceAll(",","."));
    }
}
