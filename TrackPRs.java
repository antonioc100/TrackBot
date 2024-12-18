public class TrackPRs 
{
    //instance variables for the person's PRs
    private int sixtyPR;
    private int hundredPR;
    private int twoHundredPR;
    private int fourHundredPR;
    private int eightHundredPR;
    private int sixteenHundredPR;
    private int thirtyTwoHundredPR;
    private int fiveKilometerPR;
    
    /**
    * returns the PR's number from the given object
    * Precondition: TrackPRs initialized
    * Postcondition: Returns PRname's value
    * 
    * @return PRname's value
    */
    
    public int getPR(String PRname){
        if (PRname.equals("60m")){return sixtyPR;}
        else if (PRname.equals("100m")){return hundredPR;}
        else if (PRname.equals("200m")){return twoHundredPR;}
        else if (PRname.equals("400m")){return fourHundredPR;}
        else if (PRname.equals("800m")){return eightHundredPR;}
        else if (PRname.equals("1600m")){return sixteenHundredPR;}
        else if (PRname.equals("3200m")){return thirtyTwoHundredPR;}
        else if (PRname.equals("5kPR")){return fiveKilometerPR;}
        
        return 0;
    }

    /**
    * sets user's PRtime to the instance variable of the event they requested
    * Precondition: TrackPRs initialized
    * Postcondition: set PRname time total
    */
    
    public void setPR(String PRname, int minPR, int secPR){
        int seconds = ((minPR*60) + secPR);
        
        if (PRname.equals("60m")){sixtyPR = seconds;}
        else if (PRname.equals("100m")){hundredPR = seconds;}
        else if (PRname.equals("200m")){twoHundredPR = seconds;}
        else if (PRname.equals("400m")){fourHundredPR = seconds;}
        else if (PRname.equals("800m")){eightHundredPR = seconds;}
        else if (PRname.equals("1600m")){sixteenHundredPR = seconds;}
        else if (PRname.equals("3200m")){thirtyTwoHundredPR = seconds;}
        else if (PRname.equals("5k")){fiveKilometerPR = seconds;}
    }

    /**
    * prints the the inputted time in seconds, converted to minutes and seconds
    * Precondition: parameters given
    * Postcondition: prints out the time in minutes/secs
    */
    
    public String toString(String event){
        int mins = getPR(event) / 60;
        int secs = getPR(event) % 60;
        
        return ("You run the " + event + " in " + mins + " minutes and " + secs + " seconds!");
    }
}
