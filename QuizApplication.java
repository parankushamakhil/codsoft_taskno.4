import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
public class QuizApplication {
    private static int timelimit=30;
    private static boolean timeup=false;
    private static StringBuilder summary=new StringBuilder();
    private static int score=0;
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("Welcome to the quiz!");
        System.out.println("You have "+timelimit+" seconds to answer each question.");
        String[] questions={
            "Who is the current president of India?\n1. A.P.J.Abdul Kalam\n2. Ram Nath Kovind\n3. Droupadi Murmu\n4. Pranab Mukherjee",
            "What is the capital of Russia?\n1. Moscow\n2. Delhi\n3.New York\n4. Paris",
            "Who wrote 'A Song of Ice and Fire'?\n1. William Shakespeare\n2. George R.R.Martin\n3. Agatha Christie\n4. J.K.Rowling",
            "What is 9999 + 11?\n1. 1000\n2. 10000\n3. 10010\n4. 10001",
            "Who invented the first printing press?\n1. Thomas Edison\n2. Wright brothers\n3. Nikola Tesla\n4. Johannes Gutenberg"
        };
        int[] answers={3,1,2,3,4};
        for(int i=0;i<questions.length;i++){
            Timer timer=new Timer();
            TimerTask task=new TimerTask() {
                @Override
                public void run(){
                    timeup=true;
                    System.out.println("\nTime's up for this question.");
                }
            };
            System.out.println(questions[i]);
            timer.schedule(task, timelimit*1000);
            String useranswers=s.nextLine();
            timer.cancel();
            int useranswer;
            try{
                useranswer=Integer.parseInt(useranswers);
            }catch(NumberFormatException e){
                System.out.println("Invalid input.");
                continue;
            }
            if(timeup){
                summary.append("Question").append(i+1).append(": Time up! (Correct Answer: ").append(answers[i]).append(")\n");
                timeup=false;
                continue;                
            }
            if(useranswer==answers[i]){
                System.out.println("Correct!");
                score+=10;
                summary.append("Question ").append(i+1).append(": Correct\n");
            }
            else{
                System.out.println("Incorrect");
                summary.append("Question ").append(i+1).append(": Incorrect (Correct Answer: ").append(answers[i]).append(")\n");
            }
        }
        System.out.println("\nQuiz over.");
        System.out.println("Summary: ");
        System.out.println(summary.toString());
        System.out.println("Your score: "+score);
        s.close();
    }
}