import java.util.Objects;

//Tree object, also functions as a stump for boosted training
public class Tree{

    public int pred;
    Tree left = null;
    Tree right = null;
    public Boolean englishOrDutch = null; //null if attribute, T/F if leaf node
    public double weight = 0.0; //adaboost

    public Tree(int pred, Tree left, Tree right, Boolean AorB){

        this.pred = pred;

        this.right = right;
        this.left = left;
        this.englishOrDutch = AorB;

        hardCodeFixBothSidesEnglishOrBothSidesDutch(left, right); //this is a recursion thing, because the left side
                                                                    //does not interact with the right, not
                                                                //inherently a problem with how I am implementing the
                                                                //learn decision tree algorithm

    }

    private void hardCodeFixBothSidesEnglishOrBothSidesDutch(Tree left, Tree right) {
        if(left != null && right != null && left.pred == -1 && right.pred == -1){

            if(left.englishOrDutch == right.englishOrDutch){

                this.englishOrDutch = left.englishOrDutch;
                this.pred = -1;
                this.right = null;
                this.left = null;

            }

        }
    }

    @Override
    public String toString() {
        if(this.englishOrDutch != null){

            if(this.englishOrDutch){

                return "English";

            }
            else{

                return "Dutch";

            }


        }
        else{

            return "[(" + pred + ") right: " + right + " left: " + left + "]"; //this isnt it

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return pred == tree.pred && Double.compare(tree.weight, weight) == 0 && Objects.equals(left, tree.left) && Objects.equals(right, tree.right) && Objects.equals(englishOrDutch, tree.englishOrDutch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pred, left, right, englishOrDutch);
    }
}
