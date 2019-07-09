package Week2.Homeworks;

/*This program recursively generates random sentences
based on random elements like proper nouns, common nouns, conjunctions, .... */
public class SimpleRandomSentence2 {

   static final String[] conjunction = {"and", "or", "but", "because"};
   static final String[] proper_noun = {"Donald Trump", "Ricky Gervais", "Arnold Schwarzenegger","Harambe"};
   static final String[] common_noun = {"man", "woman", "fish", "elephant", "unicorn", "chicken", "dog"};  
   static final String[] determiner = {"a", "the", "every", "some"};
   static final String[] adjective = {"big", "tiny", "pretty", "bald", "unpredictable"};
   static final String[] intransitive_verb = { "runs", "jumps", "talks", "sleeps", "lied"};
   static final String[] transitive_verb = { "loves", "hates", "sees", "knows", "looks for", "finds", "married", "chased"};                                

    public static void main(String[] args) {
       while (true) {                             //calling the first subroutine for generating sentences
          simpleSentence();
       System.out.println(".\n\n");
          try {
              Thread.sleep(3000);
          }
          catch (InterruptedException e) {
          }
       }
    }
    

    static void simpleSentence() {
       randomNounPhrase();                        //generating basic sentence using noun and verb phrase
       randomVerbPhrase();
       if (Math.random() > 0.60) {                //recursively making sentences using conjunctions
               System.out.print(" " + randomItem(conjunction));
               simpleSentence();
       }
    }
    static void randomNounPhrase() {
          if (Math.random() > 0.50)              //displaying a proper noun first
              System.out.print(" " + randomItem(proper_noun));
          else {
              System.out.print(" " + randomItem(determiner));
              if (Math.random() > 0.50)
          System.out.print(" " + randomItem(adjective));
                 System.out.print(" " + randomItem(common_noun));
                  if (Math.random() > 0.75){      // an optional who clause with random verb phrase
        		randomVerbPhrase();
                       System.out.print(" who" );
                       randomVerbPhrase();
                  }
          }
    }
    static void randomVerbPhrase() {
           if (Math.random() > 0.50)
              System.out.print(" " + randomItem(intransitive_verb));
                 else if (Math.random() > 0.40) {   //displaying transitive verb
                         System.out.print(" " + randomItem(transitive_verb));
                         randomNounPhrase();       //displaying intransitive verb
                 }
                 else if (Math.random() > 0.30)    //"is" followed by an adjective
                     System.out.print(" is " + randomItem(adjective));
                 else {                            //"believes that" with another simple sentence
                     System.out.print(" believes that");
                     randomNounPhrase();
                     randomVerbPhrase();
                 }
        }
    static String randomItem(String[] listOfStrings){
        return listOfStrings[(int)(Math.random()*listOfStrings.length)];
    }
}