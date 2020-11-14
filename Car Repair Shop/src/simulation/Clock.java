/** Clock class
 *
 *  -defines a clock that keeps track of the time inside the simulation.
 *  -the clock gets initialized with the current time and date.
 */

package simulation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Clock implements Serializable {
    int hours;
    int minutes;
    int year;
    int month;
    int day;

    public Clock() {
        this.initialize();
    }

    private void initialize() {
        LocalDateTime now = LocalDateTime.now();

        this.hours = now.getHour();
        this.minutes = now.getMinute();
        this.day = now.getDayOfMonth();
        this.month = now.getMonthValue();
        this.year = now.getYear();
    }

    private int getMonthDays() {
        if (this.month == 1 || this.month == 3 || this.month == 5 ||
                this.month == 7 || this.month == 8 || this.month == 10 || this.month == 12) {
            return 31;
        }
        else if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
            return 30;
        }
        else if (this.month == 2) {
            if (this.year % 4 == 0) {
                return 29;
            } else {
                return 28;
            }
        }

        return 0;
    }

    public void advance() {
        this.minutes ++;

        if (this.minutes == 60) {
            this.minutes = 0;
            this.hours++;

            if (this.hours == 24) {
                this.hours = 0;
                this.day++;
                if ( this.day > this.getMonthDays()) {
                    this.day = 1;
                    this.month++;
                    if (this.month > 12) {
                        this.month = 1;
                        this.year++;
                    }
                }
            }
        }
    }

    public void advance(int minutes) {
        this.minutes += minutes;

        while (this.minutes >= 60) {
            this.minutes = this.minutes - 60;
            this.hours++;

            if (this.hours == 24) {
                this.hours = 0;
                this.day++;
                if ( this.day > this.getMonthDays()) {
                    this.day = 1;
                    this.month++;
                    if (this.month > 12) {
                        this.month = 1;
                        this.year++;
                    }
                }
            }
        }
    }

    public String date() {
        String toPrint = "";

        if (this.day < 10) {
            toPrint += "0";
            toPrint += this.day;
        } else {
            toPrint += this.day;
        }

        toPrint += ".";

        if (this.month < 10) {
            toPrint += "0";
            toPrint += this.month;
        } else {
            toPrint += this.month;
        }

        toPrint += ".";
        toPrint += this.year;

        return toPrint;
    }

    @Override
    public String toString() {
        String toPrint = "";

        if (this.hours < 10) {
            toPrint += "0";
            toPrint += this.hours;
        } else {
            toPrint += this.hours;
        }

        toPrint += ":";

        if (this.minutes < 10) {
            toPrint += "0";
            toPrint += this.minutes;
        } else {
            toPrint += this.minutes;
        }

        return toPrint;
    }
}
