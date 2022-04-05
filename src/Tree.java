public class Tree {

    public int pred;
    Tree left = null;
    Tree right = null;
    public Boolean AorB = null; //null if attribute, T/F if leaf node

    public Tree(int pred, Tree left, Tree right, Boolean AorB){

        this.pred = pred;
        this.right = right;
        this.left = left;
        this.AorB = AorB;

    }

    @Override
    public String toString() {
        if(AorB != null){

            if(AorB){

                return "(A)";

            }
            else{

                return "(B)";

            }


        }
        else{

            return "[(" + pred + ") right: " + right + " left: " + left + "]"; //this isnt it

        }
    }
}
