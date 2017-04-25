package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	 
	private LLNode<T> head;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if(this.size() == 0) {
			throw new StackUnderflowException();
		}
		T temp = head.getData();
		head = head.getNext();
		return temp;
	}
		

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		if(this.size() == 0) {
			throw new StackUnderflowException();
		}
		return head.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		LLNode<T> curr = head;
		int counter = 0;
		while(curr != null) {
			curr = curr.getNext();
			counter++;
		}
		return counter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> node = new LLNode<T>(elem);
		node.setNext(head);
		head = node;
	}

}
