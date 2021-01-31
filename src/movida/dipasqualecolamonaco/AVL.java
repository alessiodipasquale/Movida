package movida.dipasqualecolamonaco;

import java.lang.reflect.Array;
import java.util.Set;

public class AVL<K extends Comparable<K>,V extends Object> extends Map<K,V> {
	V t;
	private int numNodes = 0;

	private Node root;

    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    int height(Node n) {
        return n == null ? -1 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }
    
    Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    
    Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    
    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }
        return z;
    }
	
	public AVL() {}
	
	public void delete(K key) {
		key = (K)key.toString().trim().toLowerCase();
		this.root = this.delete(root,key);
	}
	
	Node delete(Node node, K key) {
	    if (node == null) {
	        return node;
	    } else if (node.key.compareTo(key) > 0) {
	        node.left = delete(node.left, key);
	    } else if (node.key.compareTo(key) < 0) {
	        node.right = delete(node.right, key);
	    } else {
	        if (node.left == null || node.right == null) {
	            node = (node.left == null) ? node.right : node.left;
		    	if(this.numNodes > 0)this.numNodes--;
	        } else {
	            Node mostLeftChild = mostLeftChild(node.right);
	            node.key = mostLeftChild.key;
	            node.right = delete(node.right, (K) node.key);
	        }
	    }
	    if (node != null) {
	        node = rebalance(node);
	    }
	    return node;
	}
	
	private Node mostLeftChild(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
	
	@Override
	public void putIfAbsent(K key, V value) {
		key = (K)key.toString().trim().toLowerCase();
		this.root = this.insert(root, key, value);
	}
	
	Node insert(Node node, K key, V value) {
	    if (node == null) {
	    	this.numNodes++;
	        return new Node(key, value);
	    } else if (node.key.compareTo(key) > 0) {
	        node.left = insert(node.left, key, value);
	    } else if (node.key.compareTo(key) < 0) {
	        node.right = insert(node.right, key, value);
	    } /* else {
	        throw new RuntimeException("duplicate Key");
	    }*/
	    return rebalance(node);
	}
	
	@Override
	public V search(K key) throws KeyException {
		key = (K)key.toString().trim().toLowerCase();
		 Node current = root;
	        while (current != null) {
	            if (current.key.compareTo(key) == 0) {
	               break;
	            }
	            current = current.key.compareTo(key) < 0 ? current.right : current.left;
	        }
	        return (V)current.value;
	}
	/*public V search(K key) {
		
	}*/

	@Override
	public int length() {
		return this.numNodes;
	}  

	@Override
	public void clear() {
		this.root = null;
		
	}

	@Override
	public Set<Map<K, V>.Data> getData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
