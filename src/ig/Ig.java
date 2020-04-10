package ig;

import fg.*;
import nasm.*;
import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class Ig {
    public Graph graph;
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
			manageEdge(fgs.in.get(inst));
			manageEdge(fgs.out.get(inst));
		}
    }

    private void initializeNodes() {
		for (NasmInst inst : nasm.listeInst)
			graph.newNode();
		int2Node = graph.nodeArray();
	}

	// TODO: rename
	private void manageEdge(IntSet refSet) {
    	if (refSet.getSize() < 2)
    		return;
    	for (int i=0 ; i < nasm.getTempCounter()-1 ; i++) {
    		if (!refSet.isMember(i))
    			continue;
    		for (int j= i+1 ; j < nasm.getTempCounter() ; j++) {
				if (!refSet.isMember(j))
					continue;
				graph.addEdge(int2Node[i], int2Node[j]);
			}
		}
	}

    public int[] getPrecoloredTemporaries()
    {
    	int[] precoloredTemporaries = new int[nasm.getTempCounter()];
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
    	if (op.isGeneralRegister())
    		precoloredTemporaries[((NasmRegister) op).val] = ((NasmRegister) op).color;
	}


    public void allocateRegisters(){
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
	    
    

    
    
