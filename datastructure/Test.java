import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
class Test extends JFrame
{
	public static void main(String arg[])
	{
		new Test("calculator");
	}
	Test(String str)
	{
		super(str);
		setBounds(100,100,300,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}