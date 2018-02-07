package huffman;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Huffman {
	private static final String DEFAULT_TEXT = "zzzzzzzzzzxxcnnnnnmmmmmmmmlll";
	private String inputtext,outputtext;
	private byte[] byteinput,byteoutput;
	private int charcounts[],totalchars,specialcount;
	private TreeNode tnodes[];
	Huffman(){
		System.out.println("taking "+DEFAULT_TEXT+" as input");
		inputtext=DEFAULT_TEXT;
		byteinput=DEFAULT_TEXT.getBytes();
		outputtext=inputtext;
		charcounts=new int[26];
	}
	Huffman(String path){
		try {
			byteinput=Files.readAllBytes(Paths.get(path));
			inputtext=new String(byteinput).replaceAll("[^a-z]", "#");
		} catch (IOException e) {
			System.out.println("cannot read file :"+path+" taking "+DEFAULT_TEXT+" as input");
			inputtext=DEFAULT_TEXT;
			byteinput=DEFAULT_TEXT.getBytes();
		}
		outputtext=inputtext;
		charcounts=new int[26];
	}
	void printInput(){
		System.out.println("------------------------------------------------\nInput (Only lowercase alphabet are supported, other characters are converted to #) :\n------------------------------------------------\n"+inputtext+"\n------------------------------------------------");
	}
	void frequencyCalculate() {
		for (char element : inputtext.toCharArray()) {
			try{
				charcounts[element-'a']++;
			} catch (ArrayIndexOutOfBoundsException e){
				specialcount++;
			}
		}
	}
	void makeQueue(){
		for(int i=0;i<charcounts.length;i++){
			if(charcounts[i]!=0){
				totalchars++;
			}
		}
		if(specialcount!=0)
			totalchars++;
		tnodes=new TreeNode[totalchars*2-1];
		for(int i=0;i<charcounts.length;i++){
			if(charcounts[i]!=0){
				insertNode(new TreeNode((char)(i+'a'),charcounts[i]));
			}
		}
		if(specialcount!=0)
			insertNode(new TreeNode('#',specialcount));
	}
	void insertNode(TreeNode node){
		int i,j;
		for(i=0;i<TreeNode.size-1;i++){
			if(tnodes[i].frequency>node.frequency)
				break;
		}
		for(j=TreeNode.size-2;j>=i;j--){
			tnodes[j+1]=tnodes[j];
		}
		tnodes[i]=node;
	}
	void buildTree(){
		int looping=tnodes.length-3;
		for(int i=0;i<=looping;i+=2){
			insertNode(new TreeNode('!',tnodes[i].frequency+tnodes[i+1].frequency,tnodes[i],tnodes[i+1]));
		}
		System.out.println("------------------------------------\nCharcter\tFrequency\tCode\n------------------------------------");
		createCode(tnodes[tnodes.length-1],new StringBuffer());
		System.out.println("------------------------------------");
	}
	void createCode(TreeNode node,StringBuffer codestring){
		if(node.left!=null){
			codestring.append(0);
			createCode(node.left,codestring);
			codestring.deleteCharAt(codestring.length()-1);
		}
		if(node.right!=null){
			codestring.append(1);
			node.right.code=codestring;
			createCode(node.right,codestring);
			codestring.deleteCharAt(codestring.length()-1);
		}
		if(node.left==null&&node.right==null){
			node.code=codestring;
			outputtext=outputtext.replaceAll(String.valueOf(node.nodechar), codestring.toString());
			System.out.println(node.nodechar+"\t\t"+node.frequency+"\t\t"+node.code);
		}
	}
	void makeOutputFile(String outputpath){
		try {
			byteoutput=new BigInteger(outputtext,2).toByteArray();
			Files.write( Paths.get(outputpath),byteoutput, StandardOpenOption.CREATE);
			System.out.println("------------------------------------------------\nSaved output in file"+outputpath+"\n------------------------------------------------");
		} catch (IOException e) {
			System.out.println("cannot open/create file :"+outputpath);
		}
	}
	void printEncoded(){
		System.out.println("------------------------------------------------\nOutput :\n------------------------------------------------\n"+outputtext+"\n------------------------------------------------");
	}
	Double getRatio(){
		return ((double)byteoutput.length/byteinput.length*100);
	}
}
