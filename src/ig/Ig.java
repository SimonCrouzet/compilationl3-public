package ig;

import fg.*;
import nasm.*;
import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class Ig {
	static final int K = 4;

    public Graph graph;
    public ColorGraph colorGraph;
    public FgSolution fgs;
    public int regNb;
    public Nasm nasm;
    public Node int2Node[];

    
    public Ig(FgSolution fgs){
		this.fgs = fgs;
		this.graph = new Graph();
		this.nasm = fgs.nasm;
		this.regNb = this.nasm.getTempCounter();
		this.int2Node = new Node[regNb];
		this.construction();
    }

    public void construction(){
		initializeNodes();
		for (NasmInst inst : nasm.listeInst) {
			initializeEdges(fgs.in.get(inst));
			initializeEdges(fgs.out.get(inst));
		}
    }

    private void initializeNodes() {
		for (int i=0 ; i<regNb ; i++)
			graph.newNode();
		int2Node = graph.nodeArray();
	}

	private void initializeEdges(IntSet refSet) {
    	if (refSet.isEmpty())
    		return;
    	for (int i=0 ; i < regNb ; i++) {
    		if (!refSet.isMember(i))
				continue;
    		for (int j= i+1 ; j < regNb ; j++) {
				if (!refSet.isMember(j))
					continue;
				graph.addNOEdge(int2Node[i], int2Node[j]);
			}
		}
	}

    public int[] getPrecoloredTemporaries()
    {
    	int[] precoloredTemporaries = new int[graph.nodeCount()];
    	for (NasmInst inst : nasm.listeInst) {
    		colorTemporary(inst.source, precoloredTemporaries);
    		colorTemporary(inst.destination, precoloredTemporaries);
		}
    	return precoloredTemporaries;
    }

    private void colorTemporary(NasmOperand op, int[] precoloredTemporaries) {
    	if (op == null)
    		return;
    	if (op instanceof NasmRegister)
    		getColor(op, precoloredTemporaries);
    	if (op instanceof NasmAddress) {
    		getColor(((NasmAddress) op).base, precoloredTemporaries);
    		getColor(((NasmAddress) op).offset, precoloredTemporaries);
		}
	}

	private void getColor(NasmOperand op, int[] precoloredTemporaries) {
    	if (op == null || !(op instanceof NasmRegister))
    		return;
    	if (op.isGeneralRegister() && ((NasmRegister) op).color != Nasm.REG_UNK)
    		precoloredTemporaries[((NasmRegister) op).val] = ((NasmRegister) op).color;
	}

	private void color(NasmOperand op, int[] colors) {
		if (op == null || !(op instanceof NasmRegister))
			return;
		if (op.isGeneralRegister() && ((NasmRegister) op).color == Nasm.REG_UNK)
			((NasmRegister) op).colorRegister(colors[((NasmRegister) op).val]);
	}

	private void allocateColor(NasmOperand op, int[] colors) {
		if (op == null)
			return;
		if (op instanceof NasmRegister)
			color(op, colors);
		if (op instanceof NasmAddress) {
			color(((NasmAddress) op).base, colors);
			color(((NasmAddress) op).offset, colors);
		}
	}

    public void allocateRegisters(){
    	System.out.println(Arrays.toString(getPrecoloredTemporaries()));
    	colorGraph = new ColorGraph(graph, K, getPrecoloredTemporaries());
    	System.out.println(Arrays.toString(colorGraph.couleur));
    	for (NasmInst inst : nasm.listeInst) {
    		allocateColor(inst.source, colorGraph.couleur);
    		allocateColor(inst.destination, colorGraph.couleur);
		}
    }


    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;
	
	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".ig";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	
	for(int i = 0; i < regNb; i++){
	    Node n = this.int2Node[i];
	    out.print(n + " : ( ");
	    for(NodeList q=n.succ(); q!=null; q=q.tail) {
		out.print(q.head.toString());
		out.print(" ");
	    }
	    out.println(")");
	}
    }
}
	    
    

    
    
