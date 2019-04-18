package vrRBTree;

import exceptions.ElementException;

public interface IvrRbTreeO<E extends Comparable<? super E>> extends IvrRbTree<E> {
	public void insert(E element) throws ElementException;
	public void delete(E element) throws ElementException;
}
