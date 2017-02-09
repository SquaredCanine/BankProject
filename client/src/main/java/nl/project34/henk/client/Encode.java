package nl.project34.henk.client;

public class Encode
{
	public static String encoden(String s)
	{
		String converted = "";
    	for (char c : s.toCharArray())
    	{
    		int tijdelijk = ((int)c) + 23;
    		converted += (tijdelijk + ".");
    	}
    	System.out.println(converted);
    	return converted;
	}
}