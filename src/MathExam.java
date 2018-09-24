import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author LE
 *
 */
public class MathExam {
	static OutputStream out = null;
	static int sum = 0, remainder = 0;	
	static String word = null;
	public static void main(String[] args) {
			File_Initialization();
			int[] output = Input_Message(args);
			Product_Problem_Answer(output[0], output[1]);
	}
	/**
	 * ����number����Ŀ��������Ŀд���ı��ļ�
	 * @return 
	 * **/
	public static boolean Product_Problem_Answer(int number, int grade) {
		List<String> Calculation_Problem = new ArrayList<String>(),
				Word_Set = new ArrayList<String>();
		for (int i = 1; i <= number; i++) 
		{
			Iteration(i, grade, Word_Set);//����һ���ϸ����Ŀ
			File_Write_Problem(i, Calculation_Problem);//�������Ŀд���ĵ�
			if(i == number)
			{
				File_Write_Answer(Calculation_Problem);//��������Ŀ�Ĵ�д���ĵ�
				System.out.println("�Ѿ�������Ŀ���뿴out.txt�ĵ�");
			}
		}
		if(number == 0)
		{
			System.out.println("��Ϊ����Ϊ0�����Բ�����������Ŀ��");
			return false;
		}
		return true;
	}

	/**
	 * �ж���ʽ�Ƿ��ظ���
	 * û�к���ʽ�ظ����ͽ�����ʽ�洢������true��
	 * ����ʽ�ظ��ͷ���false
	 * **/
	public static boolean Judge_Repetition(int i, int cal_number1, int cal_number2, 
			String[] str_symbol, int symbol, List<String> Word_Set) {
		if(Word_Set.contains(word))
		{
			return false;
		}
		else
		{
			Word_Set.add(word);
			if(str_symbol[symbol].equals("+") || str_symbol[symbol].equals("x"))
				Word_Set.add(Integer.toString(cal_number2)+" "+str_symbol[symbol]+" "+Integer.toString(cal_number1));
			word = "("+Integer.toString(i)+") "+word;
			return true;
		}
	}
	
	/**
		Сѧ���꼶�����������Ҫ�����£�
		�������2��4��
		���Լ�����
		��������Ľ�������и���--����Ҫ���沨������ʱ�����ж�
		���������������Ϊ0������������
		������0-1000���ڣ����˵�
		��Ȼ��Ϊһ�꼶�����꼶����Ĺ��ܻ���Ҫ����
		������ѯ�����꼶�����������������С��,���������ڵ�����ֻ������
	 * 
	 * **/
	public static void Iteration(int i, int grade, List<String> Word_Set) {
		Two_Numbers calc = new Two_Numbers();
		Random ranNum = new Random();
		int cal_number1 = 0, cal_number2 = 0, symbol;
		String[] str_symbol = {"+","-","x","��"};
		if(grade == 1)
		{
			symbol = ranNum.nextInt(2);
			cal_number1 = ranNum.nextInt(101);
			cal_number2 = ranNum.nextInt(101);
			if((sum = calc.Calculate_Two_Numbers(cal_number1, cal_number2, symbol, grade)) == -1)
				Iteration(i, grade, Word_Set);
			word = Integer.toString(cal_number1)+" "+str_symbol[symbol]+" "+Integer.toString(cal_number2);
			if(Judge_Repetition(i, cal_number1, cal_number2, str_symbol, symbol, Word_Set))
			{
				Iteration(i, grade, Word_Set);
			}
			else
				return;
		}	
		else if(grade == 2)
		{	
			symbol = ranNum.nextInt(2) + 2;
			cal_number1 = ranNum.nextInt(101);
			cal_number2 = ranNum.nextInt(101);
			if((sum = calc.Calculate_Two_Numbers(cal_number1, cal_number2, symbol, grade)) == -1)
				Iteration(i, grade, Word_Set);
			if(str_symbol[symbol].equals("��"))
				remainder = calc.getRemainder();
			word = Integer.toString(cal_number1)+" "+str_symbol[symbol]+" "+Integer.toString(cal_number2);
			if(Judge_Repetition(i, cal_number1, cal_number2, str_symbol, symbol, Word_Set))
			{
				Iteration(i, grade, Word_Set);
			}
			else
				return;
		}
		else if(grade == 3)
		{		
			int ran_left_parenthesis_num;
			cal_number1 = ranNum.nextInt(1001);
			int ran_symbol_num = ranNum.nextInt(3)+2;
			word = Integer.toString(cal_number1);
			for(int j = 1;j <= ran_symbol_num;j++)
			{
				cal_number1 = cal_number2;
				cal_number2 = ranNum.nextInt(1001);
				symbol = ranNum.nextInt(4);
				ran_left_parenthesis_num = ranNum.nextInt(2);
				if((sum = calc.Calculate_Two_Numbers(cal_number1, cal_number2, symbol, grade)) == -1
					|| calc.getRemainder() != 0)
				{
					j--;
					continue;
				}
				else if(ran_left_parenthesis_num % 2 == 1 && j <= ran_symbol_num-1)
				{	
					if(j == 1)
					{
						word = "(" + word;
						symbol = ranNum.nextInt(2);
						cal_number2 = ranNum.nextInt(1001);
						word = word + str_symbol[symbol] + Integer.toString(cal_number2)+")";
						symbol = ranNum.nextInt(2) + 2;
						cal_number2 = ranNum.nextInt(1001);
						word = word+str_symbol[symbol]+Integer.toString(cal_number2);
					}
					else if(j <= ran_symbol_num-1)
					{
						word = word + str_symbol[symbol] + "("+Integer.toString(cal_number2);
						if(symbol == 0 || symbol == 1)
							symbol = ranNum.nextInt(2);
						else
							symbol = ranNum.nextInt(4);
						cal_number2 = ranNum.nextInt(1001);
						word = word + str_symbol[symbol] + Integer.toString(cal_number2)+")";
					}
					j++;
					ran_left_parenthesis_num = 0;
					continue;
				}
				word = word+str_symbol[symbol]+Integer.toString(cal_number2);
			}
			Calculation calculation = new Calculation(word,Word_Set);
			calculation.Infix_Expression_To_Word();
			calculation.To_Suffix_Expression();
			if(calculation.Suffix_Expression_Summation())
			{				
				word = calculation.getword();
				sum = calculation.getSum();
				if(Word_Set.contains(word))
				{
					Iteration(i, grade, Word_Set);
				}
				else
				{
					word = "("+i+") " + word;
					Word_Set.add(word);
					return;
				}
			}
			else
				Iteration(i, grade, Word_Set);
		}
	}

