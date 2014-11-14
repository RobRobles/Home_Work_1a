package hw_1a;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class hw_1a {

	public static ArrayList<String> gradeBook; 
	public int count; 
	public ArrayList<Float> GPA;
	public String parse; 
	public boolean found = false; 
	public int K = 3; 

	public hw_1a(){
		
		
	} 
	
	public void readFile() throws IOException
	{

		gradeBook = new ArrayList<String>();

		@SuppressWarnings("resource")
		Scanner input = new Scanner(new File("gradebook.txt"));
		count = 0;

		while(input.hasNext())
		{
			gradeBook.add(input.nextLine());
			count++;
		}
	}

	public void printData()
	{
		for(int i = 0; i < count; i++)
		{
			System.out.println(gradeBook.get(i));
			//System.out.print(" ");

			//look for a GPA value and if there is a value print a newline 
			boolean isGPA = true; 

			//I am going to try and parse a double and if I can then I must have a GPA Value
			//If I cannot I must be looking at a first or last name
			//If I find a GPA I will put a newline to replicate the gradeBook format. I will
			//use this try catch to add up all of the GPA's

			try
			{
				Double.parseDouble(gradeBook.get(i));
				isGPA = true; 
			}
			catch (NumberFormatException exception)
			{
				//test is just a first or last name 
				isGPA = false; 
			}

			//checking for GPA to add a newline 
			if(isGPA == true)
			{
				//System.out.println("");
			}
		}
	}

	public void computeAverageGPA()
	{

		GPA = new ArrayList<Float>();
		//boolean isGPA = true; 
		parse = "";
		String tempGPA = "";
		//this is the average
		float average = 0; 
		float temp = 0; 

		for(int i = 0; i < count; i++)
		{
			//Using this try catch to find the GPA in the gradeBook and 
			//adding them to a seperate GPA float arraylist for computing 
			parse = gradeBook.get(i); 
			tempGPA = parse.substring((parse.length() - 3), (parse.length()));

			GPA.add(Float.valueOf(tempGPA));

			//I tried this as a parsing method for a different way I added the names and gpa to an arraylist 
			/*
			try
			{
				Float.parseFloat(gradeBook.get(i));
				isGPA = true; 
			}
			catch (NumberFormatException exception)
			{
				//test is just a first or last name 
				isGPA = false; 
			}
			 */
		}

		//going through the GPA arraylist and adding up all of the GPA's to be divided by the total amount
		for(int j = 0; j < GPA.size(); j++)
		{
			temp = GPA.get(j);
			average = average + temp;
		}

		average = (average / GPA.size());
		
		//spacer 
		System.out.println("");
		
		System.out.println(average);
	}

	public void getTopK()

	{ 
		ArrayList<String> Kay = new ArrayList<String>(); 
		//sort the list of GPA's and then print out the number k 
		Collections.sort(GPA);
		Collections.reverse(GPA);
		String parse1 = "";
		float temp = 0; 

		//walking through the GPA float list and comparing them to the list of gpa's that include the first and last name. 
		for(int w = 0; w < GPA.size(); w++)
		{	
			for(int x = 0; x < gradeBook.size(); x++)
			{
				parse1 = gradeBook.get(x);
				temp = Float.parseFloat(parse1.substring((parse1.length() - 3),  (parse1.length()))); 

				if(GPA.get(w) == temp)
				{
					//Once we find matching gpa's we will add the first last name and gpa to the Kay ArrayList which will be a sorted (by gpa) 
					//arraylist 
					Kay.add(parse1); 
					break;
				}
			}
		}

		//spacer
		System.out.println("");
		
		for(int i = 0; i < K; i++)
		{
			System.out.println(Kay.get(i)); 
		}

	}

	public void getTopKYear(ArrayList<String> Input, int k, String Year)
	{
		ArrayList<String> KayYear = new ArrayList<String>(); 

		Input = gradeBook; 

		//parsing temp for GPA
		String parse1 = "";
		//graping the parsed GPA and putting it into temp
		float temp = 0; 
		//parsing holder for Year
		String tempYear = ""; 
		//grabing the year value and putting into parseYear
		String parseYear = ""; 

		//walking through the GPA float list and comparing them to the list of gpa's that include the first and last name. 
		for(int w = 0; w < GPA.size(); w++)
		{	
			for(int x = 0; x < Input.size(); x++)
			{
				//getting each item out of the arrayList so it can be parsed out for GPA value
				parse1 = Input.get(x);
				//getting each item out of the arrayList so it can be parsed out for grade Year
				tempYear = Input.get(x); 

				//getting the GPA's from all of the students 
				temp = Float.parseFloat(parse1.substring((parse1.length() - 3),  (parse1.length()))); 
				//Getting their year
				parseYear = tempYear.substring((tempYear.length() - 6), (tempYear.length() - 4));

				//go through the list and grab the highest GPA in a given class (Sr, Jr, So, Fr) 
				if(GPA.get(w) == temp && Year.equals(parseYear))
				{	
						//Once we find matching gpa's we will add the first last name and gpa to the Kay ArrayList which will be a sorted (by gpa) 
						//arraylist 
						KayYear.add(tempYear); 
						break;
				}
			}
		}
		
		//spacer 
		System.out.println(""); 
		
	}

	public static void main(String[] args) throws IOException 
	{
		hw_1a hw = new hw_1a(); 
		hw.readFile();
		hw.printData(); 
		hw.computeAverageGPA();
		hw.getTopK(); 
		hw.getTopKYear(gradeBook, 3, "Jr"); 
	}

}
