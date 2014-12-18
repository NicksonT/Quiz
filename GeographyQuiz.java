/****************************************
 MINI PROJECT - GEOGRAPHY QUIZ
 AUTHOR: Nickson Thanda
 This is a geography quiz. Users can input their answers and get points
 they can also play with their friends.
 USES: Screen output, Keyboard input, variables, assignment, expressions, 
 nested loops, sort algorithms and file input/output
 ****************************************/
import javax.swing.*; 
import java.util.Random;
import java.io.*; 
public class GeographyQuiz {
    public static void main(String[] param)
    {
	fileInput();
        forLooper();
        System.exit(0);
    }

    public static void fileInput()
    {
	print("These are the scores from the previous games");
	try
	{
	        BufferedReader inputStream = new BufferedReader (new FileReader("mydata.txt"));
        	String s = inputStream.readLine();
		while (s != null)
		{
			print(s);
			s = inputStream.readLine();
		}
	}
	catch (IOException ioe)
	{
		print("Trouble reading from the file: " + ioe.getMessage());
	}
    }

    public static void fileOutput(int[]playerScores, int[]playersPlaying)
    {
	try
	{
		PrintWriter outputStream = new PrintWriter(new File("mydata.txt"));
		for(int i = 0; i<playersPlaying.length; i++)
		{
            		int currentPlayerScore = playerScores[i];
            		int stringPlayer = playersPlaying[i];
        		outputStream.print("Player " + stringPlayer + " got " + currentPlayerScore + " points\n");
		}
		outputStream.close();
	}
	catch (IOException ioe)
	{
		print("Trouble writing to the the file: " + ioe.getMessage());
	}
	
    }
    public static void forLooper()
    {
        final int numOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("How many people are playing?"));

        String[] questions =
                {
                        ", what continent is the United Kingdom in?",
                        ", what is the Capital of Germany?",
                        ", which country is Rome in?",
                        ", which country borders the United States of America to the North?"
                };
        String[] correctAnswers =
                {
                        "Europe",
                        "Berlin",
                        "Italy",
                        "Canada"
                };
        int[] playersPlaying = new int [numOfPlayers];

	for (int i = 0; i<numOfPlayers; i++)
	{
		playersPlaying[i] = i+1;  
	}
	
        int[] playerScores = new int [numOfPlayers];

        quiz(playerScores, questions, correctAnswers);
        rankings(playerScores, playersPlaying);

    }
    public static void quiz(int[] playerScores, String[] questions, String[] correctAnswers)
    {
        for (int i = 0; i <questions.length; i++)
        {
            for (int z = 0; z <playerScores.length; z++)
            {
                int player = z + 1;
                String currentPlayer = "Player " + player;
                String answer = stringInput(currentPlayer + questions[i]);
                if (answer.equals(correctAnswers[i]))
                {
                    playerScores[z] = rightAnswer(correctAnswers[i], playerScores[z]);
                }
                else
                {
                    wrongAnswer(correctAnswers[i]);
                }

            }
        }


    }
    public static int randomScore()
    {
        Random score = new Random();
        int answerScore = score.nextInt(5); /* Declares a new variable called answerScore that gets a random number from
         0 to 5 */
        return answerScore;
    }

    public static int rightAnswer(String answer, int score)
    {

        int value = randomScore();
        if (value <= 5) {
            print("You got it right, it is " + answer + ". You have been awarded three points for your answer!");
            return score + 3;
        }
        else
        {
            print("You got it right, it is " + answer + ". You have been awarded six points for your answer!");
            return score + 6;
        }
    }

    public static void wrongAnswer(String message)
    {
        print("Sorry, you got it wrong, the right answer is " + message);
    }

    public static void rankings(int[] playerScores, int[] playersPlaying)
    {
        print("I will now list each players score from highest to lowest");
        sortArray(playerScores, playersPlaying);
	int currentPlayerScore = 0;
	int stringPlayer = 0;
	fileOutput(playerScores,playersPlaying);
        for(int i = 0; i<playerScores.length; i++)
        {
            currentPlayerScore = playerScores[i];
            stringPlayer = playersPlaying[i];
            print("Player " + stringPlayer + " got " + currentPlayerScore + " points");	   
        }
    }

    public static void sortArray(int[] array, int[]anoArray)
    {
	for(int i = 0; i< array.length; i++)
        {
            if(i != array.length-1)
            {	
		if (array[i] < array[i + 1]) 
		{
                    swap(array, i, i+1);
                    swap(anoArray, i, i+1);
                }
            }

        }
    }

    public static void print(String message)
    {
        JOptionPane.showMessageDialog(null, message);
    }

    public static String stringInput(String message)
    {
        return JOptionPane.showInputDialog(message);
    }
    public static int[] swap(int[] array, int i, int z)
    {
    	int temp = array[i];
        array[i] = array[z];
        array[z] = temp;
    	return array;
    }
}
