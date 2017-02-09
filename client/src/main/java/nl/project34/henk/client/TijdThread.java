package nl.project34.henk.client;

/**
 * Created by ShanePereira on 05/04/16.
 */
public class TijdThread extends Thread
{
    protected long startTime;
    protected static boolean isRunning = false;
    protected static int time;
    private int ms;

    public void start(){}

    public void run()
    {
        while(true)
        {
            while(isRunning)
            {
                if(System.nanoTime() - startTime >= 10000000)
                {
                    startTime = System.nanoTime();

                    ms++;                                                    //milis
                    time = ms / 100;                                         // seconds
                }
            }
        }
    }

//    public void setRunning(boolean b)
//    {
//        isRunning = b;
//    }

//    public int getTime()
//    {
//        return this.time;
//    }

}