	/**
	 	�������Ҫ�����Ϣ
	 * **/
	public static int[] Input_Message(String[] args) {
		int j = 1, i = 1, number = -1, grade = -1;
		Scanner input = new Scanner(System.in);
		String check_input_message = null;
		String[] input_args= {" "," "};
		int[] output = {number,grade};
		if(args[0].equals("-n") && args[2].equals("-grade"))
		{	input_args[0] = args[1];input_args[1] = args[3];}
		else if(args[0].equals("-grade") && args[2].equals("-n"))
		{	input_args[0] = args[3];input_args[1] = args[1];}
		else
			System.out.print("�������󣡣���");	
		while(number < 0 || number > 1000 || grade < 1 || grade > 3)
		{
			if(j == 1 && i == 1)
			{
				check_input_message = input_args[0];//����
				j++;
			}	
			else if(j == 1 && i == 2)
			{
				check_input_message = input_args[1];//�꼶
				j++;
			}
			else
				check_input_message = input.nextLine();
			if(!check_input_message.matches("[0-9]{,4}") && i == 1)
			{	
				System.out.print("������������Ϸ�����������������(������)��");
			}
			else if(!check_input_message.matches("[1-3]{1}") && i == 2)
			{
				System.out.print("������꼶���Ϸ�!�����������꼶(1~3)��");
			}
			else if(i == 1)
			{
				number = Integer.parseInt(check_input_message);
				if(number > 1000)
					System.out.print("���������̫����������������(0~1000����)��");
				else if(number < 0)
					System.out.print("���������Ϊ��������������������(0~1000����)��");
				else
				{
					i = 2;
					j = 1;
				}	
			}	
			else if(i == 2 && j != 1)
			{
				grade = Integer.parseInt(check_input_message);
				if(grade<1 || grade>3)
					System.out.print("������꼶���Ϸ�!�����������꼶(1~3)��");
			}
		}
		return output;
	}
	
	public static boolean File_Write_Answer(List<String> Calculation_Problem) {
		try {
			out.write("\r\n".getBytes());
			for (int i = 0; i < Calculation_Problem.size(); i++)
			{
				out.write(Calculation_Problem.get(i).getBytes());
			}
			return true;
		} 
		catch (IOException e) {
			System.out.println("������д��������ʱ�쳣��"+e.getMessage());
			return false;
		}
	}

	public static boolean File_Write_Problem(int i, List<String> Calculation_Problem) {
		try {
			out.write((word+"\r\n").getBytes());
			if(remainder!=0)
			{
				Calculation_Problem.add(word+" = "+Integer.toString(sum)+"..."+Integer.toString(remainder)+"\r\n");
				remainder = 0;
			}
			else
			{	
				Calculation_Problem.add(word+" = "+Integer.toString(sum)+"\r\n");
			}
			word = "";
			return true;
		} 
		catch (IOException e) 
		{
			System.out.println("������out.write()ʱ�׳��쳣"+e.getMessage());
			return false;
		}
	}
	/**
	 * �����ı��ļ�
	 * **/
	public static boolean File_Initialization() {
		String filename ="out.txt";	
		File file = new File(filename);
		if(!file.exists()) 
		{		
			File parent = file.getParentFile();
			if(parent !=null && !parent.exists())
			{	parent.mkdir();//����Ŀ¼
				System.out.println("����Ŀ¼��"+parent);
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("������file.createNewFile()ʱ�׳��쳣"+e.getMessage());
				return false;
			}
			System.out.println("�������ļ���"+file.getAbsolutePath());
		}//��ȡ����·��
		try {
			out = new FileOutputStream(file);
			return true;
		} 
		catch (FileNotFoundException e) {
			System.out.println("������new FileOutputStream(file)ʱ�׳��쳣"+e.getMessage());
			return false;
		}
	}

}