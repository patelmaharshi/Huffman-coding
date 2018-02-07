package huffman;

public class MainClass {

	public static void main(String[] args) {
		Huffman hman;
		if(args.length==0){
			System.out.println("USAGE:java MainClass [inputfile] [outputfile]");
			hman = new Huffman();
		}
		else{
			hman=new Huffman(args[0]);
		}
		hman.printInput();
		hman.frequencyCalculate();
		hman.makeQueue();
		hman.buildTree();
		if(args.length==2)
			hman.makeOutputFile(args[1]);
		else
			hman.makeOutputFile("output.txt");
		hman.printEncoded();
		System.out.println("Compression Ratio : "+hman.getRatio());
	}

}
