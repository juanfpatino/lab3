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
}
