package vrAVLTree;

import exceptions.ElementException;
import interfaces.IvrTreeO;

public class VrAvlTree<T extends Comparable<? super T>> implements IvrTreeO<T>{

	private VrAvlTreeNode<T> root;
	private int weight;
	
	public  VrAvlTree() {
		root=null;
		weight=0;
	}
	
	public  VrAvlTree(VrAvlTreeNode<T> n,int p) {
		root=n;
		weight=p;
	}
	
	
	@Override
	public int getHeight() {
		return ( root != null ) ? root.getHeight() : 0;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public T search(T e) {
		return ( root != null ) ? root.search(e) : null;
	}
	
	public T search2(T e) {
		return ( root != null ) ? root.search(e) : null;
	}

	public VrAvlTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(VrAvlTreeNode<T> root) {
		this.root = root;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public void insert(T elem) throws ElementException {
		if( root == null ){
			root = new VrAvlTreeNode<T>( elem );
		}
		else{
			root = root.insert( elem );
		}
		weight++;
	}

	@Override
	public void delete(T elem) throws ElementException {
		if( root != null ){
			root = root.delete( elem );
			weight--;
		}
		else	{
			throw new ElementException(ElementException.NO_SUCH_ELEMENT);
			
		}
		
	}

	

	
	
}
