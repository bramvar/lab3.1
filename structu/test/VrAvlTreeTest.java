package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.ElementException;
import vrAVLTree.VrAvlTree;

class VrAvlTreeTest {

	private VrAvlTree<Double> tree;
	
	public void scenario1() {
		tree = new VrAvlTree<Double>();
		try {
			tree.insert(9.0);
		} catch (ElementException e) {
			e.printStackTrace();
		}
	}
	
	public void scenario2() {
		tree = new VrAvlTree<Double>();
		try {
			tree.insert(9.0);
			tree.insert(12.0);
			tree.insert(7.0);

			// tree.insert(10);
		} catch (ElementException e) {
			e.printStackTrace();
		}
	}
	
	public void scenario3() {
		tree = new VrAvlTree<Double>();
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
		assertTrue(tree.getRoot() != null);
		assertTrue(tree.getRoot().getElem() == 9.0);
		assertTrue(tree.getRoot().getBalance() == 0);
	}
	
	@Test
	void test1() {
		scenario2();
		assertFalse(tree.getRoot() == null);
		assertTrue(tree.getRoot().getElem() == 9.0);
		assertTrue(tree.getRoot().getBalance() == 0);
		assertFalse(tree.getRoot().getRightN().getBalance() == -1);
		assertFalse(tree.getRoot().getLeftN().getBalance() == 1);
		assertTrue(tree.getRoot().getLeftN().getElem() == 7.0);
		assertTrue(tree.getRoot().getLeftN().getBalance() == 0);
		assertTrue(tree.getRoot().getRightN().getBalance() == 0);
		assertTrue(tree.getRoot().getRightN().getElem() == 12.0);
	}
	
	@Test
	void test2() {
		scenario3();
		assertTrue(tree.getRoot() != null);
		assertTrue(tree.getRoot().getElem() == 9.0);
		assertTrue(tree.getRoot().getBalance() == 0);
		assertTrue(tree.getRoot().getLeftN().getRightN().getElem() == 7.5);
		assertTrue(tree.getRoot().getRightN().getRightN().getRightN().getElem() == 15.0);

	}

}
