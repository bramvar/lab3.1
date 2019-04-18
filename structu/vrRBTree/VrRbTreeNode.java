package vrRBTree;

import exceptions.ElementException;

public class VrRbTreeNode <T extends Comparable<? super T>>{
	
	private static final int BLACK=1;
	private static final int RED=0;
	
	private VrRbTreeNode<T> rightChild;
	private VrRbTreeNode<T> leftChild;
	private T elem;
	private int color;
	private VrRbTreeNode<T> parent;
	
	
	public VrRbTreeNode(T elem) {
		this.elem=elem;
		color=RED;
		setRightChild(new VrRbTreeNode<T>());
		setLeftChild(new VrRbTreeNode<T>());
		parent=null;
	}
	
	public VrRbTreeNode() {
		this.elem=null;
		color=BLACK;
		parent=null;
	}
	
	public VrRbTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(VrRbTreeNode<T> rightChild) {
		if(rightChild!=null) {
			rightChild.setParent(this);
			this.rightChild=rightChild;
		}
	}

	public VrRbTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(VrRbTreeNode<T> leftChild) {
		if(leftChild!=null) {
			leftChild.setParent(this);
			this.leftChild=leftChild;
		}
	}

	public T getElem() {
		return elem;
	}

	public void setElem(VrRbTreeNode<T> e) {
		if(e.elem!=null) {
			T aux=elem;
			elem=e.elem;
			e.elem=aux;
		}
		else {
			elem=null;
			color=BLACK;
			rightChild=leftChild=null;
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public VrRbTreeNode<T> getParent() {
		return parent;
	}

	public void setParent(VrRbTreeNode<T> parent) {
		this.parent = parent;
	}
	
	public VrRbTreeNode<T> getUncle(){
		if(parent==null||parent.parent==null)
			return null;
		else {
			if(parent.parent.isRightChild(parent))
				return parent.parent.leftChild;
			else
				return parent.parent.rightChild;
		}
	}

	public boolean isLeftChild(VrRbTreeNode<T> p){
		return leftChild==p;
	}
	
	public boolean isRightChild(VrRbTreeNode<T> p) {
		return rightChild==p;
	}
	
	public boolean isLeafLeftChild() {
		return leftChild.elem==null;
	}
	
	public boolean isLeafRightChild() {
		return rightChild.elem==null;
	}
	
	public VrRbTreeNode<T> minimun(){
		return isLeafLeftChild()?this:leftChild.minimun();
	}
	
	public VrRbTreeNode<T> maximun(){
		return isLeafRightChild()? this:rightChild.minimun();
	}
	
	public boolean isLeaf() {
		return elem==null;
	}
	
	public boolean exists(T p) {
		try {
			getNode(p);
			return true;
		}catch(ElementException e){
			return false;
		}
	}
	
	public VrRbTreeNode<T> getNode(T elem) throws ElementException{
		
		if(elem.compareTo( this.elem )==0) 
			return this;
		else if (elem.compareTo( this.elem )<0) {
			if(!isLeafLeftChild())
				return leftChild.getNode(elem);
			else
				throw new ElementException(ElementException.NO_SUCH_ELEMENT);
		}
		else {
			if(!isLeafRightChild())
				return rightChild.getNode(elem);
			else
				throw new ElementException(ElementException.NO_SUCH_ELEMENT);
				
		}
			
		
	}
	
	public boolean isBlackRightChild() {
		return rightChild.color==BLACK;
	}
	
	public boolean isBlackLeftChild() {
		return leftChild.color==BLACK;
	}
	
	public boolean blackChildren() {
		return isBlackRightChild()&&isBlackLeftChild();
	}
	
	public VrRbTreeNode<T> getBrother(){
		if(parent==null)
			return null;
		else
			return parent.isRightChild(this)?parent.leftChild:parent.rightChild;
	}
	
	public VrRbTreeNode<T> leftRotate() {
		if(isLeafRightChild())
			return this;
		else {
			VrRbTreeNode<T> rightAux=rightChild;
			 setRightChild(rightAux.getLeftChild());
			 rightAux.setParent(parent);
			 rightAux.setLeftChild(this);
			 return rightAux;
		}
	}
	
	public VrRbTreeNode<T> rightRotate(){
		if(isLeafLeftChild())
			return this;
		else {
			VrRbTreeNode<T> leftAux=leftChild;
			 setLeftChild(leftAux.getRightChild());
			 leftAux.setParent(parent);
			 leftAux.setLeftChild(this);
			 return leftAux;
		}
	}
	
	public VrRbTreeNode<T> insert(VrRbTreeNode<T> node) throws ElementException{
		insertN(node);
		Return0 r =new Return0(null);
		node
	}
	
	public void insertN(VrRbTreeNode<T> node) throws ElementException {
		if(elem.compareTo(node.getElem())==0) 
			throw new ElementException(ElementException.ELEMENT_EXIST);
		else if(elem.compareTo(node.getElem())<0) {
			if(isLeafRightChild()) {
				rightChild=node;
				node.setParent(this);
			}
			else
				rightChild.insertN(node);
		}
		else {
			if(isLeafLeftChild()) {
				leftChild=node;
				node.setParent(this);
			}
			else
				leftChild.insertN(node);;	
		}
	}
	
	public VrRbTreeNode<T> insertCase1( Return0 r ){
	if(parent== null ){
		color = BLACK;
		r.r = this;
	}
	else{
		insertCase2( r );
	}
		return r.r;
	}

	public void insertCase2( Return0 r )	{
	if( parent.getColor( )==RED)
		insertCase3( r );
	else
		r.r=null;

	}

	public void insertCase3( Return0 r )	{
		VrRbTreeNode<T> uncle = getUncle( );
		VrRbTreeNode<T> gParent = parent.getParent( );
		r.r = null;

		if( !uncle.isLeaf( ) && uncle.getColor( )==RED ){
			getParent( ).setColor(BLACK);
			uncle.setColor(BLACK);
			gParent.setColor(RED);
			gParent.insertCase1( r );
		}
		else{
			insertCase4( r );
		}
	}
	
	public void insertCase4( Return0 r ){
		VrRbTreeNode<T>  gParent = parent.getParent( );
		r.r= null;

		if(parent.isRightChild( this ) && gParent.isLeftChild(parent) )	{
			gParent.setLeftChild(parent.leftRotate());
			leftChild.insertCase5( r );
		}
		else if(parent.isLeftChild(this) && gParent.isRightChild(parent)){
			gParent.setRightChild( parent.rightRotate());
			rightChild.insertCase5( r );
		}
		else{
			insertCase5( r );
		}
	}
	
	private void insertCase5( Return0 r ){
		VrRbTreeNode<T>  gParent =parent.getParent( );
		parent.setColor(BLACK);
		gParent.setColor(RED);

		if( parent.isLeftChild(this) && gParent.isLeftChild(parent)){
			if( gParent.getParent()==null )
				gParent.rightRotate();
			else if( gParent.getParent().isRightChild(gParent))
				gParent.getParent( ).setRightChild( gParent.rightRotate());
			else
				gParent.getParent().setLeftChild( gParent.rightRotate());

		}
		else{
			if(gParent.getParent()== null )
				gParent.leftRotate( );
			else if(gParent.getParent( ).isRightChild(gParent))
				gParent.getParent().setRightChild(gParent.leftRotate() );
			else
				gParent.getParent( ).setLeftChild(gParent.leftRotate() );
			}
		r.r= parent;
	}

	public VrRbTreeNode<T>  eliminar( ){

		VrRbTreeNode<T>  re= !isLeafLeftChild()? leftChild.maximun(): this.minimun();
		setElem( re);

		Return0 r = new Return0( null );
		re.eliminarRojoNegro( r );

		return r.r;
	}

	public void eliminarHijos( )
	{
		rightChild = new VrRbTreeNode<T>() ;
		leftChild = new VrRbTreeNode<T>();
	}
	
	public void eliminarRojoNegro( Return0 r )
	{
		VrRbTreeNode<T> child = !isLeafRightChild() ? rightChild: leftChild;
		int colorD = getColor( );
		int colorChild = child.getColor( );

		setElem(child);
		eliminarHijos( );

		if( colorChild ==RED){

			r.r= this;
			return;
		}
		else if( colorChild ==BLACK&& colorD ==RED){
	
			r.r = this;
			setColor(BLACK);
		}
		else{
			deleteCase1( r );
		}
	}
	
	public void deleteCase1( Return0 r ){
		if(parent!= null )
			this.deleteCase2( r );
		else
			r.r= null;
	}
	
	public void deleteCase2( Return0 r )
	{
		VrRbTreeNode<T> bro = getBrother();

		if( bro.color ==RED){
			parent.color = RED;
			bro.color = BLACK;

			r.r=bro;

			VrRbTreeNode<T> gParent = parent.parent;
			if( parent.isRightChild( this) ){
				if(gParent!= null ){
					if(gParent.isRightChild(parent) )
						gParent.setRightChild(parent.rightRotate() );
					else
						gParent.setLeftChild(parent.rightRotate() );
				}
				else
					parent.rightRotate();
			}
			else{
				if(gParent!= null ){
					if( gParent.isRightChild(parent) )
						gParent.setRightChild(parent.leftRotate() );
					else
						gParent.setLeftChild(parent.leftRotate() );
				}
				else
					parent.leftRotate();
			}
		}
		deleteCase3(r);
	}
	
	public void deleteCase3( Return0 r){

		VrRbTreeNode<T> bro = getBrother( );

		if( parent.color ==BLACK && bro.color ==BLACK && bro.blackChildren()){
			bro.setColor(RED);
			parent.deleteCase1( r );
		}
		else{
			deleteCase4( r );
		}
	}
	
	public void deleteCase4( Return0 r ){
	
		VrRbTreeNode<T> bro = getBrother( );

		if( parent.color ==RED && bro.color ==BLACK && bro.blackChildren()){
			bro.setColor(RED);
			parent.setColor(BLACK);
		}
		else{
			deleteCase5( r );
		}
	}
	
	public void deleteCase5( Return0 r )
	{
	
		VrRbTreeNode<T> bro = getBrother( );

	if( parent.isLeftChild( this) && bro.color ==BLACK&& !bro.isBlackLeftChild() && bro.isBlackRightChild()){
		bro.color = RED;
		bro.leftChild.color =BLACK;
		parent.setRightChild(bro.rightRotate( ) );
	}
	else if( parent.isRightChild( this ) && bro.color == BLACK && !bro.isBlackRightChild() && bro.isBlackLeftChild() ){
		bro.color =RED;
		bro.rightChild.color = BLACK;
		parent.setLeftChild(bro.leftRotate() );
	}
	deleteCase6( r );
	}
	
	public void deleteCase6( Return0 r )
	{
		VrRbTreeNode<T> bro = getBrother( );

		bro.color = parent.color;
		parent.color = BLACK;
		VrRbTreeNode<T>  gParent = parent.parent;

		r.r =bro;

		if( parent.isLeftChild( this ) ){
			bro.rightChild.color =BLACK;

			if(gParent != null ){
				if( gParent.isRightChild(parent) )
					gParent.setRightChild(parent.leftRotate() );
				else
					gParent.setLeftChild(parent.leftRotate( ) );
			}
			else
				parent.leftRotate( );
		}
		else
		{
			bro.leftChild.color = BLACK;

			if(gParent != null ){
				if( gParent.isRightChild(parent) )
					gParent.setRightChild(parent.rightRotate( ) );
				else
					gParent.setLeftChild(parent.rightRotate( ) );
			}
			else
				parent.rightRotate();
		}
	}

	


	private class Return0{
		
		private VrRbTreeNode<T> r;
		
		private Return0(VrRbTreeNode<T> r) {
			this.r=r;
		}
	
	}

}
