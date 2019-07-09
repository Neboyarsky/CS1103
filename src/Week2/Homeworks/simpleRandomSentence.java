package Week2.Homeworks;/*
 *Rules that this program follows
 *<sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
 *
 *<simple_sentence> ::= <noun_phrase> <verb_phrase>
 *
 *<noun_phrase> ::= <proper_noun> | 
 *<determiner> [ <adjective> ].<common_noun> [ who <verb_phrase> ]
 *
 *<verb_phrase> ::= <intransitive_verb> | 
 *<transitive_verb> <noun_phrase> |
 *is <adjective> |
 *believes that <simple_sentence>
 *
 *
 * This program follows these rules to generate random sentences
 * Random sentences are generated and output every two seconds
 * until the program is stopped. TO do this press "terminate"
 * in Eclipse or Control-C in a terminal window where the program
 * is running. 
 * 
 * @author Brittany
 * 
 *
 */


/*
 * creating and filling lists that correspond to different types of words that 
 * make up sentences
 * 
 */
public class simpleRandomSentence {
	//list for conjunction
	static final String[] conjun = {"and", "or", "but", "because", "if", "so","yet"};
	
	//list for proper nouns
	static final String[] prop_noun = {"Brittany","Kole", "Rollie","Smaug","Saphira"};
	
	//list for common nouns 
	static final String[] comm_noun = {"women","man", "cat","dragon","fairy"};
	
	//list for determiners
	static final String[] determiner = { "a","the","every","some"};
	
	//list of  adjectives
	static final String[] adjective = {"huge","small","beautiful","bold","brave"};
	
	//list for intrasitive verbs
	static final String[] intrasitive_verb = {"runs","flies","jumps","walks","growls"};
	
	//list for transitive verbs
	static final String[] transitive_verb = {"loves","hates","knows", "sees","finds","looks for"};
	
/*
 * 	Main method that Calls first subroutine to generate sentences
 * and continues with 2 seconds between each sentences
 * until user terminates program or an exception occurs
 * 
 */
	
	public static void main(String[]args) {
		
		
		while(true) {
			
			randomSentence();
		    System.out.println(".\n\n");
		    
		    
			try {
				Thread.sleep(2000);
			}
			catch (InterruptedException e){
				System.exit(0);
			}
			
			
		
		} 
	}
	
/*
 * Generates random sentence using noun and verb phrase
 * with 80% chance and recursively adding conjunctions to make
 * compound sentences 25% of the time.
 */
	static void randomSentence() {
	   if(Math.random()>0.2) {	
		randomNounLine();
		  randomVerbLine();
	   }
		if(Math.random()>0.75) {
			System.out.print(" "+ randomItem(conjun));
			randomSentence();
		}
	}
	
/*
 * Generates random noun phrase
 */
	static void randomNounLine() {
		if(Math.random()>0.5) {
			System.out.print(" "+ randomItem(prop_noun)); //displays proper noun 50% of the time
		}
		else {
			System.out.print(" "+ randomItem(determiner)); //or displays a determiner the other times
			
			if(Math.random()>0.5) {
				System.out.print(" "+ randomItem(adjective)+" ");//with a adjective
				System.out.print(" "+ randomItem(comm_noun));//common noun following the adjective
			}
			if (Math.random()>0.75) {  
				System.out.print(" who");//with who clause after determiner 25% of the times
				randomVerbLine();        //calls random verb generator
			}
		}
	}
	
/*
 * Generates random verb phrase	
 */
	
	static void randomVerbLine() {
		if(Math.random()>0.5) {
			System.out.print(" "+ randomItem(transitive_verb));	//display random transitive verb 50% of the time
		}
		
		else if (Math.random()>0.4) {
			System.out.print(" "+ randomItem(intrasitive_verb)); // or display random intrasitive verb 60% of the time
			randomNounLine();									//followed by random noun
		}
		
		else if (Math.random()>0.25) {
			System.out.print(" is " + randomItem(adjective)); //or display is followed by random adjective 75% of time
		}
		
		else {
			System.out.print(" knows that ");// or knows that with another random sentence
			randomSentence();
		}
	}
	
/*
 * Randomly selects a word from one of the lists,
 * 	
 */
	static String randomItem(String[]listOfStrings) {
		return listOfStrings[(int)(Math.random()*listOfStrings.length)];
	}
}