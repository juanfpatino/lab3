import java.util.Arrays;

public class Attribute { //takes in a word, determines if it matches a certain attribute

    public int id;
    //1 = function word

    public Attribute(int id){

        this.id = id;

    }

    public boolean englishOrDutch(String[] words){ //TODO: DOCUMENT

        return switch (id) {
            case 0 -> isEnglishFunctionWord(words);
            case 1 -> weirdConsonants(words);
            case 2 -> isDutchFunctionWord(words);
            case 3 -> containsEnglishSuffixes(words);
            case 4 -> containsDutchSuffixes(words);
            case 5 -> containsDutchPrefixes(words);
            default -> false;
        };

    }

    //first attribute. function word?
    private boolean isEnglishFunctionWord(String[] words){

        String[] fWords = new String[]{"the", "a", "it", "he", "him", "she", "her",
        "his", "hers", "under", "towards", "before", "of", "for", "and", "but", "if", "then", "well",
        "however", "thus", "would", "could", "should", "up", "on", "down", "oh", "ah", "eh", "yes", "no", "okay", "hello"};

        for (String s: fWords
             ) {


            if(Arrays.asList(words).contains(s))return true;//english

        }

        return false;//dutch

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

    //third attribute. function word?
    private boolean isDutchFunctionWord(String[] words){

        String[] fWords = new String[]{"de", "een", "het", "hij", "hem", "zij", "haar",
                "zijn", "de hare", "onder", "richting", "voordat", "van", "voor", "en", "maar", "als", "dan", "goed",
                "echter", "dus", "zou", "zou kunnen", "zou moeten", "omhoog", "aan", "omlaag", "ja", "nee", "oke"};

        for (String s: fWords
        ) {

            if(Arrays.asList(words).contains(s))return false;//dutch

        }

        return true;

    }


    //fourth attribute. contains english suffixes
    private boolean containsEnglishSuffixes(String[] words){

        String[] fWords = new String[]{"acy", "al", "ance", "ence", "dom", "er", "or",
                "ism", "ist", "ity", "ment", "ness", "ship", "ate", "ify", "fy",
        "ize", "ise", "able", "ible", "esque", "ful", "ious", "ous", "less"};

        for (String s: words
        ) {

            for (String w: fWords
            ) {

                int fWordLength = w.length();
                int sLength = s.length();

                if(fWordLength > sLength) continue;

                String sEnd = s.substring(sLength - fWordLength);
                if(sEnd.equals(w)) return true; //english

            }

        }

        return false;//dutch

    }


    //fifth attribute. contains dutch suffixes
    private boolean containsDutchSuffixes(String[] words){

        String[] fWords = new String[]{"aal", "aan", "aar", "aard", "aat", "achtig",
                "antie", "baar", "daags", "drecth", "egge", "erig", "erij", "erik", "esse",
                "etje", "eus", "euse", "foob", "foon", "ganger", "gewijs", "grafie", "haftig",
                "heden", "iek", "ij", "isch", "iseren", "isme",
                "istiek", "iteit", "jarig", "jes", "kje", "kunde", "lei", "lieden", "lijk", "lijks", "lijn",
                "loge", "loog", "loos", "lui", "maan", "manie", "mannen",
                "matig", "meer", "mens", "mensen", "morfisme", "niem", "nitril", "noom", "ooat",
                "ologie", "pje", "sch", "schap", "sche", "thie", "ische", "tisch",
                "sfeer", "soom", "teit", "thiol", "tig", "tje", "uur",
                "vol", "vormig", "voud", "voudig", "vrij", "vrouw", "vrouwen", "vuldig", "waardig", "waarts", "wijs", "zaam", "zelf", "zinnig"};

        for (String s: words
        ) {

            for (String w: fWords
            ) {

                int fWordLength = w.length();
                int sLength = s.length();

                if(fWordLength > sLength) continue;

                String sEnd = s.substring(sLength - fWordLength);
                if(sEnd.equals(w)) {

                    return false; //dutch

                }

            }

        }

        return true;//english

    }


    //sixth attribute. contains dutch suffixes
    private boolean containsDutchPrefixes(String[] words){

        String[] fWords = new String[]{"aan", "aarts", "achter", "betoergroot", "bloed",
                "boven", "buiten", "carcino", "cyano", "elektro", "filo", "fono", "fysio",
                "groot", "hoofd", "huis-tuin-en-keuken-", "kanker", "kinesi", "klote", "kunst", "kut", "kwase",
                "mannetjes", "mede", "middel", "midden", "niet", "noorder",
                "onder", "oor", "opeen", "opper", "oud", "overgroot", "stief", "terug", "tering", "tussen", "lijn",
                "voor", "voort", "vooruit", "vrouwtjes", "weder", "weer", "yocto",
                "yotta", "zelf", "zepto", "zetta", "zuider"};

        for (String s: words
        ) {

            for (String w: fWords
            ) {

                int fWordLength = w.length();
                int sLength = s.length();

                if(fWordLength > sLength) continue;

                String start = s.substring(0,fWordLength);
                if(start.equals(w)) {

                    return false; //dutch

                }

            }

        }

        return true;//english

    }



}
