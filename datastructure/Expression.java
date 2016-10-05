import java.util.Stack;
public class Expression
{
	private String exp="";
	static private final String operators="^*/+-";
	private final char OPR_PRECEDENCE[]={0,1,1,2,2};
	public  static final int INFIX=0;
	public  static final int PREFIX=1;
	public  static final int POSTFIX=2; 

	Expression(String exp,int type) throws IllegalArgumentException
	{
		switch(type)
		{
			case INFIX: if(Expression.isValidIn(exp))this.exp=exp.replaceAll(" ","");
						else throw new IllegalArgumentException("Not a valid Infix Expression: "+exp);
						break;
			case PREFIX: if(Expression.isValidPre(exp)){this.exp=exp;}
						 else
						throw new IllegalArgumentException("Not a valid Prefix Expression: "+exp);
						break;
			case POSTFIX: if(Expression.isValidPost(exp))this.exp=exp;
						   else 
						  throw new IllegalArgumentException("Not a valid Postfix Expression: "+exp);
						break;
			default: throw new IllegalArgumentException("Not a valid Expression type: "+type);
		}
	}

	public String toInfix()
	{
		StringBuffer in=new StringBuffer("");
		byte x=-1;
		char c;
		for(int i=0;i<exp.length();i++)
			{	c=exp.charAt(i);
				if(operators.indexOf(c)>-1){in.append(" "+c);x=1;}
				else if(c=='('||c==')'){in.append(" "+c);x=2;}
				else  {
					if(x!=3)in.append(" ");
						in.append(c);
					x=3;
				}
			}
	 return in.toString().trim();
	
	}
	/*
	toPostfix() is used to convert the Infix Expression to Postfix Expression. I uses the <b>Stack</b> for the same. 
 	*/
	public String toPostfix()
	{	StringBuffer post=new StringBuffer("");
		Stack<Character>par=new Stack();
		char A[]=exp.toCharArray(),c;
		for(int i=0;i<A.length;i++)
		{	if(A[i]==' ')continue;
		if(A[i]>='0'&&A[i]<='9')post.append(A[i]);
		else if(A[i]=='(') par.push(A[i]);
		else if(A[i]==')')
				while((c=par.pop())!='(')
					post.append(" "+c);
		else {
				while(!par.isEmpty()&&par.peek()!='('&&OPR_PRECEDENCE[operators.indexOf(par.peek())]<=OPR_PRECEDENCE[operators.indexOf(A[i])])
					post.append(" "+par.pop());
				post.append(" ");
				par.push(A[i]);
			}
		}
		while(!par.isEmpty())post.append(" "+par.pop());

		return post.toString();
	}
	/*
	toPrefix() is used to convert the Infix Expression to Prefix Expression. I uses the <b>Stack</b> for the same. 
 	*/
	public String toPrefix()
	{
		StringBuffer post=new StringBuffer("");
		Stack<Character>par=new Stack();
		char A[]=exp.toCharArray(),c;
		for(int i=A.length-1;i>=0;i--)
		{	if(A[i]==' ')continue;
		if(A[i]>='0'&&A[i]<='9')post.append(A[i]);
		else if(A[i]==')') par.push(A[i]);
			else if(A[i]=='(')while((c=par.pop())!=')')post.append(" "+c);
			else {
				while(!par.isEmpty()&&par.peek()!=')'&&OPR_PRECEDENCE[operators.indexOf(par.peek())]<=OPR_PRECEDENCE[operators.indexOf(A[i])])
					post.append(" "+par.pop());
				post.append(" ");
				par.push(A[i]);
			}
		}
		while(!par.isEmpty())post.append(" "+par.pop());

		return post.reverse().toString();

	}
	
	/*
	isValidIn(String exp) check if the Infix Expression is valid or not. The current Implementation only allows binary arithematic operators and the expression can only contains integer values. 
	*/
	public static boolean isValidIn(String exp)
	{	exp=exp.replace(" ","");
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
	{

		Stack<Integer> st=new Stack();

		String A[]=exp.split(" ");
		char c;
		int p1,p2;
		try
		{
			for(int i=A.length-1;i>=0;i--)
			{
				c=A[i].charAt(0);
				if(operators.indexOf(c+"")>-1)
				{	p1=st.pop();
					p2=st.pop();
					switch(c)
					{
						case '+': st.push(p2+p1);break;
						case '-': st.push(p2-p1);break;
						case '*': st.push(p2*p1);break;
						case '/': st.push(p2/p1);break;
						case '^': st.push((int)Math.pow(p2,p1));break;
					}
				}else
					st.push(Integer.parseInt(A[i]));	
			}
		}catch(Exception e)
		{
			return false;
		}

		return st.size()==1?true:false;


	}

	public static boolean isValidPost(String exp)
	{
		Stack<Integer> st=new Stack();

		String A[]=exp.split(" ");
		char c;
		int p1,p2;
		try
		{
			for(int i=0;i<A.length;i++)
			{
				c=A[i].charAt(0);
				if(operators.indexOf(c+"")>-1)
				{	p1=st.pop();
					p2=st.pop();
					switch(c)
					{
						case '+': st.push(p2+p1);break;
						case '-': st.push(p2-p1);break;
						case '*': st.push(p2*p1);break;
						case '/': st.push(p2/p1);break;
						case '^': st.push((int)Math.pow(p2,p1));break;
					}
				}else
					st.push(Integer.parseInt(A[i]));	
			}
		}catch(Exception e)
		{
			return false;
		}

		return st.size()==1?true:false;

	}	
	/*
	evaluate() evaluates the expression and return an integer result. The expression is evaluated on according to <b>BODMAS</b>. 
	*/
	public int evaluate()
	{
		String post=toPostfix();
		Stack<Integer> st=new Stack();

		String A[]=post.split(" ");
		char c;
		int p1,p2;
			for(int i=0;i<A.length;i++)
			{
				c=A[i].charAt(0);
				if(operators.indexOf(c+"")>-1)
				{	p1=st.pop();
					p2=st.pop();
					switch(c)
					{
						case '+': st.push(p2+p1);break;
						case '-': st.push(p2-p1);break;
						case '*': st.push(p2*p1);break;
						case '/': st.push(p2/p1);break;
						case '^': st.push((int)Math.pow(p2,p1));break;
					}
				}else
					st.push(Integer.parseInt(A[i]));	
			}
		
		return st.peek();
	}

	public String toString()
	{
		return toInfix();
	}
}