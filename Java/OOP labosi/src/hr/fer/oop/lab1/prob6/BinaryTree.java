package hr.fer.oop.lab1.prob6;
public class BinaryTree {
 TreeNode root;
 public BinaryTree(){
	TreeNode node = new TreeNode (null);
	root = node;
 }
 public void insert(String data) {
 if (this.root.left == null && this.root.right == null && this.root.data == null) {
	 this.root.data = data;	
	 return;
 }
 else {
     TreeNode node = this.root;
     TreeNode parent = new TreeNode(null);
     
     while(node!=null) {
         parent = node;
         if (node.data.compareTo(data)>0) {
             node=node.left;
         }
         else{
             node=node.right;
         }
         
     }
     if (parent.data.compareTo(data)>0) {
         parent.left=new TreeNode(data);
     }
     else {
         parent.right=new TreeNode(data);
     }
     }
 } 
 
 private boolean subTreeContainsData(TreeNode node, String data) {
	 if (node == null) return false;
	 if ((node.data).compareTo(data) == 0) return true;
	 
 TreeNode temp = node;
 return  (subTreeContainsData(temp.left, data) || subTreeContainsData(temp.right, data));
 }

 public boolean containsData(String data) {
 return subTreeContainsData(root, data);
 }
 
 private int sizeOfSubTree(TreeNode node) {
 
 if (node == null) return 0;
 return 1 + (sizeOfSubTree(node.left) + sizeOfSubTree(node.right));
 }

 public int sizeOfTree() {
 return sizeOfSubTree(root);
 }

 private void writeSubTree(TreeNode node) {
	 
	 if (node!=null) {
	        writeSubTree(node.left);
	        System.out.println(node.data);
	        writeSubTree(node.right);
	        }
	        return;
	 
	  }

 public void writeTree() {
 writeSubTree(root);
 }
 private void reverseSubTreeOrder(TreeNode node) {
	 if (node==null) return;
     TreeNode helpNode = new TreeNode(null);
     helpNode=node.left;
     node.left=node.right;
     node.right=helpNode;
     reverseSubTreeOrder(node.left);
     reverseSubTreeOrder(node.right);
	 }
 
 public void reverseTreeOrder() {
 reverseSubTreeOrder(root);
 }
 public static void main(String[] args) {
 
 BinaryTree tree = new BinaryTree();
 tree.insert("Jasna");
 tree.insert("Ana");
 tree.insert("Ivana");
 tree.insert("Anamarija");
 tree.insert("Vesna");
 tree.insert("Kristina");
System.out.println("Writing tree inorder:");
 tree.writeTree();
 tree.reverseTreeOrder();
System.out.println("Writing reversed tree inorder:");
tree.writeTree();
int size = tree.sizeOfTree();
System.out.println("Number of nodes in tree is "+size+".");
boolean found = tree.containsData("Ivana");
System.out.println("Searched element is found: "+found);
 }
}
