public class TrackSplit {
    
    /**
    * prints the split's of a given distance
    * Precondition: TrackSplit initialized
    * Postcondition: Prints PRname's value
    */
    
    public void splitCalculator(String event, int mins, int secs)
    {
        double divider = 0;
        double hundredSplit;
        double twoHundredSplit;
        double fourHundredSplit;
        
        double totalTime = ((mins*60) + secs);
        
        if (event.equals("800m")){divider = 8;}
        else if (event.equals("1600m")){divider = 16;}
        else if (event.equals("3200m")){divider = 32;;}
        else if (event.equals("5k")){divider = 50;}
        
        hundredSplit = (totalTime/divider);
        twoHundredSplit = (hundredSplit*2);
        fourHundredSplit = (twoHundredSplit*2);

        System.out.println("100m Split: " + hundredSplit);
        System.out.println("200m Split: " + twoHundredSplit);
        System.out.println("400m Split: " + fourHundredSplit);
    }
}
