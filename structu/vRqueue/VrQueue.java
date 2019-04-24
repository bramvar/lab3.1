package vRqueue;

import interfaces.IvrQueue;

public class VrQueue<E> implements IvrQueue<E>{

	private VrNode<E> first;
	private int size;
	
	public VrQueue(){
		first=null;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean empty() {	
		return first==null;
	}

	@Override
	public E peek() {
		if(empty())
			return null;
		else if(size()==1) {
			E aux=first.getData();

			return aux;
		}
		else {
			VrNode<E> lastNode=first.getPrev();
			E aux=lastNode.getData();
			
			return aux;
		}
	}

	@Override
	public void offer(E element) {
		VrNode<E> newElement=new VrNode<E>(element);
		if(empty()) {
			newElement.setNext(newElement);
			newElement.setPrev(newElement);
			first=newElement;
		} else {
			VrNode<E> lastNode=first.getPrev();
			newElement.setNext(first);
			newElement.setPrev(lastNode);
			first.setPrev(newElement);
			lastNode.setNext(newElement);
			first=newElement;
		}
		size++;
	}

	@Override
	public E poll() {
		if(empty())
			return null;
		else if(size()==1) {
			E aux=first.getData();
			first=null;
			size--;
			
			return aux;
		}
		else {
			VrNode<E> lastNode=first.getPrev();
			E aux=lastNode.getData();
			lastNode=lastNode.getPrev();
			first.setPrev(lastNode);
			lastNode.setNext(first);
			size--;
			
			return aux;
		}
	}
}
