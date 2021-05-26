public class TimeFormat
{
    int year;
    int month;
    int day;
    int hour;
    int minute;
    int second;
    
    public static TimeFormat timeFormatter(String s)//conversion of json time format into object format
    {
        TimeFormat time = new TimeFormat();
        time.year = Integer.parseInt(s.substring(0,4));
        time.month = Integer.parseInt(s.substring(5,7));
        time.day = Integer.parseInt(s.substring(8,10));
        time.hour = Integer.parseInt(s.substring(11,13));
        time.minute = Integer.parseInt(s.substring(14,16));
        time.second = Integer.parseInt(s.substring(17,19));
        return time;
    }
}