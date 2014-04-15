import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;


public class Parser {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		System.out.println("Please enter the expression to be proved");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			//(p ^ q) > (p v q)
			//( p v (p ^ q)) > ( (p v p) ^ (p v q) )
			//(p>q) -> ~((p>q)>q)
			//(p v q) > p
			//(( p > F) > q) > p
			
			String formula=br.readLine();
			String []units=new String[formula.length()];
			Stack<Integer> stack =new Stack<Integer>();  
			
			int unit_count=0;
			
			for(int i=0;i<formula.length();i++)
			{
				if(formula.charAt(i)=='(')
				{
					stack.push(i+1);
					
					continue;
				}
				else if(formula.charAt(i)==')') //then backtrack till the right paranthesis
				{
					 units[unit_count++]=formula.substring(stack.pop(),i);
				}
			}
			
			//displaying the various units
			for(int i=0;i<unit_count;i++)
			{
				units[i]=units[i].replaceAll("\\s","");
				System.out.print(units[i]+", ");
			}
			System.out.println();
			
			//lets do it first for binary operators
			HashMap<String,String> patterns=new HashMap<String,String>();
			StringBuffer []units_buff=new StringBuffer[unit_count];
			
			for(int i=0;i<unit_count;i++)
			{
				units_buff[i]=new StringBuffer(units[i]);
				for(int j=0;j<units[i].length();j++)
				{
					if(units[i].charAt(j)=='^')
					{
						if(Character.isAlphabetic(units[i].charAt(j-1)) && Character.isAlphabetic(units[i].charAt(j+1)))
						{
							patterns.put(units_buff[i].substring(j-1,j+2), "( "+units[i].charAt(j-1)+" > ( "+units[i].charAt(j+1)+" > F)) >F");
							units_buff[i].replace(j-1,j+2,"( "+units[i].charAt(j-1)+" > ( "+units[i].charAt(j+1)+" > F)) >F");
							units[i]=units_buff[i].toString();
						}
						else if(Character.isAlphabetic(units[i].charAt(j-1)) && units[i].charAt(j+1)=='(')
						{
							Stack<Integer> stack_and=new Stack<Integer>();
							String part="";
							int k;
							for (k=j+1;k<units[i].length();k++)
							{
								if(units[i].charAt(k)=='(')
								{
									stack_and.push(k+1);
								}
								else if(units[i].charAt(k)==')')
								{
									part=units[i].substring(stack_and.pop(),k);
									part=part.replaceAll("\\s","");
									part=patterns.get(part);
									break;
								}
							}
							patterns.put(units_buff[i].substring(j-1,k), "( "+units[i].charAt(j-1)+" > ( "+part+" > F)) >F");
							units_buff[i].replace(j-1,k,"( "+units[i].charAt(j-1)+" > ( "+part+" > F)) >F");
							units[i]=units_buff[i].toString();
						}
						else if(Character.isAlphabetic(units[i].charAt(j+1)) && units[i].charAt(j-1)==')')
						{
							String part_1=units[i].substring(1,j-1);
							patterns.put(units_buff[i].substring(0,j+2),"(("+part_1+") > "+units[i].charAt(j+1)+">F)F");
							units_buff[i].replace(0,j+2,"(("+part_1+") > "+units[i].charAt(j+1)+">F)>F");
							units[i]=units_buff[i].toString();
							
						}
						else if(units[i].charAt(j-1)==')' && units[i].charAt(j+1)=='(')
						{
							//first we will fetch the string in the 'closing' parenthesis
							//then the 'opening' parenthesis one and then we will concatenate them
							Stack<Integer> stack_and=new Stack<Integer>();
							String part_1="";
							String part_2="";
							
							part_1=units[i].substring(0,j-1); 
									
							int l;
							for(l=j+1;l<units[i].length();l++)
							{
								if(units[i].charAt(l)=='(')
								{
									stack_and.push(l+1);
								}
								else if(units[i].charAt(l)==')')
								{
									part_2=units[i].substring(stack_and.pop(),l);
									part_2=part_2.replaceAll("\\s","");
									part_2=patterns.get(part_2);
									break;
								}
							}
							patterns.put(units_buff[i].substring(0,l),part_1+" > ( "+part_2+" > F)) >F");
							units_buff[i].replace(0,l,part_1+" > ( "+part_2+" > F)) >F");
							units[i]=units_buff[i].toString();
						}
						
					}
					else if(units[i].charAt(j)=='v')
					{
						if(Character.isAlphabetic(units[i].charAt(j-1)) && Character.isAlphabetic(units[i].charAt(j+1)))
						{
							patterns.put(units_buff[i].substring(j-1,j+2), "( "+units[i].charAt(j-1)+" > F) > "+units[i].charAt(j+1));
							units_buff[i].replace(j-1,j+2,"("+units[i].charAt(j-1)+" > F) > "+units[i].charAt(j+1));
							units[i]=units_buff[i].toString();
						}
						else if(Character.isAlphabetic(units[i].charAt(j-1)) && units[i].charAt(j+1)=='(')
						{
							Stack<Integer> stack_or=new Stack<Integer>();
							String part="";
							int k;
							for (k=j+1;k<units[i].length();k++)
							{
								if(units[i].charAt(k)=='(')
								{
									stack_or.push(k+1);
								}
								else if(units[i].charAt(k)==')')
								{
									part=units[i].substring(stack_or.pop(),k);
									part=part.replaceAll("\\s","");
									part=patterns.get(part);
									break;
								}
							}
							
							patterns.put(units_buff[i].substring(j-1,k), "("+units[i].charAt(j-1)+" > F) > "+part);
							units_buff[i].replace(j-1,k,"("+units[i].charAt(j-1)+" > F) > "+part);
							units[i]=units_buff[i].toString();
						}
						else if(Character.isAlphabetic(units[i].charAt(j+1)) && units[i].charAt(j-1)==')')
						{
							String part_1=units[i].substring(1,j-1);
							patterns.put(units_buff[i].substring(0,j+2),"("+part_1+" >F) >"+units[i].charAt(j+1));
							units_buff[i].replace(0,j+2,"("+part_1+" >F) >"+units[i].charAt(j+1));
							units[i]=units_buff[i].toString();
							
						}
						else if(units[i].charAt(j-1)==')' && units[i].charAt(j+1)=='(')
						{
							//first we will fetch the string in the 'closing' parenthesis
							//then the 'opening' parenthesis one and then we will concatenate them
							Stack<Integer> stack_and=new Stack<Integer>();
							String part_1="";
							String part_2="";
							part_1=units[i].substring(0,j-1); 
									
							int l;
							for(l=j+1;l<units[i].length();l++)
							{
								if(units[i].charAt(l)=='(')
								{
									stack_and.push(l+1);
								}
								else if(units[i].charAt(l)==')')
								{
									part_2=units[i].substring(stack_and.pop(),l);
									part_2=part_2.replaceAll("\\s","");
									part_2=patterns.get(part_2);
									break;
								}
							}
							patterns.put(units_buff[i].substring(0,l),part_1+" >F) > "+part_2);
							units_buff[i].replace(0,l,part_1+" >F) > "+part_2);
							units[i]=units_buff[i].toString();
						}
					}
					else if(units[i].charAt(j)=='~')
					{
						if(Character.isAlphabetic(units[i].charAt(j+1)))
						{
							patterns.put(units_buff[i].substring(j,j+2),"("+units[i].charAt(j+1)+">F)");
							units_buff[i].replace(j, j+2,"("+units[i].charAt(j+1)+">F)");
							units[i]=units_buff[i].toString();
						}
						else if(units[i].charAt(j+1)=='(')
						{
							patterns.put(units_buff[i].substring(j,units[i].length()),units[i].substring(j+1,units[i].length())+">F");
							units_buff[i].replace(j,units[i].length(),units[i].substring(j+1,units[i].length())+">F");
							units[i]=units_buff[i].toString();
						}
					}
				}
				System.out.println(units[i]);
			}
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(EmptyStackException e)
		{
			System.out.println("Stack is empty, formula is not well formed");
		}
	
	}

}
