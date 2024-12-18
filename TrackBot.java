import java.util.Scanner;

public class TrackBot
{
    //new scanner is created
    Scanner input = new Scanner(System.in);
    TrackPRs myPRs = new TrackPRs();
    TrackSplit mySplits = new TrackSplit();

	/**
	 * Gets a default greeting about whether you like running or not.
	 * @return String
	 */
	
	public String greeting()
	{
		return "Hey, what's up? You like to run?";
	}
	
	/**
	 * Resturns a response to a user statement
	 * 
	 * @param statement
	 * @return response
	 */
	
	public String getResponse(String statement)
	{
		String response = "";
		if (findKeyword(statement, "no") >= 0)
		{
		    response = "I don't like you!";
		    //converstion ends
		}
		
        else if (findKeyword(statement, "PR", 0) >= 0)
        {
            // asks the user if they want to access or mutate their PR
            System.out.println("Would you like to (add) a PR to (get) a pr?");
            String type = input.nextLine();
            
            // mutating their PR
            if (type.equals("add"))
            {
                // asks user for the specific event and their time
                System.out.println("What's your PR? (60m, 100m, 200m, 400m, 800m, 1600m, 3200m, 5k)");
                String event = input.nextLine();
                System.out.println("How many minutes did it take to run your event (if any)?");
                int mins = Integer.parseInt(input.nextLine());
                System.out.println("How many seconds did it take to run your event?");
                int secs = Integer.parseInt(input.nextLine());
                
                // checks if the event is a valid event, if not, stops the loop
                if ((event.equals("60m")) || (event.equals("100m")) || (event.equals("200m")) || (event.equals("400m")) || (event.equals("800m")) || (event.equals("1600m")) || (event.equals("3200m")) || (event.equals("5k")))
                {
                    // sets the specific event then responds the bot
                    myPRs.setPR(event, mins, secs);
                    response = (event + " added to your PRs!");
                }
                else
                {
                    response = "Please add in a correct event!";
                }
            }
            // accessing their PR
            else if (type.equals("get"))
            {
                // asks user for the specific event
                System.out.println("Which PR do you want to retrieve? (60m, 100m, 200m, 400m, 800m, 1600m, 3200m, 5k)");
                String event = input.nextLine();
                
                // checks if the event is a valid event, if not, stops the loop
                if ((event.equals("60m")) || (event.equals("100m")) || (event.equals("200m")) || (event.equals("400m")) || (event.equals("800m")) || (event.equals("1600m")) || (event.equals("3200m")) || (event.equals("5k")))
                {
                    // gets the specific event and has the bot respond
                    response = myPRs.toString(event);
                }
                else
                {
                    response = "Please add in a correct event!";
                }
            }
            else
            {
                response = "Didn't choose one";
            }
        }
        
        else if (findKeyword(statement, "Split", 0) >= 0)
        {
            // gets the user's information about which event, and times
            System.out.println("What event do you want to find a split in? (800m, 1600m, 3200, 5k)");
            String event = input.nextLine();
            System.out.println("How many minutes do you want to run the event in?");
            int mins = Integer.parseInt(input.nextLine());
            System.out.println("How many seconds do you want to run the event in");
            int secs = Integer.parseInt(input.nextLine());
            
            // checks if it is a valid event
            if ((event.equals("800m")) || (event.equals("1600m")) || (event.equals("3200m")) || (event.equals("5k")))
            {
                // calculator prints it out and track bot responds
                mySplits.splitCalculator(event, mins, secs);
                response = "Here are your splits for the " + event;
            }
            else
            {
                response = "Please add in a correct event!";
            }
        }
	    else if (
	        findKeyword(statement, "400m") >= 0 ||
		    findKeyword(statement, "800m") >= 0 ||
		    findKeyword(statement, "1600m") >= 0 ||
		    findKeyword(statement, "3200m") >= 0 ||
		    findKeyword(statement, "5k") >= 0
        )
        //keywords running chatbot will look for
        //and will respond to accordingly.
        {
            response = "You're a long distance runner!";
        }
		else if (
		    findKeyword(statement, "60m") >= 0 ||
		    findKeyword(statement, "100m") >= 0 ||
		    findKeyword(statement, "200m") >= 0
		)
	    {
	        response = "You're a short distance runner!";
	    } 
		else if (
		    findKeyword(statement, "fast") >= 0 ||
		    findKeyword(statement, "good") >= 0
		)
	    {
	        response = "Are you in track?!";
	    } 
	    else if (
	        findKeyword(statement, "cross country") >= 0 ||
		    findKeyword(statement, "track") >= 0 ||
		    findKeyword(statement, "XC") >= 0
        )
        {
            response = "Hey that's my favorite sport(s)!!!";
        }
        else if (
            findKeyword(statement, "ran a mile") >= 0
        )
        {
            response = "How fast did you run?";
        }
        else if (
            findKeyword(statement, "running related") >= 0 ||
            findKeyword(statement, "running-related") >= 0 ||
            findKeyword(statement, "related to running") >= 0
        )
        {
            response = "Oh my bad. If I say that again than I'm crazy.";
        }
        else if (
            findKeyword(statement, "Montgomery Hill") >= 0
        )
        {
            response = "Did you say Montgomery Hill? That's the worse place I've ever ran at!!!";
        }
        else if (findKeyword(statement, "I want to", 0) >= 0)
        {
            response = transformIWantToStatement(statement);  
        } 
        else if (findKeyword(statement, "I want to run", 0) >= 0)
        {
            response = transformIWantToRunStatement(statement);  
        }
        else if (findKeyword(statement, "I ran") >= 0)
        {
            response = transformIRanStatement(statement);
        } else {
            if (hasXThenY(statement, "My Coach, named", "is pretty strict."))
            {
                response = transformStrictCoachStatement(statement);
            } else {
                response = randomResponse();
            }
        }
		return response;
	}
	
