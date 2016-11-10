package PocketGem;

import java.util.Set;

public class WordBreak {
	public boolean wordBreak(String s, Set<String> wordDict) {
	       boolean[] t = new boolean[s.length()+1];
	       t[0]=true;
	       for(int i=0; i<=s.length(); i++){
	           for(int j=i-1; j>=0; j--){
	               if(t[j]&&wordDict.contains(s.substring(j,i))){
	               t[i]=true;
	               break;
	               }
	           }
	       }
	       return t[s.length()];
	    }
}
