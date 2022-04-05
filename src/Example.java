import java.util.ArrayList;
import java.util.Arrays;

public class Example {

    public ArrayList<String> predicates; //always 15 long
    public boolean englishOrDutch;

    public Example(ArrayList<String> predicates, boolean englishOrDutch){

        this.predicates = predicates;
        this.englishOrDutch = englishOrDutch;

    }

    @Override
    public String toString() {

        String s = "Dutch";
        if(englishOrDutch)
            s = "English";

        return predicates +
                "......... " + s +
                '}';
    }
}