	/**
	 * Makes sure that chatbot ignores spaces.
	 * Adds spaces if need to user input to make appropriate output response.
	 */
	public int findKeyword(String statement, String goal, int startPos)
	{
	    String phrase = statement.trim().toLowerCase();
	    goal = goal.toLowerCase();
	    int position = phrase.indexOf(goal, startPos);
	    
	    while (position >= 0)
	    {
	        String before = " ";
	        String after = " ";
	        if (position > 0)
	        {
	            before = phrase.substring(position - 1, position).toLowerCase();
	        }
	        if (position + goal.length() < phrase.length())
	        {
	            after = phrase.substring(position + goal.length(),
	                                     position + goal.length() + 1).toLowerCase();
	        }
	        // uncomment to view the values of the variables
	        // System.out.println( " psn: " + position + " before: '" + before + "' after: '" + after + "'");
	        if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0)) &&
	            ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))     
            {
                return position;
            }    
            position = phrase.indexOf(goal, position + 1);
	    }
	    return -1;
	}
    
    /**
     * Returns the keyword if found - takes it without any startingpos
     * @param statement - statement the user entered
     * @param goal - finds the specific goal statement
     * @returns the proper output response at a starting position of 0
     */
	private int findKeyword(String statement, String goal)
	{
	    return findKeyword(statement, goal, 0);
	}

    /**
     * Says "That's great that you ran (what user says)"
     * @param statement - what the user says
     * @return transformSingle(statement) - chatbots response to user.
     */
	private String transformIRanStatement(String statement)
	{
	    return transformSingle(statement, "I ran ", "That's great that you ran ", ".");
	}
    
    /**
     * Says "Have a safe trip to (what user says)"
     * @param statement - what the user says
     * @return transformSingle(statement) - chatbots response to user.
     */
	private String transformDrivingStatement(String statement) // my new statement
	{
	    return transformSingle(statement, "I am driving to ", "Have a safe trip to ", "!");
	}
	
	/**
     * Says "I don't want to (what user says)"
     * @param statement - what the user says
     * @return transformSingle(statement) - chatbots response to user.
     */
	private String transformIWantToStatement(String statement)
	{
	    return transformSingle(statement, "I want to ", "I don't want to ", ".");
	}
	
	/**
     * Says "Let's not run to (what user says)"
     * @param statement - what the user says
     * @return transformSingle(statement) - chatbots response to user.
     */
	private String transformIWantToRunStatement(String statement)
	{
	    return transformSingle(statement, "I want to run ", "Let's not run to ", ".");
	}

    /**
     * the substring length needs to be trimed so that the program can read only the whole string the user entered.
     * or else there would be an off-by-one error.
     */
	private String transformSingle(String statement, String keyword, String before, String after)
	{
	    statement = statement.trim();
	    String lastChar = statement.substring(statement.length() - 1);
	    if (lastChar.equals("."))
	    {
	        statement = statement.substring(0, statement.length() - 1);
	    }
	    int position = findKeyword(statement, keyword, 0);
	    String restOfStatement = statement.substring(position + keyword.length()).trim();
	    return before + restOfStatement + after;
	}
	
	/**
     * Agrees that the coach the user puts in is tough. But then lists their advantage."
     * @param statement - what the user says
     * @return transformSingle(statement) - chatbots response to user.
     */
	private String transformStrictCoachStatement(String statement)
 	{
 	    return transformInner(statement, "My Coach, named", "is pretty strict", "I heard that ", " is a very strict coach. But because of his toughness, the athletes do very well!");
 	}
	
	/**
	 * Returns if the two strings are true or above 0
	 * @param statement - the user statement
	 * @param first - the first string entered
	 * @param second - the second string entered
	 * @return the boolean if the two strings have the keywords
	 */
	private boolean hasXThenY(String statement, String first, String second)
	{
	    int position = findKeyword(statement, first, 0);
	    return position >= 0 && findKeyword(statement, second, position) >= 0;
 	}
 	
 	/**
     * Returns the statement after the statement is transformed from the middle
     * @param statement - user statement
     * @param starter - the starting goal of the statement
     * @param ender - the goal of the statement 
     * @param before - the before statement
     * @param after - the after part of the statement
     * @returns the transformation of in the inner
     */
 	private String transformInner(String statement, String starter, String ender, String before, String after)
 	{
 	    statement = statement.trim();
		String lastChar = statement.substring(statement.length() - 1);
		if (lastChar.equals("."))
		{
			statement = statement.substring(0, statement.length() - 1);
		}
		
		int psnOfStart = findKeyword (statement, starter, 0);
		int psnOfEnd = findKeyword (statement, ender, psnOfStart + starter.length());
		
		String restOfStatement = statement.substring(psnOfStart + starter.length(), psnOfEnd).trim();
		return before + restOfStatement + after;
 	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return String
	 */
	private String randomResponse()
	{
		int NUMBER_OF_RESPONSES = 6;
		double responseIndex = Math.random();
		int whichResponse = (int)(responseIndex * NUMBER_OF_RESPONSES);
		String response = "";
		
		if (whichResponse == 0)
		{
			response = "Very cool!";
		}
		else if (whichResponse == 1)
		{
			response = "Tell me more about that.";
		}
		else if (whichResponse == 2)
		{
			response = "That's really interesting!";
		}
        else if (whichResponse == 3)
		{
			response = "Can we talk about something running-related?";
		}
		else if (whichResponse == 4)
		{
			response = "You must be very fast person!!!";
		}
		else if (whichResponse == 5)
		{
			response = "You really like to run, don't you?";
		}
		else if (whichResponse == 6)
		{
		    response = "Is your coach tough. I'm assuming you're in XC or track?";
		}
		return response;
	}
}
