package challenges.binayaka.miscellaneous;

/**
 * This will try to implement our ackermann stack
 * 
 * @author Binayaka
 *
 * @param <T>
 */
public class OverflowlessStack<T> {
	final private class SinglyLinkedNode {
		private final int ArraySize = 2048;
		Object[] _array;
		int _size;
		public SinglyLinkedNode Next;

		public SinglyLinkedNode() {
			_array = new Object[ArraySize];
		}

		public boolean IsEmpty() {
			return _size == 0;
		}

		public SinglyLinkedNode Push(T item) {
			if (_size == ArraySize - 1) {
				SinglyLinkedNode n = new SinglyLinkedNode();
				n.Next = this;
				n.Push(item);
				return n;
			}
			_array[_size++] = item;
			return this;
		}

		@SuppressWarnings("unchecked")
		public T Pop() {
			return (T) _array[--_size];
		}
	}

	private SinglyLinkedNode _head = new SinglyLinkedNode();

	public T Pop() {
		T ret = _head.Pop();
		if (_head.IsEmpty() && _head.Next != null)
			_head = _head.Next;
		return ret;
	}

	public void Push(T item) {
		_head = _head.Push(item);
	}

	public boolean IsEmpty() {
		return _head.Next == null && _head.IsEmpty();
	}
}
