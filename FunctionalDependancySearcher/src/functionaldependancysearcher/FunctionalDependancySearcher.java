/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionaldependancysearcher;

import java.util.*;

/**
 *
 * @author Geeth Sandaru
 */

public class FunctionalDependancySearcher
{
	public static void main(String[] args)
	{
		Scanner get = new Scanner(System.in);
		String columns;
		
		System.out.print("Enter columns : ");
		columns = get.next();
		
		char []col = columns.toCharArray();
		
		for (int i = 0; i < (col.length - 1); i++)
		{
			char check = col[i];
			
			for (int j = i + 1; j < col.length; j++)
			{
				//System.out.println("Checking " + check + " with " + col[j]);
				
				if (check == col[j])
				{
					System.out.println("Duplicates found!");
					System.exit(0);
				}
			}
			
		}
		
		Collection fd = new Vector();
		String tmp;
		
		System.out.println("Enter FD : ");
		
		while (true)
		{
			tmp = get.next();
			
			if (tmp.equals("0"))
				break;
			
			fd.add(tmp);
		}
		
		Iterator itr = fd.iterator();
		String x[] = new String[fd.size()];
		String y[] = new String[fd.size()];
		int k = 0;
		
		while (itr.hasNext() && (k != fd.size()))
		{
			//System.out.println(itr.next());
			
			tmp = itr.next().toString();
			
			x[k] = tmp.substring(0, tmp.indexOf("-"));
			y[k] = tmp.substring((tmp.indexOf("-") + 1), tmp.toCharArray().length);
			
			//System.out.println(tmp2 + " " + tmp3);
			
			k++;
		}
		
		/***************************************
		for (int i = 0; i < fd.size(); i++)
		{
			System.out.println(x[i] + " -> " + y[i]);
		}
		***************************************/
		
		Collection pastClosure = new Vector();
		Collection newClosure = new Vector();
		String keys = "";
		ArrayList tmpX;
		
		for (int i = 0; i < fd.size(); i++)
		{
			char [] clsX = x[i].toCharArray();
			
			//System.out.println(clsX);
			
			newClosure.clear();
			
			for (int l = 0; l < clsX.length; l++)
			{
				//System.out.println(clsX[l]);
				
				if (!newClosure.contains(clsX[l]))
				{
					newClosure.add(clsX[l]);
					
					//System.out.println(newClosure);
				}
			}
			
			//System.out.println("newClosure : " + newClosure);
			
			do
			{
				pastClosure.clear();
				pastClosure.addAll(newClosure);
				
				//System.out.println("pastClosure : " + pastClosure);
				
				for (int j = 0; j < fd.size(); j++)
				{
					tmpX = new ArrayList();
					
					for (int h = 0; h < x[j].toCharArray().length; h++)
					{
						tmpX.add((x[j].toCharArray())[h]);
					}
					
					if (newClosure.containsAll(tmpX))
					{
						//System.out.println(x[j]);
						
						char [] clsY = y[j].toCharArray();
						
						for (int l = 0; l < clsY.length; l++)
						{
							if (!newClosure.contains(clsY[l]))
							{
								newClosure.add(clsY[l]);
								
								//System.out.println("new newClosure : " + newClosure + "\n");
							}
						}
						
						
					}
				}
			}
			while(!pastClosure.containsAll(newClosure));
			
			tmpX = new ArrayList();
			
			for (int h = 0; h < columns.toCharArray().length; h++)
			{
				tmpX.add((columns.toCharArray())[h]);
			}
			
			//System.out.println("key checking" + tmpX);
			
			if (newClosure.containsAll(tmpX))
			{
				if (!keys.contains(x[i]))
					keys = keys + " " + x[i];
				
				//System.out.println("key : " + keys);
			}
		}
		
		
		//System.out.println(pastClosure);
		//System.out.println(newClosure);
		System.out.println("\nKeys : " + keys + "\n");
		
	}

}


