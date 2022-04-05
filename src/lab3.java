import java.io.File;
import java.io.IOException;
import java.util.*;

public class lab3 {

    public static void main(String[] args) throws IOException {

        Scanner S = new Scanner(new File(args[0]));

        String ss = S.nextLine();

        ArrayList<Example> hypothesisSpace = new ArrayList<>();

        while(true){

            String[] line = ss.split(" ");

            Example e = new Example(new ArrayList<>(Arrays.asList(line).subList(1, line.length)), line[0].equals("en"));

            hypothesisSpace.add(e);

            try{

                ss = S.nextLine();

            }
            catch (NoSuchElementException n){

                break;

            }

        }


        System.out.println(learn_decision_tree(hypothesisSpace, new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)) , hypothesisSpace));

    }

    private static int importance(ArrayList<Integer> attributes, ArrayList<Example> examples){//returns the index of the most important predicate

        //return the attribute with the most information gain

        if(attributes.size()== 1) return attributes.get(0);

        double[] d = new double[8];

        for (int i = 0; i < 8; i++) {



            if(!attributes.contains(i))continue;

            //confusing. but for this attribute how many examples conclude with the A class or B
            ArrayList<Boolean> TRUE = new ArrayList<>(); //true = A, false = B
            ArrayList<Boolean> FALSE = new ArrayList<>(); //true = A, false = B

            for (Example e: examples
            ) {

                if(e.predicates.get(i)){

                    TRUE.add(e.englishOrDutch);

                }
                else{

                    FALSE.add(e.englishOrDutch);

                }

            }

            //Gain(A) = B(p/p+n) - Remainder(A)

            int AinTrue = 0;
            int BinTrue = 0;

            int AinFalse = 0;
            int BinFalse = 0;

            for (boolean A: TRUE
            ) {

                if(A){

                    AinTrue++;

                }
                else{

                    BinTrue++;

                }

            }

            for (boolean A: FALSE
            ) {

                if(A){

                    AinFalse++;

                }
                else{

                    BinFalse++;

                }

            }

            int p = AinFalse + AinTrue;
            int n = BinFalse + BinTrue;

            double trueRemainder = remainder(p,n,AinTrue,BinTrue) ;
            if(AinTrue == 0 || BinTrue == 0) trueRemainder = 0;
            double falseRemainder =  remainder(p,n,AinFalse,BinFalse);
            if(AinFalse == 0 || BinFalse == 0) falseRemainder = 0;

            double gain = B(p,p + n) - trueRemainder + falseRemainder;

            d[i] = gain;

        }

        double most = 0;
        int i = 0;
        int mostIdx = 0;

        for (double D: d
        ) {

            if(!attributes.contains(i)){

                i++;
                continue;

            }

            if(D>most){

                most = D;
                mostIdx = i;

            }
            i++;

        }

        return mostIdx;

    }

    private static double remainder(int p, int n, int pk, int nk){

        double afd = Double.sum(pk, nk);
        double jkl = Double.sum(p, n);
        double firstPart = afd/jkl;

        return firstPart * B(pk,(int)afd);

    }

    private static double B(int p, int pPlusn){

        double q = (double) p/pPlusn; //P(A = TRUE)

        return -1*(q*Math.log(q) + (1-q)*Math.log(1-q));

    }

    private static Tree learn_decision_tree(ArrayList<Example> examples, ArrayList<Integer> attributes, ArrayList<Example> parent_examples){

        if(examples.size() == 0){

            return plurality_value(parent_examples);

        }
        else if(allExamplesHaveTheSameClassification(examples)){

            return new Tree(-1, null, null, examples.get(0).englishOrDutch);

        }
        else if(attributes.size()==0){

            return plurality_value(examples);

        }
        else{

            Integer A = importance(attributes, examples);

            attributes.remove(A);

            //left (true) //examples are now how many are true with this attribute

            Tree left = learn_decision_tree(whichAreTrue(A, examples), attributes, examples);

            //right (false) //examples are now how many are false with this attribute

            Tree right = learn_decision_tree(whichAreFalse(A, examples), attributes, examples);

            return new Tree(A, left, right, null);

        }


    }

    private static ArrayList<Example> whichAreTrue(int a, ArrayList<Example> examples){

        ArrayList<Example> temp = new ArrayList<>();

        for (Example e: examples
        ) {

            if(e.predicates[a]) temp.add(e);

        }

        return temp;

    }

    private static ArrayList<Example> whichAreFalse(int a,ArrayList<Example> examples){

        ArrayList<Example> temp = new ArrayList<>();

        for (Example e: examples
        ) {

            if(!e.predicates[a]) temp.add(e);

        }

        return temp;

    }

    private static Tree plurality_value(ArrayList<Example> examples){

        int A = 0;
        int B = 0;

        for (Example e: examples
        ) {

            if(e.englishOrDutch){

                A++;

            }
            else{

                B++;

            }

        }

        if(A==B){


            return new Tree(-1, null, null, Math.random()*1 == 1);

        }

        return new Tree(-1, null, null, A>B);


    }

    private static boolean allExamplesHaveTheSameClassification(ArrayList<Example> examples){

        boolean startsWithA = false;

        if(examples.get(0).englishOrDutch){

            startsWithA = true;

        }


        for (int i = 0; i < examples.size(); i++) {

            Example e = examples.get(i);

            if(startsWithA){

                if(!e.englishOrDutch)
                    return false;

            }
            else{//starts w B

                if(e.englishOrDutch)
                    return false;

            }

        }

        return true;

    }


}
