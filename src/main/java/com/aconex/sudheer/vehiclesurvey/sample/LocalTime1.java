package com.aconex.sudheer.vehiclesurvey.sample;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;

import com.aconex.sudheer.vehiclesurvey.util.TimeUtil;

public class LocalTime1 {

    public static void main(String[] args) {

        // get the current time
        Clock clock = Clock.systemDefaultZone();
        long t0 = clock.millis();
        System.out.println(t0);

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);


        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");

        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

        // time
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1);
        System.out.println(now2);

        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hoursBetween);
        System.out.println(minutesBetween);


        // create time

        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);

        DateTimeFormatter germanFormatter =
                DateTimeFormatter
                        .ofLocalizedTime(FormatStyle.SHORT)
                        .withLocale(Locale.GERMAN);

        LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(leetTime);


        // to legacy date
        
        ZoneId zoneId = ZoneId.systemDefault();
        DateTimeFormatter mydf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS").withZone(zoneId);

        // ZoneId.of( "America/Montreal" )
        LocalDate previousMonday = 
        	    LocalDate.now(zoneId)
        	             .with( TemporalAdjusters.previous( DayOfWeek.MONDAY ) ) ;
        
        Instant mondayStartInstant = previousMonday.atStartOfDay(zoneId).toInstant();
        long epoch = mondayStartInstant.toEpochMilli();

        System.out.println(epoch);
        Instant ins = Instant.ofEpochMilli(epoch);
        System.out.println(ins);
        

        
    	// System.out.println(TimeUtil.df.format(Instant.ofEpochMilli(epoch)));



    }

}