public class Tree {

    public int pred;
    Tree left = null;
    Tree right = null;
    public Boolean englishOrDutch = null; //null if attribute, T/F if leaf node

    public Tree(int pred, Tree left, Tree right, Boolean AorB){

        this.pred = pred;
        this.right = right;
        this.left = left;
        this.englishOrDutch = AorB;

    }

    @Override
    public String toString() {
        if(englishOrDutch != null){

            if(englishOrDutch){

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
}
