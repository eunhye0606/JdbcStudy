/*======================
	MemberMain.java
=======================*/


package com.test;


public class MemberMain
{
	public static void main(String[] args)
	{
		Process prs = new Process();
		int menu = 0;
		
		
		try
		{
			do
			{
				menu = prs.menuPrint();
				if (menu == -1)
				{
					System.out.println("프로그램 종료.");
					return;
				}
				
				switch (menu)
				{
				case 1: prs.empInsert();
					break;
				case 2: prs.empSelect();
					break;
				case 3: prs.empSerch();
					break;
				case 4: prs.empUpdate();
					break;
				case 5: prs.empDelete();
				break;
				}
			} while (true);
			
		}
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}//end class MemberMain
