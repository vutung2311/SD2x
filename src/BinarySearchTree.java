public class BinarySearchTree<E extends Comparable<E>> {
    protected Node root = null;

    protected void visit(Node n) {
        System.out.println(n.value);
    }

    public boolean contains(E val) {
        return contains(root, val);
    }

    protected boolean contains(Node n, E val) {
        if (n == null) return false;

        if (n.value.equals(val)) {
            return true;
        } else if (n.value.compareTo(val) > 0) {
            return contains(n.leftChild, val);
        } else {
            return contains(n.rightChild, val);
        }
    }

    public boolean add(E val) {
        if (root == null) {
            root = new Node(val);
            return true;
        }
        return add(root, val);
    }

    protected boolean add(Node n, E val) {
        if (n == null) {
            return false;
        }
        int cmp = val.compareTo(n.value);
        if (cmp == 0) {
            return false; // this ensures that the same value does not appear more than once
        } else if (cmp < 0) {
            if (n.leftChild == null) {
                n.leftChild = new Node(val);
                return true;
            } else {
                return add(n.leftChild, val);
            }
        } else {
            if (n.rightChild == null) {
                n.rightChild = new Node(val);
                return true;
            } else {
                return add(n.rightChild, val);
            }
        }
    }

    public boolean remove(E val) {
        return remove(root, null, val);
    }

    protected boolean remove(Node n, Node parent, E val) {
        if (n == null) return false;

        if (val.compareTo(n.value) < 0) {
            return remove(n.leftChild, n, val);
        } else if (val.compareTo(n.value) > 0) {
            return remove(n.rightChild, n, val);
        } else {
            if (n.leftChild != null && n.rightChild != null) {
                n.value = maxValue(n.leftChild);
                remove(n.leftChild, n, n.value);
            } else if (parent == null) {
                root = n.leftChild != null ? n.leftChild : n.rightChild;
            } else if (parent.leftChild == n) {
                parent.leftChild = n.leftChild != null ? n.leftChild : n.rightChild;
            } else {
                parent.rightChild = n.leftChild != null ? n.leftChild : n.rightChild;
            }
            return true;
        }
    }

    protected E maxValue(Node n) {
        if (n.rightChild == null) {
            return n.value;
        } else {
            return maxValue(n.rightChild);
        }
    }

    /*********************************************
     *
     * IMPLEMENT THE METHODS BELOW!
     *
     *********************************************/


    // Method #1.
    public Node findNode(E val) {
        return findNode(root, val);
    }

    public Node findNode(Node root, E val) {
        if (val == null) {
            return null;
        }
        if (root == null) {
            return null;
        }
        if (val.compareTo(root.value) < 0) {
            return findNode(root.leftChild, val);
        }
        if (val.compareTo(root.value) > 0) {
            return findNode(root.rightChild, val);
        }
        return root;
    }

    // Method #2.
    protected int depth(E val) {
        if (val == null) {
            return -1;
        }
        if (findNode(val) == null) {
            return -1;
        }
        return depth(root, val);
    }

    protected int depth(Node root, E val) {
        if (root == null) {
            return 0;
        }
        if (val.compareTo(root.value) < 0) {
            return 1 + depth(root.leftChild, val);
        }
        if (val.compareTo(root.value) > 0) {
            return 1 + depth(root.rightChild, val);
        }
        return 0;
    }

    // Method #3.
    protected int height(E val) {
        if (findNode(val) == null) {
            return -1;
        }
        return height(findNode(val));
    }

    protected int height(Node root) {
        if (root == null) {
            return -1;
        }
        int leftNodeHeight = 0, rightNodeHeight = 0;
        if (root.leftChild != null) {
            leftNodeHeight = 1 + height(root.leftChild);
        }
        if (root.rightChild != null) {
            rightNodeHeight = 1 + height(root.rightChild);
        }
        return Math.max(leftNodeHeight, rightNodeHeight);
    }

    // Method #4.
    protected boolean isBalanced(Node n) {
        if (n == null) {
            return false;
        }
        if (findNode(n.value) == null) {
            return false;
        }
        if (Math.abs(height(n.leftChild) - height(n.rightChild)) >= 2) {
            return false;
        }
        if (n.leftChild != null && !isBalanced(n.leftChild)) {
            return false;
        }
        return n.rightChild == null || isBalanced(n.rightChild);
    }

    // Method #5. .
    public boolean isBalanced() {
        return isBalanced(root);
    }

    class Node {
        E value;
        Node leftChild = null;
        Node rightChild = null;

        Node(E value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BinarySearchTree.Node))
                return false;
            @SuppressWarnings("unchecked")
            Node other = (BinarySearchTree<E>.Node) obj;
            return other.value.compareTo(value) == 0 &&
                    other.leftChild == leftChild && other.rightChild == rightChild;
        }
    }

}
