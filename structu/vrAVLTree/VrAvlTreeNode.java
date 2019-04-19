package vrAVLTree;

import exceptions.ElementException;
import vrRBTree.VrRbTreeNode;

public class VrAvlTreeNode<T extends Comparable<? super T>>  {

	public static final int BLEFT=1;
	public static final int BAL=0;
	public static final int BRIGHT=-1;
	
	private T elem;
	private int balance;
	private VrAvlTreeNode<T> leftN;
	private VrAvlTreeNode<T> rightN;
	
	public VrAvlTreeNode( T elem){
		this.elem=elem ;
		leftN = null;
		rightN= null;
	}

	
	public T getElem() {
		return elem;
	}


	public void setElem(T elem) {
		this.elem = elem;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public VrAvlTreeNode<T> getLeftN() {
		return leftN;
	}


	public void setLeftN(VrAvlTreeNode<T> leftN) {
		this.leftN = leftN;
	}


	public VrAvlTreeNode<T> getRightN() {
		return rightN;
	}


	public void setRightN(VrAvlTreeNode<T> rightN) {
		this.rightN = rightN;
	}


	public  VrAvlTreeNode<T>  insert( T elem ) throws ElementException{
	
		Return0 r = new Return0( null, false );
		insert2( elem, r);
		return r.r;
	}
	
	public VrAvlTreeNode<T>  leftRotate( ){
		VrAvlTreeNode<T> aux = rightN;
		rightN = aux.leftN;
		aux.leftN = this;
		return aux;
	}
	
	public VrAvlTreeNode<T> rightRotate( ){
		VrAvlTreeNode<T> aux = leftN;
		leftN = aux.rightN;
		aux.rightN = this;
		return aux;
	}
	
	public VrAvlTreeNode<T> rightLeftRotate( ){
		rightN = rightN.rightRotate( );
		return leftRotate( );
	}
	
	public VrAvlTreeNode<T> leftRightRotate( ){
		leftN = leftN.leftRotate( );
		return rightRotate( );
	}
	
	private VrAvlTreeNode<T> rightBalance( )
	{
	if( rightN.balance == BRIGHT ){
		balance = BAL;
		rightN.balance = BAL;
		return leftRotate( );
	}
	else{

	switch( rightN.leftN.balance ){
		case BLEFT:
			balance = BAL;
			rightN.balance = BRIGHT;
			break;
		case BAL:
			balance = BAL;
			rightN.balance = BAL;
			break;
		case BRIGHT:
			balance = BLEFT;		
			rightN.balance = BAL;
			break;
		}
		rightN.leftN.balance = BAL;
		return rightLeftRotate( );
		}
	}
	
	public VrAvlTreeNode<T> leftBalance( ){
		if( leftN.balance == BLEFT ){
			balance = BAL;
			leftN.balance = BAL;
			return rightRotate( );
		}
		else{
			switch( leftN.rightN.balance ){
			case BLEFT:
				balance = BRIGHT;
				leftN.balance = BAL;
				break;
			case BAL:
				balance = BAL;
				leftN.balance = BAL;
				break;
			case BRIGHT:
				balance = BAL;
				leftN.balance = BLEFT;
				break;	
			}
			leftN.rightN.balance = BAL;
			return leftRightRotate();
		}
	}
	
	private void insert2( T elem, Return0 r) throws ElementException{
	
		int res = elem.compareTo(elem);
		if( res== 0 )
			throw new ElementException(ElementException.ELEMENT_EXIST);
		else if( res> 0 ){
			if( leftN == null ){
				leftN = new VrAvlTreeNode<T>(elem);
				r.r = this;
				if( rightN == null ){
					balance = BLEFT;	
					r.heightChange= true;
				}
				else{
					balance = BAL;
					r.heightChange=false;
				}
			}
			else{
				leftN.insert2(elem, r);
				leftN = r.r;
				if( r.heightChange ){
					switch( balance ){
					case BLEFT:
						r.heightChange = false;
						r.r= leftBalance();
						break;
					case BAL:
						balance = BLEFT;
						r.r = this;
						break;
					case BRIGHT:
						balance = BAL;
						r.heightChange= false;
						r.r= this;
						break;
					}
				}
				else{
					r.r= this;
				}
			}
		}
		else{	
			if( rightN == null ){
				rightN = new VrAvlTreeNode<T>(elem);
				r.r= this;
				if( leftN == null )
				{
					balance = BRIGHT;
					r.heightChange= true;
				}
				else{
					balance = BAL;	
					r.heightChange= false;
				}
			}
			else{
				rightN.insert2(elem, r);
				rightN = r.r;		

				if( r.heightChange) {
					switch( balance ){
						case BLEFT:
							balance = BAL;
							r.heightChange= false;
							r.r= this;
							break;
						case BAL:
							balance = BRIGHT;
							r.r = this;
							break;
						case BRIGHT:
							r.heightChange= false;
							r.r = rightBalance( );
							break;
					}
				}
				else{
					r.r = this;
				}
			}
		}
	}
	
	private class Return0{
		private VrAvlTreeNode<T> r;
		private boolean heightChange;
		
		private Return0(VrAvlTreeNode<T> r,boolean heightChange) {
			this.r=r;
			this.heightChange=heightChange;
		}
	}
}
