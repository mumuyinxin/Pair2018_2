import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculation {
	int sum = 0;
	String word = "";
	List<String> inffix_expression = new ArrayList<String>(),
				Word_Set = new ArrayList<String>(),
				cal_str = new ArrayList<String>(),
				suffix_expression = new ArrayList<String>();

	public Calculation(String cal_problem, List<String> word_Set) {
		this.Word_Set = word_Set;
		cal_str.add("+");
		cal_str.add("-");
		cal_str.add("x");
		cal_str.add("÷");
		String str_char_merge = "";
		for (int i = 0; i < cal_problem.length(); i++) {
			if(cal_problem.charAt(i) >= '0' && cal_problem.charAt(i) <= '9')
			{
				while(i < cal_problem.length() && cal_problem.charAt(i) >= '0' && cal_problem.charAt(i) <= '9')
				{
					str_char_merge += cal_problem.charAt(i);
					i++;
				}
				inffix_expression.add(str_char_merge);
				str_char_merge = "";
				i--;
			}
			else
			{	
				inffix_expression.add(cal_problem.substring(i, i+1));
			}
		}
	}

	/**
	 * 中缀表达式转后缀表达式
	 * @return 
	 *
	 */
	public List<String> To_Suffix_Expression(List<String> inffix_expression)
	{
		Stack<String> cal_symbol = new Stack<String>();
		String str_char="";
		for(int i = 0;i < inffix_expression.size();i++) {	
			str_char = inffix_expression.get(i);
			if(str_char.matches("[0-9]+"))
				suffix_expression.add(str_char);	
			else if(str_char.equals("("))
				cal_symbol.push(str_char);
			else if(str_char.equals(")"))
			{
				while(!(cal_symbol.peek().equals("(")))
				{
					suffix_expression.add(cal_symbol.pop());
				}
				cal_symbol.pop();
			}
			else
			{
				while(cal_symbol.size()!=0 && Oper(cal_symbol.peek(),str_char))
				{
					suffix_expression.add(cal_symbol.pop());
				}
				cal_symbol.push(str_char);
			}	
		}
		while(cal_symbol.size()!=0)
		{
			suffix_expression.add(cal_symbol.pop());
		}
		return suffix_expression;
	}
	
	public boolean Oper(String str1, String str2) {
		if(str1.equals("(") || str1 == null)
			return false;
		if(cal_str.indexOf(str1)/2 < cal_str.indexOf(str2)/2)
			return false;
		else 
			return true;	
	}

	/**
	 * 后缀表达式（即逆波兰表达式）求和,并判断是否符合相对应的年级
	 *
	 */
	public boolean Suffix_Expression_Summation(List<String> suffix_expression)
	{
		Stack<String> cal_sum = new Stack<String>();
		Two_Numbers calc = new Two_Numbers();
		for(int i = 0;i < suffix_expression.size();i++) {
			if(suffix_expression.get(i).matches("\\d+"))
			{
				cal_sum.push(suffix_expression.get(i));
			}
			else
			{
				int num1 = Integer.parseInt(cal_sum.pop());
				int num2 = Integer.parseInt(cal_sum.pop());
				if(suffix_expression.get(i).equals("+"))
				{					
					sum = num1+num2;
				}
				else if(suffix_expression.get(i).equals("-"))
				{
					sum = num2-num1;
				}
				else if(suffix_expression.get(i).equals("x"))
				{
					sum = num1*num2;
				}
				else if(suffix_expression.get(i).equals("÷") && num1 == 0)
				{
					return false;
				}
				else if(suffix_expression.get(i).equals("÷"))
				{
					sum = num2/num1;
					if(num2 % num1 != 0)
						return false;
				}
				if((sum = calc.Calculate_Two_Numbers(num2, num1, cal_str.indexOf(suffix_expression.get(i)), 3)) == -1
				|| calc.getRemainder() != 0)
				{
					return false;
				}
				cal_sum.push(sum+"");
			}	
		}
		sum = Integer.parseInt(cal_sum.pop());
		if(sum < 0 || sum > 10000)
			return false;
		else
			return true;
	}
	
	/**
	 * 中缀表达式（生成合格的中缀表达式）word
	 *
	 */
	public String Infix_Expression_To_Word(List<String> inffix_expression)
	{
		for (int i = 0; i < inffix_expression.size(); i++) {
			if(i == inffix_expression.size()-1)
			{
				word += inffix_expression.get(i);
				return word;
			}
			else
			{
				word += inffix_expression.get(i)+" ";
			}
		}
		return null;
	}
	

	/**
	 * @return inffix_expression
	 */
	public List<String> getInffix_expression() {
		return inffix_expression;
	}

	/**
	 * @return suffix_expression
	 */
	public List<String> getSuffix_expression() {
		return suffix_expression;
	}
	
	/**
	 * @return word
	 */
	public String getword() {
		return word;
	}
	/**
	 * 获取后缀表达式求和值
	 * @return sum
	 */
	public int getSum() {
		return sum;
	}
	
	/**
	 * @return Word_Set
	 */
	public List<String> getWord_Set(){
		return Word_Set;
	}
	
}
