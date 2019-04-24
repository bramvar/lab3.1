package vRqueue;


public class VrNode<E> {
	
	private E data;
	private VrNode<E> next,prev;
	
	VrNode(E data){
		this(data,null,null);
	}
	
	VrNode(E data, VrNode<E> next, VrNode<E> prev){
		this.data=data;
		this.next=next;		
		this.prev=prev;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public VrNode<E> getNext() {
		return next;
	}

	public void setNext(VrNode<E> next) {
		this.next = next;
	}

	public VrNode<E> getPrev() {
		return prev;
	}

	public void setPrev(VrNode<E> prev) {
		this.prev = prev;
	}
	

}
