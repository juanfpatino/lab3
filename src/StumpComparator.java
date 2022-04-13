import java.util.Comparator;

//a stump also has a weight
public class StumpComparator implements Comparator<Tree> {

    @Override
    public int compare(Tree o1, Tree o2) {

        return Double.compare(o2.weight, o1.weight);

    }


}
