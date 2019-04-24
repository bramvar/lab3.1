package interfaces;

public interface IvrQueue<E> {
	public void offer(E element);
	public E poll();
	public int size();
	public boolean empty();
	public E peek();
}
