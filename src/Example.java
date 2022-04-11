import java.util.ArrayList;
import java.util.Arrays;

public class Example {

    public ArrayList<Boolean> predicates; //always 15 long
    public boolean englishOrDutch;
    public String[] originalLine;
    public double adaBoostWeight = 0.0;

    public Example(ArrayList<Boolean> predicates, boolean englishOrDutch, String[] originalLine){

        this.predicates = predicates;
        this.englishOrDutch = englishOrDutch;
        this.originalLine = originalLine;

    }

    @Override
    public String toString() {

        String s = "Dutch";
        if(englishOrDutch)
            s = "English";

        return "{" + s + "} {" + Arrays.toString(originalLine) +
                "......... " +
                '}' + " (" + predicates.toString() + ")";
    }
}
