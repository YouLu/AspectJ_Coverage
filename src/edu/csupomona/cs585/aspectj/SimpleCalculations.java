package edu.csupomona.cs585.aspectj;
import java.util.Random;

public class SimpleCalculations {
	public static void main(String[] args)
	{
		System.out.println("calling simple calculations");
		Random rand = new Random();
		double sine = Math.sin(rand.nextInt());
		double cosine = Math.cos(rand.nextInt());
		double tangent = Math.tan(rand.nextInt());
		System.out.println("sin: " + sine);
		System.out.println("cos: " + cosine);
		System.out.println("tan: " + tangent);
		doStuff(rand.nextInt(10000));
		doStuff(rand.nextInt(10000));
		doStuff(rand.nextInt(10000));		
		makeStuff(rand.nextInt(100));
	}
	
	public static int doStuff(int times)
	{
		System.out.println("Doing stuff " + times + " times.");
		for (int ndx = 0; ndx < times; ndx++)
			Math.cos(ndx);	
		return times;
	}

	public static void makeStuff(int times)
	{
		System.out.println("Making stuff " + times + " times.");
		for (int ndx = 0; ndx < times; ndx++)
		{
			String str = new String("Number" + ndx);
			Math.tan(str.length());
		}
	}
}

