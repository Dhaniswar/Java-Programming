import java.util.Comparator;

public class DisplayByName implements Comparator<VideoGameStation> {

    /**
     * It is used to Sorting in ascending order of Array List.
     */
    public int compare(VideoGameStation i, VideoGameStation j) {
        return i.getStr1().compareTo(j.getStr1());
    }
}
