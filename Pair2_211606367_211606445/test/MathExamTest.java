import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MathExamTest {

	@Test
	public void testMain() {
		String[] args = {"-n", "100", "-grade", "3"};
		MathExam math = new MathExam();
		math.main(args);
		assertEquals(true, math.output_boolean);
	}

	@Test
	public void testProduct_Problem_Answer() {
		assertEquals(true, new MathExam().Product_Problem_Answer(100, 3));
		assertEquals(false, new MathExam().Product_Problem_Answer(0, 3));
		
	}

	@Test
	public void testInput_Message() {
		MathExam math = new MathExam();
		String[] args1 = {"-n", "100", "-grade", "3"};
		String[] args2 = {"-grade", "3", "-n", "100"};
		String[] args3 = {"-grade", "3", "-n", "-100"};
		String[] args4 = {"-grade", "3", "-n", "10000"};
		String[] args5 = {"-grade", "4", "-n", "100"};
		String[] args6 = {"-grade", "10", "-grade", "10000"};
		List<String> output = new ArrayList<String>();
		output.add("100");
		output.add("3");
		assertEquals(output, math.Input_Message(args1));	
		assertEquals(output, math.Input_Message(args2));	
	}

	@Test
	public void testFile_Write_Answer() {
		MathExam math = new MathExam();
		List<String> Calculation_Problem1 = new ArrayList<String>();
		Calculation_Problem1.add("123 + 123 = 246");
		assertEquals(true, math.File_Write_Answer(Calculation_Problem1));	
		List<String> Calculation_Problem2 = new ArrayList<String>();
		assertEquals(false, math.File_Write_Answer(Calculation_Problem2));	
	}

	@Test
	public void testFile_Write_Problem() {
		MathExam math = new MathExam();
		List<String> Calculation_Problem = new ArrayList<String>();
		int sum = (123+123) * 123;
		String word1 = "( 123 + 123 ) x 123";
		word1 += " = "+sum+"\r\n";
		String word2 = "6 ¡Â 4";
		word2 += " = " + 1 + "..." + 2 + "\r\n";
		boolean test1 = math.File_Write_Problem(Calculation_Problem, sum, 0, "( 123 + 123 ) x 123").contains(word1);
		boolean test2 = math.File_Write_Problem(Calculation_Problem, 1, 2, "6 ¡Â 4").contains(word2);
		assertEquals(true, test1);	
		assertEquals(true, test2);
		assertEquals(null, math.File_Write_Problem(Calculation_Problem, 0, 0, ""));
	}

	@Test
	public void testFile_Initialization() {
		String filename ="out.txt";
		assertEquals(true, new MathExam().File_Initialization(filename).exists());
		assertEquals(true, new MathExam().File_Initialization("input.txt").exists());
		assertEquals(null, new MathExam().File_Initialization(""));	
	}

}
