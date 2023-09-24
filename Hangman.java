import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman
{
    public void play(String name,int length){
        double guesses=(int)1.5*length;
        int warnings=length;
        int index=0;         //index increases as we guess correct letters
        int position;        //position to put correct words in array
        double j;
        ArrayList<String>guessedchar=new ArrayList<>();     //arraylist to store correct letters
        
        for(int a=0;a<length;a++){            //fill the arraylist to store the letters with dashes
            guessedchar.add(a,"_");
        }
        
         ArrayList<String>already=new ArrayList<>();   //arraylist that contains already guessed letters
        Scanner sc=new Scanner(System.in);
        
        String[] arr=name.split("");
        ArrayList<String>word=new ArrayList<>();             //arraylist that contains letters of random word
        
        for(String letters:arr){
            word.add(letters);
        }
        String sample="abcdefghijklmnopqrstuvwxyz";
        
        String[] arr2=sample.split("");
        ArrayList<String>available=new ArrayList<>();   //arraylist for available letters
        
        for(String letters1:arr2){
            available.add(letters1);
        }
        
        for( j=guesses;j>=1;j--){
        System.out.println("you have "+warnings+" warnings left");    
        System.out.println("\nyou have "+j+" guesses left");
        System.out.println("Available letters:"+available);
        
        System.out.print("\nplease guess a letter:");
        String input=sc.next();
        input=input.toLowerCase();
        
        char c=input.charAt(0);          //convert input into character to see if it is a letter
        
        if(Character.isAlphabetic(c)){
            input=Character.toString(c);
            
            if(already.contains(input)){
                System.out.println("this letter is guessed before!");
                warnings--;
                j++;
                if(warnings==0){
                    j--;
                    }
            }
            
            else if(word.contains(input)){
            System.out.println("Good guess!");
            available.remove(input);
            already.add(input);
            index++;
            
            position=word.indexOf(input);
            guessedchar.remove(position);
            guessedchar.add(position,input);
            
            System.out.println(guessedchar);
            if(index==length){
                System.out.println("congratulations you won");
                break;
            }
        }
        
           else
        {
            available.remove(input);
           System.out.println("this letter is not in my word"); 
        }
        }
   else {
            System.out.println("WARNING!!invalid input");
            warnings--;
            j++;
            if(warnings==0){
                j--;
             
        }
    }              
}

   if((j<=0 && index!=length)||(j<=0 && index==0)){
            System.out.println("your tries are over !you lost");
            System.out.println("the correct word was:"+word);
        }    
        
}
   public static void main(String [] args){
       Hangman h=new Hangman();
       String word[]={"fact","game","fly"};
        Random rand=new Random();
       int random=rand.nextInt(3);
       
       String name=word[random];
       int wordlength=name.length();
       
       System.out.println("Welcome to Hangman! \n the length of Random word is:"+wordlength);
       h.play(name,wordlength);
       
   }
}
