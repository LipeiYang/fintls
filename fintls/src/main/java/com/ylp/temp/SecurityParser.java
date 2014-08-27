package com.ylp.temp;

public class SecurityParser 
{
	public Security parse(String line)
	{
		Security s = new Security();
		s.setName(line.substring(0, 15));
		s.setHigh(Double.valueOf(line.substring(23, 31)));
		s.setLow(Double.valueOf(line.substring(31, 39)));
		s.setVol(Long.valueOf(line.substring(61, 70).trim()));
		return s;
	}
	
	public boolean isValid(String line)
	{
		return line.matches("^\\w{3}-\\d{2}.*$");
	}
	
	public static void main( String[] args )
    {
		String s  = "JUL-13   6400 C       0       0       0    2650     -105   52        0         0         0";
		String s2 = "JUL-13  20400 C     555     628     388     524     -184   23      212       202       +27";
		System.out.println(new SecurityParser().isValid(s));
		System.out.println(new SecurityParser().parse(s).getVol());
    }
}
