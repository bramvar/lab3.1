package vrAVLTree;

import exceptions.ElementException;

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
	
	public VrAvlTreeNode<T> maxNode( ){
		return ( rightN == null ) ? this : rightN.maxNode( );
	}

	public VrAvlTreeNode<T> minNode( ){
		return ( leftN == null ) ? this : leftN.minNode( );
	}
	
	public int getHeight() {
		int a1 = ( leftN == null ) ? 0 : leftN.getHeight( );
		int a2 = ( rightN == null ) ? 0 : rightN.getHeight( );
		return ( a1 >= a2 ) ? a1 + 1 : a2 + 1;
	}
	
	public T maximun( ){
		VrAvlTreeNode<T>  n = maxNode( );
		return ( n== null ) ? null : n.getElem( );
	}
	
	public T darMenor( ){
		VrAvlTreeNode<T> n = minNode( );
	return ( n == null ) ? null : n.getElem( );
	}
	
	public  VrAvlTreeNode<T>  insert( T elem ) throws ElementException{
	
		Return0 r = new Return0( null, false );
		insert2( elem, r);
		return r.r;
	}
	
	public T search( T elem )
	{
		int res = elem.compareTo( elem );
		if( res == 0 ) {
			return elem;
		}
		else if( res > 0 ){
			return ( leftN != null ) ? leftN.search( elem ) : null;
		}
		else{
			return ( rightN != null ) ? rightN.search( elem ) : null;
		}
	}
	
	public VrAvlTreeNode<T>  delete( T pElemento ) throws ElementException{
		Return0 r = new Return0( null, false );
		delete2( pElemento, r);
		return r.r;
	}
	
	private void rightBalanceDelete( Return0 r ){
		switch( balance ){
			case BLEFT:
				balance = BAL;
				r.r = this;
				break;
			case BAL:
				balance = BRIGHT;
				r.heightChange = false;
				r.r = this;
				break;
			case BRIGHT:
				if( rightN.balance != BLEFT ){
					r.r= leftRotate( );
					if( r.r.balance == BAL ){
						r.r.balance = BLEFT;
						r.r.leftN.balance = BRIGHT;
						r.heightChange = false;
					}
					else{
						r.r.balance = BAL;
						r.r.leftN.balance = BAL;
					}
				}
				else{
					r.r= rightLeftRotate( );
					if( r.r.balance == BRIGHT ){
						r.r.leftN.balance = BLEFT;
					}
					else{
						r.r.leftN.balance = BAL;
					}
					if( r.r.balance == BLEFT ){
						r.r.rightN.balance = BRIGHT;
					}
					else{
						r.r.rightN.balance = BAL;
					}
					r.r.balance = BAL;
				}
				break;
		}
	}
	
	public void leftBalanceDelete( Return0 r ){
		switch( balance ){
			case BLEFT:
				if( leftN.balance != BRIGHT ){
					r.r = rightRotate( );
					if( r.r.balance == BAL ){
						r.r.balance = BRIGHT;
						r.r.rightN.balance = BLEFT;
							r.heightChange= false;
					}
					else{
						r.r.balance = BAL;
						r.r.rightN.balance = BAL;
					}
				}
				else{
					r.r = leftRightRotate( );
					if( r.r.balance == BLEFT){
						r.r.rightN.balance = BRIGHT;
					}
					else{
						r.r.rightN.balance = BAL;
					}
					if( r.r.balance == BRIGHT ){
						r.r.leftN.balance = BLEFT;
					}
					else{
						r.r.leftN.balance = BAL;
					}
					r.r.balance = BAL;
				}
				break;
			case BAL:
				balance = BLEFT;
				r.heightChange=false;
				r.r = this;
				break;
			case BRIGHT:
				balance = BAL;
				r.r = this;
				break;
		}
	}
	
	private void delete2( T elem, Return0 r) throws ElementException{
		int res = elem.compareTo(elem );
		if( res == 0 ){
	
			if( leftN == null & rightN == null ){
				r.heightChange= true;
				r.r= null;
			}
			else if( leftN == null ){
				r.r = rightN;
				r.heightChange = true;
			
			}
			else{
				VrAvlTreeNode<T> ree = leftN.maxNode( );
				elem = ree.elem;
				leftN.delete2( ree.elem, r );
				leftN = r.r;

				if( r.heightChange ){
					rightBalanceDelete( r);
				}
				else{
					r.r= this;
				}
			}
		}
		else if( res > 0 ){
	
			if( leftN == null )	{
				throw new ElementException(ElementException.NO_SUCH_ELEMENT);
			}
			leftN.delete2( elem, r);
			leftN = r.r;

			if( r.heightChange ){
				rightBalanceDelete( r );
			}
			else{
				r.r = this;
			}
		}
		else{
	
			if( rightN == null ){
				throw new ElementException(ElementException.NO_SUCH_ELEMENT);
			}
			rightN.delete2(elem, r);
			rightN = r.r;	
			
			if( r.heightChange ){
				leftBalanceDelete( r);
			}
			else{
				r.r= this;
			}
		}	
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
