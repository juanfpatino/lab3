import java.util.Arrays;

public class Attribute { //takes in a word, determines if it matches a certain attribute

    public int id = 0;
    //1 = function word

    public Attribute(int id){

        this.id = id;

    }

    public boolean englishOrDutch(String[] words){

        switch (id){

            case 1:
                return isEnglishFunctionWord(words);
            case 2:
                return weirdConsonants(words);
            default:
                return false;

        }

    }

    //first attribute. function word?
    private boolean isEnglishFunctionWord(String[] words){

        String[] fWords = new String[]{"the", "a", "it", "he", "him", "she", "her",
        "his", "hers", "in", "under", "towards", "before", "of", "for", "and", "but", "if", "then", "well",
        "however", "thus", "would", "could", "should", "up", "on", "down", "oh", "ah", "eh", "yes", "no", "okay"};

        for (String s: fWords
             ) {

            if(Arrays.asList(words).contains(s))return true;

        }

        return false;

    }

    //second attribute. consonants out weird order?
    private boolean weirdConsonants(String[] words){

        String[] fWords = new String[]{"wd", "tg", "fb", "kt", "jn", "rz", "jv", "fd", };


        for (String s: fWords
        ) {

            for (String w: words
                 ) {

                if(w.contains(s)) return false; //dutch

            }

        }

        return true;//english

    }

}
