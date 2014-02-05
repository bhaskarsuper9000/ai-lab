/** Creates features out of a string
    Also removes unwanted words, '@' markers
    and returns a feature vector based on dictionary
*/

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;


class TweetToFeature {
    HashMap dict;   // = new HashMap<String, Integer>();
    String filters[] = {"is", "was", "to", "the", "a", "be", 
                        "all", "six", "less", "being", "indeed", 
                        "over", "move", "anyway", "not", "own", 
                        "through", "yourselves","we","i"};
    
    //constructor for preprocessing
    public TweetToFeature( HashMap dict, String fileName ) {
        try{
            this.dict = dict;
            Integer id = 0;
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()){
                String temp[] = fileScanner.nextLine().split("\\s");
                for(int i=0; i<temp.length; i++) {
                    temp[i] = temp[i].replaceAll("[^a-zA-Z0-9]","");    //remove all bad characters
                    temp[i] = temp[i].toLowerCase();
                    for(String filter : filters)
                        if(filter.equals(temp[i])){
                            temp[i] = "";
                        }
                    System.out.print(temp[i]+"|");
                    //System.out.println(fileScanner.nextLine());
                }
                System.out.println("");
            
                //Ditch temp[0] coz its the name of a handle
                for(int i=1; i<temp.length; i++){
                    if( !temp[i].equals("") ){
                        dict.put(temp[i], id++);
                    }
                }

                //System.out.println(dict.size());
            
            }
        }catch(FileNotFoundException e1){
            System.out.println("File Not Found :" + e1);
        }
    }

    public String preProcess ( String s ){
        String temp[] = s.split("\\s");
        String retString = "";
        for(int i=0; i<temp.length; i++) {
            temp[i] = temp[i].replaceAll("[^a-zA-Z0-9]","");    //remove all bad characters
            temp[i] = temp[i].toLowerCase();
            for(String filter : filters)
            if(filter.equals(temp[i])){
                temp[i] = "";
            }
            System.out.print(temp[i]+"|");
            if(!temp[i].equals("")){
                retString += temp[i] + " ";
            }
        }
        retString = retString.trim();
        return retString;
    }

    public int [] getFeature ( String s ){
        int a[] = new int [dict.size()];
        String temp[] = s.split("\\s");
        for(String t : temp) {
            if (dict.containsKey(t)) {
                a[ (int)dict.get(t) ] = 1;
            }
        }
                //a[ dict.get(t) ]++; //works for frequency
        return a;
    }

    //filters the given word by removing @,", etc.
    //Also returns null if the given word is in a list of filters
    public String filterWord(String word){
       String temp="";
       return temp;
    }

    public static void main(String args[]) {
        HashMap <String, Integer> dict = new HashMap<String, Integer>();
        TweetToFeature tweet1 = new TweetToFeature(dict, "twitter_positive");
        TweetToFeature tweet2 = new TweetToFeature(dict, "twitter_negative");
        TweetToFeature tweet3 = new TweetToFeature(dict, "twitter_objective");

        System.out.println( dict.size() );

        tweet1.getFeature("Hello World");

    }
}
