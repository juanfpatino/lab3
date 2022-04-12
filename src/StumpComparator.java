import java.util.Comparator;

public class StumpComparator implements Comparator<Tree> {


    @Override
    public int compare(Tree o1, Tree o2) {

        return Double.compare(o2.weight, o1.weight);

    }


}
