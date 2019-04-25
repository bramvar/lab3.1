package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.ElementException;
import interfaces.IvrTree;
import interfaces.IvrTreeO;
import vrRBTree.VrRbTree;
import vrRBTree.VrRbTreeNode;

class VrRBTreeTest {
	
	private VrRbTree<Double> tree;
	
	public void scenario1() {
		tree=new VrRbTree<Double>();
		try {
			tree.insert(9.0);
		} catch (ElementException e) {
			e.printStackTrace();
		}
	}
	
	public void scenario2() {
		tree=new VrRbTree<Double>();
		try {
			tree.insert(9.0);
			tree.insert(12.0);
			tree.insert(7.0);
			
			//tree.insert(10);
		} catch (ElementException e) {
			e.printStackTrace();
		}
	}
	
	public void scenario3() {
		tree=new VrRbTree<Double>();
		try {
			tree.insert(9.0);
			tree.insert(12.0);
			tree.insert(7.0);
			tree.insert(6.0);
			tree.insert(7.5);
			tree.insert(10.0);
			tree.insert(14.0);
			tree.insert(9.5);
			tree.insert(11.0);
			tree.insert(13.0);
			tree.insert(15.0);
			tree.insert(6.5);
		} catch (ElementException e) {
			e.printStackTrace();
		}
	}

	@Test
	void test0() {
		scenario1();
		assertFalse(tree.getRoot()==null);
		assertTrue(tree.getRoot().getColor()==VrRbTreeNode.BLACK&&tree.getRoot().getElem()==9);
		assertTrue(tree.getRoot().blackChildren());
	}
	
	@Test
	void test1() throws ElementException {
		scenario2();
		assertFalse(tree.getRoot().blackChildren());
		assertTrue(tree.getRoot().getLeftChild().getElem()==7&&tree.getRoot().getRightChild().getElem()==12);
		tree.insert(6.0);
		assertTrue(tree.getRoot().getElem()==9);
		assertTrue(tree.getRoot().blackChildren());
		assertTrue(tree.getRoot().getLeftChild().getLeftChild().getColor()==VrRbTreeNode.RED);
		assertTrue(tree.minimun()==6);
		tree.insert(7.5);
		tree.insert(10.0);
		tree.insert(14.0);
		tree.insert(9.5);
		assertTrue(tree.getRoot().getRightChild().blackChildren());
		assertTrue(tree.getRoot().getRightChild().getColor()==VrRbTreeNode.RED);
		tree.insert(11.0);
		tree.insert(13.0);
		tree.insert(15.0);
		tree.insert(6.5);
		assertTrue(tree.getRoot().getLeftChild().getElem()==7.0&&tree.getRoot().getLeftChild().getColor()==VrRbTreeNode.RED);
		assertTrue(tree.getRoot().getLeftChild().blackChildren());
		
	}
	

}
