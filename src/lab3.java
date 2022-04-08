import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class lab3 {

    public static void main(String[] args) throws IOException {

        Scanner S = new Scanner(new File(args[1]));
        String hFile;

        if(args[0].equals("train")){ //train <examples> <hypothesisOut> <learning-type>

            hFile = args[2];

            String ss = S.nextLine();
            BufferedWriter writer = new BufferedWriter(new FileWriter(hFile));

            ArrayList<Example> exampleSpace = configureES(S, ss); //HYPOTHESIS SPACE //EXAMPLES

            Tree t;
            Tree l = learn_decision_tree(exampleSpace, new ArrayList<>(Arrays.asList(0,1,2,3,4,5)), exampleSpace);


            if(args[3].equals("dt")){ //decision tree

                t = l;

            }
            else{//"ada" //adaboost

                t = adaBoost(exampleSpace, l, 6); //6 hypothesis

            }

            assert t != null;
            String tree = t.toString();

            writer.write(tree);
            System.out.println(tree);
            writer.close();

        }
        else{//predict <hypothesis> <file>

            hFile = args[1];

            String tString = S.nextLine();

            Tree t = parseTree(tString.split(" "));

            if(t.toString().equals(tString)) System.out.println("PARSE WORKS");

            //read tree file
            //spit out new file

        }


    }

    private static Tree parseTree(String[] sp){


        String firstS = sp[0];

        if(firstS.contains("English") || firstS.contains("Dutch")){

            return new Tree(-1,null,null,firstS.contains("English"));

        }
        else{//number

            String[] right = new String[sp.length-1];

            int startIdx = 2;


            System.arraycopy(sp, startIdx, right, 0, sp.length-startIdx);

            int rightLength = right.length;

            if(right[0].equals("Dutch")){

                String[] newRight = new String[]{"Dutch"};
                String[] left = new String[rightLength-startIdx];
                System.arraycopy(right, startIdx, left, 0, rightLength-startIdx);
                return new Tree(Integer.parseInt(firstS.substring(2,3)), parseTree(left), parseTree(newRight), null);

            }
            else if(right[0].equals("English")){

                String[] newRight = new String[]{"English"};
                String[] left = new String[rightLength-startIdx];
                System.arraycopy(right, startIdx, left, 0, rightLength-startIdx);
                return new Tree(Integer.parseInt(firstS.substring(2,3)), parseTree(left), parseTree(newRight), null);


            }

            int leftBrackets = 0;
            int rightBrackets = 0;
            //when these two equal each other, good to cut string

            String[] left = new String[69];
            boolean leftDone = false;

            for (int i = 1; i < sp.length; i++) {

                if(leftDone)break;

                String ss = sp[i];

                if(ss.contains("["))leftBrackets++;
                if(ss.contains("]")){

                    for (char c: ss.toCharArray()
                         ) {

                        if(c == ']'){

                            rightBrackets++;

                            if(rightBrackets  == leftBrackets){

                                left = new String[sp.length - i];
                                for (int j = 0; j < left.length - 2; j++) {

                                    left[j] = sp[i + 2 +j];

                                }


                                leftDone = true;
                                break;

                            }

                        }

                    }

                }



            }

            return new Tree(Integer.parseInt(firstS.substring(2,3)), parseTree(left), parseTree(right), null);

        }

    }

    private static Tree adaBoost(ArrayList<Example> examples, Tree dt, int k){

        Tree t = dt;

        for (int i = 0; i < k; i++) {

            //todo convert tree into some hypothesis space format

            int n = examples.size();

            for (int j = 1; j < n; j++) {

            }
            for (int j = 1; j < n; j++) {

            }

            //normalize
            //other stuff

        }

        return t;

    }


    private static ArrayList<Example> configureES(Scanner S, String ss) {
        ArrayList<Example> hypothesisSpace = new ArrayList<>();

        while(true){

            String[] line = ss.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

            Example e = createExampleFromLine(line);
            hypothesisSpace.add(e);

            try{

                ss = S.nextLine();

            }
            catch (NoSuchElementException n){

                break;

            }

        }
        return hypothesisSpace;
    }

    private static Example createExampleFromLine(String[] line) {
        ArrayList<Boolean> preds = new ArrayList<>();

        boolean E = line[0].substring(0,2).equals("en");

        line[0] = line[0].substring(2);

        int attributeCount = 6; //how many features
        for (int i = 0; i < attributeCount; i++) {

            Attribute a = new Attribute(i);
            preds.add(a.englishOrDutch(line));

        }
        return new Example(preds, E, line);
    }


    private static int importance(ArrayList<Integer> attributes, ArrayList<Example> examples){//returns the index of the most important predicate

        //return the attribute with the most information gain

        if(attributes.size()== 1) return attributes.get(0);

        double[] d = new double[6];

        for (int i = 0; i < 6; i++) {

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

            int errs = 0;

            if(i == 3){//part 2

                //hypothesis = if !3, B
                for (Example e: examples
                ) {

                    if((e.predicates.get(i) && !e.englishOrDutch)){//if 3 and b

                        errs++;

                    }

                }
            }

            double errorrate = (double)errs/(200);

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

    //learn
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

            ArrayList<Integer> attributesRight = new ArrayList<>(attributes);
            ArrayList<Integer> attributesLeft = new ArrayList<>(attributes);    //recursion is weird

            //left (true) //examples are now how many are true with this attribute

            Tree left = learn_decision_tree(whichAreTrue(A, examples), attributesRight, examples);

            //right (false) //examples are now how many are false with this attribute

            Tree right = learn_decision_tree(whichAreFalse(A, examples), attributesLeft, examples);

            return new Tree(A, left, right, null);

        }


    }

    private static ArrayList<Example> whichAreTrue(int a, ArrayList<Example> examples){

        ArrayList<Example> temp = new ArrayList<>();

        for (Example e: examples
        ) {

            if(e.predicates.get(a)) temp.add(e);

        }

        return temp;

    }

    private static ArrayList<Example> whichAreFalse(int a,ArrayList<Example> examples){

        ArrayList<Example> temp = new ArrayList<>();

        for (Example e: examples
        ) {

            if(!e.predicates.get(a)) temp.add(e);

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
