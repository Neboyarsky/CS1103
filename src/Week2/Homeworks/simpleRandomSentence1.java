package Week2.Homeworks;

public class simpleRandomSentence1 {
	static String[] conjuction= new String[] {"and ", "or ","but", "because"};
	static String[] properNoun= new String[] {"Fred", " Jane "," Richard Nixon"," Miss America"};
	static String[] commonNoun= new String[] { "man"," woman"," fish "," elephant","unicorn"};
	static String[] determiner = new String[] {" a"," the "," every "," some"};
	static String[] adjective=new String[] {"big "," tiny "," pretty"," bald"};
	static String[] intransiveVerb=new String[] { "runs", "jumps"," talks","sleeps"};
	static String[] transitiveVerb=new String[] {"loves"," hates "," sees"," knows "," looks for "," finds"};

      public static void main(String[] args) {
    	  
    	  theSentence();
    	
    	// a subroutine(the sentence) to generate either a  sentence or a simple sentence;  
    	 
      }static public void theSentence() {
    	  double s=((Math.random()*2));
    	  if(s==1) {
    		  randomSentence();
    	  }else {
    		  randomSimpleSentence();
    	  }
    	 // a s subroutine to generate a randomSentence;
    	  
      }static public void randomSentence() {
    	  randomSimpleSentence();
    	  System.out.print(" "+conjuction[(int)(Math.random()*conjuction.length)]+" ");
    	  randomSentence();
    	  
    	  
      } static public void randomSimpleSentence() {
    	  randomNounPhrase();
    	  randomVerbPhrase();
    	  
      } static public  void   randomNounPhrase() {
    	  int np = (int)(Math.random()*5)+1;
    	  if(np>2) {
    		  System.out.print(properNoun[(int)(Math.random()*properNoun.length)]+" ");
    	  }else {
    		  System.out.print(determiner[(int)(Math.random()*determiner.length)]
    				  +" "+adjective[(int)(Math.random()*adjective.length)]+" "+commonNoun[(int)(Math.random()*commonNoun.length)]
    						  +"  "+"who"+"  ");
    		  randomVerbPhrase();
    	  }
    	  
      } static public void randomVerbPhrase() {
    	  int vp=(int)(Math.random()*4)+1;
    	  if(vp==1) {
    		  System.out.print(" "+intransiveVerb[(int)(Math.random()*intransiveVerb.length)]);
    	  }else if(vp==2) {
    		  System.out.print(" "+transitiveVerb[(int)(Math.random()*transitiveVerb.length)]+" ");
    		  randomNounPhrase();
    		  System.out.print(" "+properNoun[(int)(Math.random()*properNoun.length)]);
    	  }else if(vp==3) {
    		  System.out.print(" "+"is"+adjective[(int)(Math.random()*adjective.length)]);
    	  }else {
    		  System.out.print(" "+"believes that"+" ");
    		  randomSimpleSentence();
    		  
    	  }
    	  
      }

}
