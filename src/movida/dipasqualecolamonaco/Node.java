package movida.dipasqualecolamonaco;

public class Node <K extends Comparable<K>, V extends Object> {
	
	public K key;
	public V value;
	
	public Node left;
	public Node right;
	public int height;
	
	public Node() {
		this.key = null;
		this.value = null;
		this.left = null;
		this.right = null;
		this.height = 0;

	}
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
		this.height = 0;
	}
	
	
	public boolean isLeaf() {
		return (this.left == null && this.right == null);
	}
	
}
