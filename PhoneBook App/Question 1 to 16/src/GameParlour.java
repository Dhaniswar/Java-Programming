import java.util.ArrayList;
import java.util.Collections;

public class GameParlour {

    /**
     * Creating an ArrayList of object to store video game station.
     */
    private ArrayList<VideoGameStation> mhimArray = new ArrayList<>();

    public void addVideoGameStation(String GameStationName, String VideoGameName, int GameHourRate) {

        /**
         * TO Add object to ArrayList.
         */
        mhimArray.add(new VideoGameStation(GameStationName, VideoGameName, GameHourRate));
    }


    /**
     * Creating a Method for removing object from the array list.
     *
     * @param index it's an index number used to removed the position in the array list of the object.
     */
    public void removingGameStation(int index) {
        try {
            mhimArray.remove(index);

        } catch (IndexOutOfBoundsException exp) {
            System.out.println("Video Game Station doesn't exist.");
        }
    }


    /**
     * For booking VideoGameStation.
     * Such book gameStation parameters question the user's interest and move it on to the getBooking process.
     *
     * @param index                it's an index number.
     * @param CustomerName         name of the customer.
     * @param CustomerType         types of customer.
     * @param CustomerDate         customer hire date.
     * @param CustomerTimeDuration customer time duration.
     */
    public void bookVideoGameStation(int index, String CustomerName, String CustomerType, String CustomerDate, int CustomerTimeDuration) {
        try {
            VideoGameStation nisal = mhimArray.get(index);
            nisal.tobook(CustomerName, CustomerType, CustomerDate, CustomerTimeDuration);
        } catch (IndexOutOfBoundsException exp) {
            System.out.println("Provided index number doesn't exist.");
        }
    }


    /**
     * Creating a method to free the video game for the customer.
     *
     * @param index it is an index number, it is used to get the Video Game from the Game Parlour and
     *              call the method in the Video Game Station class to make Video Game free.
     */
    public void freeVideoGameStation(int index) {
        try {
            VideoGameStation ronisfree = mhimArray.get(index);
            ronisfree.toremove();
        } catch (IndexOutOfBoundsException exp) {
            System.out.println("Given Index Number is Invalid.");
        }
    }


    /**
     * Method for displayVideoGame or to called all the previous object.
     */
    public void displayVideoGame() {
        for (VideoGameStation ryjdisplay : mhimArray) {
            if (ryjdisplay.getAvailableStatus()) {
                System.out.println("Index Number : " + mhimArray.indexOf(ryjdisplay));
                ryjdisplay.printInfo();
            }
        }
    }


    /**
     * Searching for where we're passing parameter, which helps to users to search the specific object user want.
     *
     * @param search          is required to search the list of game station currently available.
     * @param MaximumHourRate maximum hourly rate.
     */
    public void searchOneByOne(String search, int MaximumHourRate) {
        int power = 1;
        for (VideoGameStation oneByOne : mhimArray) {
            if (oneByOne.getStr1().equals(search) && oneByOne.getHourlyRate() < MaximumHourRate) {
                displayVideoGame();
                power = 0;
            }

        }
        if (power != 0) {
            System.out.println("didn't match each condition");
        }
    }


    /**
     * if we want to understand this then we should see class==> DisplayByName.
     */
    public void displayAscending() {
        /**
         * Exchange existing array with new shorted array i.e. Collection sort completely change.
         */
        Collections.sort(mhimArray, new DisplayByName());
        System.out.println("The new Sorted Ascending Array is ==>");

        /**
         * The logic of the given below is same as above.
         */
        for (VideoGameStation customerNameBooked : mhimArray) {
            if (customerNameBooked.getAvailableStatus()) {
                System.out.println("Customer Name ==> " + customerNameBooked.getStr3());
                System.out.println("Customer Booked VideoGameStation Name ==> " + customerNameBooked.getStr1());
            }
        }

    }

}
