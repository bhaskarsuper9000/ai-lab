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
    String filters[] = {"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"};
                        
                        
    
    //constructor for preprocessing
    public TweetToFeature( HashMap dict, String fileName ) {
        try{
            this.dict = dict;
            Integer id = 0;
            Scanner fileScanner = new Scanner(new File(fileName));
            while (fileScanner.hasNextLine()){
 
            	String nxt = fileScanner.nextLine();
            	//System.out.println( nxt.indexOf('\t') );
            	String handleLess = nxt.substring(nxt.indexOf('\t')+1,nxt.length());
                String temp[] = handleLess.split("\\s");
 
                for(int i=0; i<temp.length; i++) {
                    temp[i] = temp[i].replaceAll("[^a-zA-Z@#]","");    //remove all bad characters
                    temp[i] = temp[i].toLowerCase();
                    for(String filter : filters)
                        //Suffix funda here
                        temp[i] = temp[i].substring();
                        if(filter.equals(temp[i])){
                            temp[i] = "";
                        }
                    //System.out.print(temp[i]+"|");
                    //System.out.println(fileScanner.nextLine());
                }
                System.out.println("");
            
                //Ditch temp[0] coz its the name of a handle
                for(int i=0; i<temp.length; i++){
                    	
                    if( !temp[i].equals("") ){
                    		if( temp[i].indexOf('@')!=-1  || temp[i].indexOf('#')!=-1 )
                    			continue;
                    	System.out.print(temp[i]+"|");
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
    
    	String handleLess = s.substring(s.indexOf('\t')+1,s.length());
        String temp[] = handleLess.split("\\s");
        
        String retString = "";
        for(int i=0; i<temp.length; i++) {
            temp[i] = temp[i].replaceAll("[^a-zA-Z@#]","");    //remove all bad characters
            temp[i] = temp[i].toLowerCase();
            for(String filter : filters)
            if(filter.equals(temp[i])){
                temp[i] = "";
            }
            System.out.print(temp[i]+"|");
            if(!temp[i].equals("")){
            	if( temp[i].indexOf('@')!=-1  || temp[i].indexOf('#')!=-1 )
                    continue;
                retString += temp[i] + " ";
            }
        }
        retString = retString.trim();
        return retString;
    }

    public int [] getFeatureWithOutput ( String s, int output ){
    	s = preProcess(s);
    
        int a[] = new int [dict.size()+1];
        String temp[] = s.split("\\s");
        for(String t : temp) {
            if (dict.containsKey(t)) {
                a[ (int)dict.get(t) ] = 1;
            }
        }
        a[dict.size()] = output;
                //a[ dict.get(t) ]++; //works for frequency
        return a;
    }
    
    public int [] getFeatureWithoutOutput ( String s ){
    	s = preProcess(s);
    	System.out.println(s);
    
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
    
    int nTweets(String s){
    	int n = 1000;
    	if(s.equals("twitter_positive"))
    		n = 182;
    	else if(s.equals("twitter_negative"))
    		n = 177;
    	else if(s.equals("twitter_objective"))
    		n = 139;
    	return n;
    }
    
    int [][] getFeaturesFromTweet(String fileName){
    	int [][] fv = new int [ nTweets(fileName) ][];
		try{
			Scanner fileScanner = new Scanner(new File(fileName));
			int i = 0;
		    while (fileScanner.hasNextLine()){
		        String nxt = fileScanner.nextLine();
		        nxt = preProcess(nxt);
		        	
		        fv[i] = getFeatureWithoutOutput(nxt);
		        i++;
		    }
        }catch(FileNotFoundException e1){
            System.out.println("File Not Found :" + e1);
        }
        return fv;
    }
    
    int [][] getAnnotatedFeaturesFromTweet(String fileName, int output){
    	int [][] fv = new int [ nTweets(fileName) ][];
    	try{
    		Scanner fileScanner = new Scanner(new File(fileName));
    		int i = 0;
            while (fileScanner.hasNextLine()){
            	String nxt = fileScanner.nextLine();
            	nxt = preProcess(nxt);
            	
            	fv[i] = getFeatureWithOutput(nxt,output);
            	i++;
            } 
        }catch(FileNotFoundException e1){
            System.out.println("File Not Found :" + e1);
        }
        return fv;
    }

    public static void main(String args[]) {
		//Preprocessing : Pass 1
        HashMap <String, Integer> dict = new HashMap<String, Integer>();
        TweetToFeature tweet1 = new TweetToFeature(dict, "twitter_positive");
        TweetToFeature tweet2 = new TweetToFeature(dict, "twitter_negative");
        TweetToFeature tweet3 = new TweetToFeature(dict, "twitter_objective");

        System.out.println( dict.size() );

        int a[] = tweet1.getFeatureWithOutput("jquery	Jquery is my new best friend.",1); 
        for(int t : a)
        System.out.print(t+",");
        
        //Pass 2
        int fvector[][] = tweet1.getFeaturesFromTweet("twitter_positive");
		int fvector2[][] = tweet1.getAnnotatedFeaturesFromTweet("twitter_negative",2);
    }
}
