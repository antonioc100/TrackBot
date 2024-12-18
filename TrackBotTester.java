import java.util.Scanner;

/**
 * 
 */
public class TrackBotTester
{
	public static void main(String[] args)
	{
	    //create new TrackBot object
		TrackBot myBot = new TrackBot();
		
		//This indicates that chatbot is talking.
		System.out.println("T > " + myBot.greeting());
		
		Scanner input = new Scanner(System.in);
		
		String statement = input.nextLine();
		
		//If you don't say bye, it will keep running until you say "Bye".
		//Then the chatbot will stop running.
		while (!statement.equals("Bye"))
		{
			System.out.println("T > " + myBot.getResponse(statement));
			statement = input.nextLine();
		}
		
		input.close();
	}

}
