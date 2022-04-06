import java.util.Arrays;

public class Attribute { //takes in a word, determines if it matches a certain attribute

    public int id;
    //1 = function word

    public Attribute(int id){

        this.id = id;

    }

    public boolean englishOrDutch(String[] words){

        return switch (id) {
            case 1 -> isEnglishFunctionWord(words);
            case 2 -> weirdConsonants(words);
            case 3 -> isDutchFunctionWord(words);
            default -> false;
        };

    }

    //first attribute. function word?
    private boolean isEnglishFunctionWord(String[] words){

        String[] fWords = new String[]{"the", "a", "it", "he", "him", "she", "her",
        "his", "hers", "under", "towards", "before", "of", "for", "and", "but", "if", "then", "well",
        "however", "thus", "would", "could", "should", "up", "on", "down", "oh", "ah", "eh", "yes", "no", "okay"};

        for (String s: fWords
             ) {

            String sBeginningOfSentence = Character.toUpperCase(s.charAt(0)) + s.substring(1); //i.e. hello -> Hello

            if(Arrays.asList(words).contains(s) || Arrays.asList(words).contains(sBeginningOfSentence))return true;//english

        }

        return false;//dutch

    }

    //third attribute. function word?
    private boolean isDutchFunctionWord(String[] words){

        String[] fWords = new String[]{"de", "een", "het", "hij", "hem", "zij", "haar",
                "zijn", "de hare", "onder", "richting", "voordat", "van", "voor", "en", "maar", "als", "dan", "goed",
                "echter", "dus", "zou", "zou kunnen", "zou moeten", "omhoog", "aan", "omlaag", "ja", "nee", "oke"};

        for (String s: fWords
        ) {

            String sBeginningOfSentence = Character.toUpperCase(s.charAt(0)) + s.substring(1); //i.e. haar -> Haar

            if(Arrays.asList(words).contains(s) || Arrays.asList(words).contains(sBeginningOfSentence))return false;//dutch


        }

        return true;

    }

    //second attribute. consonants follow dutch patterns?
    private boolean weirdConsonants(String[] words){

        String[] fWords = new String[]{"wd", "tg", "fb", "kt", "jn", "rz", "jv", "fd", "jk", "jl", "gv", "kk", "gt", "fv", };

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
