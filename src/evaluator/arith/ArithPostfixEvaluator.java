package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack = new LinkedStack<Operand<Integer>>();
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		while(!stack.isEmpty()) {
			stack.pop();
		}
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				stack.push(token.getOperand());
				break;
			case OPERATOR:
				Operator<Integer> o = token.getOperator();
				int NoA = o.getNumberOfArguments();
				if(NoA > stack.size()) {
					throw new IllegalPostfixExpressionException("Not Enough Operands");
				}
				for(int i = NoA - 1; i >= 0; i--) {
					o.setOperand(i, stack.pop());
				}
				stack.push(o.performOperation());
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}						
		}
		if(stack.size() != 1) {
			throw new IllegalPostfixExpressionException("Not Enough Operators");
		}
		return stack.top().getValue();
	}

}
