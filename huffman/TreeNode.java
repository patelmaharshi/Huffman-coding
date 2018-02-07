package huffman;

class TreeNode {
	char nodechar;
	static int size;
	int frequency;
	StringBuffer code;
	TreeNode left,right;
	TreeNode(char ch,int fr){
		nodechar=ch;
		frequency=fr;
		size++;
		left=right=null;
	}
	TreeNode(char ch,int fr,TreeNode left,TreeNode right){
		nodechar=ch;
		frequency=fr;
		size++;
		this.left=left;
		this.right=right;
	}
}
