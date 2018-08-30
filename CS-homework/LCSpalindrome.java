// Evan Waldmann
// COP 3503H 
// 10/15/17

public class LCSpalindrome {

	public static void printouttable(int[][] arr)
	{
		for (int i=0; i<arr.length; i++)
		{
			for (int j=0; j<arr[0].length; j++)
			{
				System.out.print(arr[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println("END");
	}

	public static String generateLengthTable(char[] str)
	{
		int n = str.length;
		int r, c, i;
		int[][] table = new int[n][n];  

		for (r = 0; r < n; r++){
			table[r][r] = 1; //single chars are palindromes of length 1 
		}

		for (i=2; i<=n; i++)                             
		{
			for (r=0; r<n-i+1; r++)                         
			{
				c = r+i-1;                                 
				if (str[r] == str[c] && i == 2){             
					table[r][c] = 2;
				}
				else if (str[r] == str[c])   {                
					table[r][c] = table[r+1][c-1] + 2;
				}
				else
				{
					if (table[r][c-1] > table[r+1][c])
						table[r][c] = table[r][c-1]; 
					else 
						table[r][c] =  table[r+1][c];
				}         
			}
		}

		// printouttable(table); //test to see if table is working
		return printStringFromTable(str, table); 

	}

	private static String printStringFromTable(char[] a, int[][] table)
	{
		int len = a.length;
		int end = table[0][len-1]; // the longest palindrome's length
		char result[] = new char[end+1];
		result[end] = '\0';//null terminator
		end--; 

		int r = 0;
		int c = len - 1;
		while(end >= 0 && r <= c)
		{
			if(a[r] == a[c])
			{
				result[end] = a[r];
				end--; 
				r++;
				c--;
			}
			else
			{
				if(table[r+1][c] > table[r][c-1])
				{
					r++;
				}
				else
				{
					c--;
				}
			}
		}

		//even or odd length changes bounds of loop
		if(table[0][len-1]%2 == 0)
		{
			r=0;
			int mid = table[0][len-1]/2;
			c = result.length - 2;
			while(c >= mid)
			{
				result[r++] = result[c--];
			}
		}
		else        
		{  
			r = 0;
			int mid = table[0][len-1]/2;
			c = result.length - 2;
			while(c > mid)
			{
				result[r++] = result[c--];
			}
		}

		return (String.valueOf(result)).substring(0, table[0][len-1]);
		//null terminator was printing for some reason so I cut it off
	}


	public static void main(String args[])
	{

		String str = "character"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "BBABCBCAB"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "aibohphobia"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "racecar"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "one"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "palindrome"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "input"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "example"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));

		str = "sequence"; 
		System.out.println("Longest commmon palindrome of "+str+" is "+ generateLengthTable(str.toCharArray()));
	}
}
