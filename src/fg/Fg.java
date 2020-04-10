package fg;
import nasm.*;
import util.graph.*;
import java.util.*;
import java.io.*;

public class Fg implements NasmVisitor <Void> {
    public Nasm nasm;
    public Graph graph;
    Map< NasmInst, Node> inst2Node;
    Map< Node, NasmInst> node2Inst;
    Map< String, NasmInst> label2Inst;
    private ArrayList<String> systemCalls = new ArrayList<String>(Arrays.asList("iprintLF", "readline", "atoi"));

    public Fg(Nasm nasm){
	this.nasm = nasm;
	this.inst2Node = new HashMap< NasmInst, Node>();
	this.node2Inst = new HashMap< Node, NasmInst>();
	this.label2Inst = new HashMap< String, NasmInst>();
	this.graph = new Graph();

    initializeNodes(); // initializeEdges();
    for (NasmInst inst : nasm.listeInst)
        inst.accept(this);
    }

    private void initializeNodes() {
        for (NasmInst inst : nasm.listeInst) {
            Node newNode = graph.newNode();
            inst2Node.put(inst, newNode);
            node2Inst.put(newNode, inst);
            if (inst.label != null) {
                NasmLabel label = (NasmLabel) inst.label;
                label2Inst.put(label.toString(), inst);
            }
        }
    }

    private void initializeEdges() {
        for (NasmInst inst : nasm.listeInst) {
            if (inst.source == null || inst.destination == null)
                return;
            Node from = inst2Node.get(label2Inst.get(inst.source.toString()));
            Node to = inst2Node.get(label2Inst.get(inst.destination.toString()));
            if (from == null || to == null)
                return;
            graph.addEdge(from, to);
        }
    }

    private void addEdgeWithInst(NasmInst source, NasmInst destination) {
        if (source == null || destination == null)
            throw new FgException("ERROR: Can't create an edge without a source and a destination");
        Node from = inst2Node.get(source);
        Node to = inst2Node.get(destination);
        if (from == null || to == null)
            throw new FgException("ERROR: No nodes matching with given Source or destination ");
        graph.addEdge(from, to);
    }

    private void addEdgeWithLabel(NasmInst source, NasmLabel destination) {
        if (source == null || destination == null)
            throw new FgException("ERROR: Can't create an edge without a source and a destination");

        if (systemCalls.contains(destination.toString()))
            return; // System call: iprintLF, readline or atoi. No edge needed

        NasmInst destinationInst = label2Inst.get(destination.toString());
        if (destinationInst == null)
            throw new FgException("ERROR: No instruction matching with the given label");
        addEdgeWithInst(source, destinationInst);
    }

    private NasmInst getNextInst(NasmInst previousInst) {
        if (previousInst == null)
            throw new FgException("ERROR: Need previous instruction to find the next one");
        int instIndex = nasm.listeInst.indexOf(previousInst);
        if (instIndex == -1)
            throw new FgException("ERROR: Instruction doesn't appear on the NASM");
        if (instIndex == nasm.listeInst.size() - 1)
            throw new FgException("ERROR: No next instruction");
        return nasm.listeInst.get(instIndex + 1);
    }

    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;

	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".fg";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	
	for(NasmInst nasmInst : nasm.listeInst){
	    Node n = this.inst2Node.get(nasmInst);
	    out.print(n + " : ( ");
	    for(NodeList q=n.succ(); q!=null; q=q.tail) {
		out.print(q.head.toString());
		out.print(" ");
	    }
	    out.println(")\t" + nasmInst);
	}
    }
    
    public Void visit(NasmAdd inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmCall inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        return null;
    }
    public Void visit(NasmDiv inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJe inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJle inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJne inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmMul inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmOr inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmCmp inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmInst inst){
        // Case NasmInt
        if (nasm.listeInst.indexOf(inst) != nasm.listeInst.size() - 1)
            addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJge inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJl inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmNot inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmPop inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmRet inst){
        if (nasm.listeInst.indexOf(inst) != nasm.listeInst.size() - 1)
            addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmXor inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmAnd inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJg inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmJmp inst){
        addEdgeWithLabel(inst, (NasmLabel) inst.address);
        return null;
    }
    public Void visit(NasmMov inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmPush inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmSub inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }
    public Void visit(NasmEmpty inst){
        addEdgeWithInst(inst, getNextInst(inst));
        return null;
    }

    public Void visit(NasmAddress operand){
        return null;
    }
    public Void visit(NasmConstant operand){return null;}
    public Void visit(NasmLabel operand){return null;}
    public Void visit(NasmRegister operand){return null;}



    static class FgException extends RuntimeException {
        FgException(String error) {
            super(error);
        }
    }
}
