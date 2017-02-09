package nl.project34.henk.client;

public class Decode
{
	public static String decoden(String s)
	{
		String convert = "";
		String decoded = "";
		for(char c : s.toCharArray())
		{
			if(c != '.')
			{
				convert += c;
			}else{
				int tijdelijk = Integer.parseInt(convert);
				tijdelijk -= 23;
				decoded += (char)(tijdelijk);
				convert = "";
			}
		}
		return decoded;
	}
}