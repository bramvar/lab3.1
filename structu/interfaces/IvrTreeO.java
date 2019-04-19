package interfaces;

import exceptions.ElementException;

public interface IvrTreeO<E extends Comparable<? super E>> extends IvrTree<E> {
	public void insert(E element) throws ElementException;
	public void delete(E element) throws ElementException;
}
