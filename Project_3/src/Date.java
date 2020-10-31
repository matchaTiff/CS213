/**
 * The class Date represents a specific instance in time.
 * Allows the interpretation of dates as year, month, and day.
 *
 * @author Seth Santos, Tiffany Chen
 *
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    private int leapYearCriteria1 = 400;
    private int leapYearCriteria2 = 100;
    private int leapYearCriteria3 = 4;

    /**
     * Default constructor
     */
    public Date() {
        year = 0;
        month = 0;
        day = 0;
    }

    /**
     * Class constructor that initializes the data fields of the date.
     *
     * @param month month account opened
     * @param day day account opened
     * @param year year account opened
     */
    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Getter method for the year the account was opened
     * @return year int
     */
    public int getYear(){
        return year;
    }

    /**
     * Getter method for month account was opened
     * @return Month int
     */
    public int getMonth(){
        return month;
    }

    /**
     * Getter method for the day account was opened
     * @return day int
     */
    public int getDay(){
        return day;
    }

    /**
     * Compares two Dates for ordering.
     * @param date Date that is being compared
     * @return -1 - this date is before the parameter date
     *          0 - dates are the same
     *          1 - this date is after the parameter date
     */
    public int compareTo(Date date) {
        if( this.year < date.getYear() ){
            return -1;
        }else if( this.year > date.getYear() ){
            return 1;
        }// Else Years are the same


        if( this.month < date.getMonth() ){
            return -1;
        }else if( this.month > date.getMonth() ){
            return 1;
        }// Else Months are the same


        if( this.day < date.getDay() ){
            return -1;
        }else if( this.day > date.getDay() ){
            return 1;
        }// Else Days are the same

        return 0; // Everything is the same
    }


    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    } //in the format mm/dd/yyyy

    /**
     * Determines whether or not the year is a leap year or not
     * @return True or False depending on leap year status
     */
    private boolean isLeapYear() {
        if( year % leapYearCriteria1 == 0){
            return true;
        }else if( year % leapYearCriteria2 == 0 ){
            return false;
        }else if( year % leapYearCriteria3 == 0 ){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Determines if the month has 31 days or not
     * @return True or False depending on the recorded month
     */
    private boolean is31DayMonth(){
        switch(this.month){
            case(1):
            case(3):
            case(5):
            case(7):
            case(8):
            case(10):
            case(12):
                return true;
            default: return false; // month is not one of these numbers
        }
    }

    /**
     * Determines if the Day, Month, and Year the account was opened is a valid date
     * @return True if the date is valid
     *         False if the date is invalid
     */
    public boolean isValid() {
        int MAX_DAY; // The maximum amount of days in the month
        if( month < 1 || month > 12 || day < 1){
            return false;
        }
        if( is31DayMonth() ){
            MAX_DAY = 31;
        }else{
            if( month == 2 ){ // Checks if the month is February
                if( isLeapYear() ){ // Checks if it's leap year so February would have 29 days
                    MAX_DAY = 29;
                }else{
                    MAX_DAY = 28;
                }
            }else{
                MAX_DAY = 30;
            }
        }
        if( day > MAX_DAY ){ // Checks that the day does not exceed the max number of days in the month
            return false;
        }

        if(year <= 0){ // Year cannot be less than 1
            return false;
        }
        return true;
    }
}
