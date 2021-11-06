import java.util.ArrayList;

public class VideoGameStation {
    /**
     * Creating a instance variable as tore "String" in char Array.
     */
    private String str1 = "Game Station Name";
    private String str2 = "Video Game Name";
    private String str3 = "Customer  Name";
    private String str4 = "Customer Type";
    private String str5 = "Booking Date";
    private int Duration;
    private int HourlyRate;
    private boolean AvailableStatus;


    /**
     * Creating a Parameterized constructor.
     *
     * @param gsn game station name.
     * @param vdn video game name.
     * @param hr  hourly rate.
     */
    public VideoGameStation(String gsn, String vdn, int hr) {
        this.str1 = gsn;
        this.str2 = vdn;
        this.HourlyRate = hr;
        this.str3 = "";
        this.str4 = "";
        this.str5 = "";
        this.AvailableStatus = true;
        this.Duration = 0;
    }


    /**
     * getter method number.
     */
    public String getStr1() {
        return this.str1;
    }

    public String getStr2() {
        return this.str2;
    }

    public int getHourlyRate() {
        return this.HourlyRate;
    }

    public String getStr3() {
        return this.str3;
    }

    public String getStr4() {
        return this.str4;
    }

    public String getStr5() {
        return this.str5;
    }

    public boolean getAvailableStatus() {
        return this.AvailableStatus;
    }

    public int getDuration() {
        return this.Duration;
    }


    /**
     * Setter method number.
     *
     * @param HourRate hourly rate.
     */
    public void setHourlyRate(int HourRate) {
        this.HourlyRate = HourRate;
    }

    /**
     * Setter method number.
     *
     * @param CustomerType type of the customer.
     */

    public void setStr4(String CustomerType) {
        this.str4 = CustomerType;
    }


    /**
     * Method for booking VideoGameStation.
     *
     * @param CustomerName name of the customer.
     * @param CustomerType type of the customer.
     * @param CustomerDate customer hire date.
     * @param CustomerTime customer hire time.
     */
    public void tobook(String CustomerName, String CustomerType, String CustomerDate, int CustomerTime) {
        if (AvailableStatus == true) {
            this.str3 = CustomerName;
            this.str4 = CustomerType;
            this.str5 = CustomerDate;
            this.Duration = CustomerTime;
            this.AvailableStatus = false;
        } else {
            System.out.println("This game is not Available for " + this.str5 + "and" + this.Duration);
        }
    }


    /**
     * Creating a Method for  Removing VideoGameStation.
     */
    public void toremove() {
        if (AvailableStatus == true) {
            System.out.println("The Video Game is ready for Booking.");
        } else {
            this.str3 = "";
            this.str4 = "";
            this.str5 = "";
            this.AvailableStatus = true;
            this.Duration = 0;
        }
    }


    /**
     * Creating a method for display VideoGameStation.
     */
    public final void printInfo() {
        System.out.println("The Game Station Name is ==> " + this.str1);
        System.out.println("Hourly Rate is ==> " + this.HourlyRate);
    }


}
