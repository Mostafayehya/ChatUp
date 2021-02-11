package eg.gov.iti.jets.utilities;

import java.sql.Date;
import java.time.LocalDate;

public class JavaSqlTimeConverter {
    public static Date convertJavadateToSqlDate(LocalDate date){
        return Date.valueOf(date);
    }

    public static LocalDate convertSqlDateToJavaDate(Date date){
        return date.toLocalDate();
    }
}
