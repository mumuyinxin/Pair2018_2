import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CalculationTest {

	@Test
	public void testCalculation() {
		List<String> inffix_expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		assertEquals(inffix_expression, new Calculation("(123+123)x123", new ArrayList<String>()).getInffix_expression());
	}

	@Test
	public void testTo_Suffix_Expression() {
		List<String> inffix_expression = new ArrayList<String>(),
				Suffix_Expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		Suffix_Expression.add("123");
		Suffix_Expression.add("123");
		Suffix_Expression.add("+");
		Suffix_Expression.add("123");
		Suffix_Expression.add("x");
		assertEquals(Suffix_Expression,new Calculation("(123+123)x123", new ArrayList<String>()).To_Suffix_Expression(inffix_expression));
	}

	@Test
	public void testOper() {
		Calculation calc = new Calculation("(1+2)x3", new ArrayList<String>());
		assertEquals(false, calc .Oper("", "x"));
		assertEquals(false, calc .Oper("+", "¡Â"));
		assertEquals(true, calc .Oper("¡Â", "+"));
	}

	@Test
	public void testSuffix_Expression_Summation() {
		List<String> Suffix_Expression1 = new ArrayList<String>();
		Suffix_Expression1.add("123");
		Suffix_Expression1.add("123");
		Suffix_Expression1.add("+");
		Suffix_Expression1.add("123");
		Suffix_Expression1.add("x");
		assertEquals(false,new Calculation("(123+123)x123", new ArrayList<String>()).Suffix_Expression_Summation(Suffix_Expression1));
		List<String> Suffix_Expression2 = new ArrayList<String>();
		Suffix_Expression2.add("12");
		Suffix_Expression2.add("12");
		Suffix_Expression2.add("+");
		Suffix_Expression2.add("3");
		Suffix_Expression2.add("x");
		assertEquals(true,new Calculation("(12+12)x3", new ArrayList<String>()).Suffix_Expression_Summation(Suffix_Expression2));
		
	}

	@Test
	public void testInfix_Expression_To_Word() {
		List<String> inffix_expression1 = new ArrayList<String>();
		inffix_expression1.add("(");
		inffix_expression1.add("123");
		inffix_expression1.add("+");
		inffix_expression1.add("123");
		inffix_expression1.add(")");
		inffix_expression1.add("x");
		inffix_expression1.add("123");
		assertEquals("( 123 + 123 ) x 123", new Calculation("(123+123)x123", new ArrayList<String>()).Infix_Expression_To_Word(inffix_expression1));
		List<String> inffix_expression2 = new ArrayList<String>();
		assertEquals(null, new Calculation("(123+123)x123", new ArrayList<String>()).Infix_Expression_To_Word(inffix_expression2));
		
	}

	@Test
	public void testGetInffix_expression() {
		List<String> inffix_expression = new ArrayList<String>();
		inffix_expression.add("(");
		inffix_expression.add("123");
		inffix_expression.add("+");
		inffix_expression.add("123");
		inffix_expression.add(")");
		inffix_expression.add("x");
		inffix_expression.add("123");
		assertEquals(inffix_expression, new Calculation("(123+123)x123", new ArrayList<String>()).getInffix_expression());
	}

	@Test
	public void testGetSuffix_expression() {
		List<String> Suffix_Expression = new ArrayList<String>();
		assertEquals(Suffix_Expression, new Calculation("(123+123)x123", new ArrayList<String>()).getSuffix_expression());
	}

	@Test
	public void testGetword() {
		assertEquals("", new Calculation("(123+123)x123", new ArrayList<String>()).getword());
	}

	@Test
	public void testGetSum() {
		assertEquals(0, new Calculation("(123+123)x123", new ArrayList<String>()).getSum());
	}

	@Test
	public void testGetWord_Set() {
		List<String> word_set = new ArrayList<String>();
		word_set.add("123+123");
		assertEquals(word_set, new Calculation("(123+123)x123", word_set).getWord_Set());
	}

}
