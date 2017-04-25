package language.arith;

import language.Operand;
import language.UnaryOperator;

/**
 * The {@code NegateOperator} is an operator that performs negation on a single integer
 * @author jcollard
 *
 */

public class NegateOperator extends UnaryOperator<Integer> {
	public Operand<Integer> performOperation() {
		if(op0 == null)
			throw new IllegalStateException("Could not perform operation prior to operand being set.");
		Integer result = op0.getValue() * -1;
		return new Operand<Integer>(result);
	}

	public String toString() {
		return "!";
	}
}
