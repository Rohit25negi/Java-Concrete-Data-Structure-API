package datastructure;
import java.util.Stack;
public class Expression
{
	private String exp="";
	private final String operator="^";
	public  static final int INFIX=0;
	public  static final int PREFIX=1;
	public  static final int POSTFIX=2; 

	Expression(String exp,int type) throws IllegalArgumentException
	{
		switch(type)
		{
			case 0: if(Expression.isValidIn(exp))this.exp=exp;
					else throw new IllegalArgumentException("Not a valid Infix Expression: "+exp);
					break;
			case 1: if(Expression.isValidPre(exp))this.exp=exp;
					else
					throw new IllegalArgumentException("Not a valid Prefix Expression: "+exp);
					break;
			case 2: if(Expression.isValidPost(exp))this.exp=exp;
					else 
					throw new IllegalArgumentException("Not a valid Postfix Expression: "+exp);
					break;
			default: throw new IllegalArgumentException("Not a valid Expression type: "+type);
		}
	}
	/*
	toPostfix() is used to convert the Infix Expression to Postfix Expression. I uses the <b>Stack</b> for the same. 
 	*/
	public String toPostfix()
	{	String post="";
		Stack<Character>par=new Stack();
		char A[]=exp.toCharArray();
		for(int i=0;i<A.length;i++)
		{
		
		}




		return null;
	}
	/*
	toPrefix() is used to convert the Infix Expression to Prefix Expression. I uses the <b>Stack</b> for the same. 
 	*/
	public String toPrefix()
	{
return null;
	}
	/*
	evaluate() evaluates the expression and return an integer result. The expression is evaluated on according to <b>BODMAS</b>. 
	*/
	public int evaluate()
	{
return 0;
	}
	/*
	isValidIn(String exp) check if the Infix Expression is valid or not. The current Implementation only allows binary arithematic operators and the expression can only contains integer values. 
	*/
	public static boolean isValidIn(String exp)
	{
		Stack<Character>par=new Stack();
		String operators="+-/*";
		char A[]=exp.toCharArray();
		for(int i=0;i<A.length;i++)
		{
			if(A[i]=='(')par.push('(');
			else if(A[i]==')')
					
				if(!par.isEmpty()&&A[i-1]!='(')
					par.pop();
				else 
					{if(par.isEmpty()||A[i-1]=='(')
					return false;}
			else if(operators.contains(A[i]+""))
					if((i==0||i==A.length-1))return false;
					else {if((A[i-1]<'0'||A[i-1]>'9')&&A[i-1]!=')'||(A[i+1]<'0'||A[i+1]>'9')&&A[i+1]!='(')
						return false;}
			else if(A[i]>'9'||A[i]<'0')return false;
		}
		return par.isEmpty()?true:false;

	}
	public static boolean isValidPre(String exp)
	{return false;}
	public static boolean isValidPost(String exp)
	{return false;}	
	public String toString()
	{
		return exp;
	}
}