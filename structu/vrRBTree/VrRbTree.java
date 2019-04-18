package vrRBTree;

import exceptions.ElementException;

public class VrRbTree<T extends Comparable<? super T>> implements IvrRbTreeO<T>{
	
	private VrRbTreeNode<T> root;
	
	public VrRbTree() {
		root=null;
	}

	@Override
	public int getHeight() {
		return root == null ? 0 : root.;
	}

	@Override
	public int getWeight() {
		return root == null ? 0 : root.getWeight( );
	}

	@Override
	public T search(T e) {
		try{
			return root != null ? root.getNode(e).getElem( ) : null;
		}
		catch( ElementException ex ){
			return null;
		}
	}
	
	public T maximun() {
		return root == null ? null : root.maximun( ).getElem( );
	}
	
	public T minimun() {
		return root == null ? null : root.minimun( ).getElem( );
	}

	public VrRbTreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(VrRbTreeNode<T> root) {
		this.root = root;
	}

	@Override
	public void insert(T elem) throws ElementException {
		VrRbTreeNode<T> n=new VrRbTreeNode<T>(elem);
		VrRbTreeNode<T> r2 = null;

		if( root == null ){
			root = n;
			root.setColor( VrRbTreeNode.BLACK );
		}
		else{
			r2 =root.insert( n );
		}
		root =r2 != null && r2.getParent( ) == null ? r2 :root;
		
	}

	@Override
	public void delete(T elem) throws ElementException {
		if( root == null )
			throw new ElementException(ElementException.NO_SUCH_ELEMENT);
			if( root.getElem( ).compareTo( elem ) == 0 && root.isLeafRightChild() && root.isLeafLeftChild() )
				root = null;
			else{
				VrRbTreeNode<T> r2 = root.getNode( elem ).eliminar( );
				root = r2 != null && r2.getParent( ) == null ? r2 : root;
			}
		
	}
	
	
}
