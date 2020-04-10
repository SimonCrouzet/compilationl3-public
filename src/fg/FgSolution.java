package fg;
import util.graph.*;
import nasm.*;
import util.intset.*;
import java.io.*;
import java.util.*;

public class FgSolution{
    int iterNum = 0;
    public Nasm nasm;
    Fg fg;
    public Map< NasmInst, IntSet> use;
    public Map< NasmInst, IntSet> def;
    public Map< NasmInst, IntSet> in;
    public Map< NasmInst, IntSet> out;

	/**
	 * Constructor of the analytic graph solver
	 * @param nasm the NASM code to analyze
	 * @param fg the analytic graph to solve
	 */
    public FgSolution(Nasm nasm, Fg fg){
		this.nasm = nasm;
		this.fg = fg;
		this.use = new HashMap< NasmInst, IntSet>();
		this.def = new HashMap< NasmInst, IntSet>();
		this.in =  new HashMap< NasmInst, IntSet>();
		this.out = new HashMap< NasmInst, IntSet>();

		// Compute Use & Def
		for (NasmInst inst : nasm.listeInst)
			initializeInst(inst);

		// Compute In & Out
		InOutAlgorithm();
    }

	/**
	 * Add a Register to an IntSet
	 */
	private void addRegisterNumberToSet(NasmRegister op, IntSet set) {
    	if (op.isGeneralRegister()) {
			int value = ((NasmRegister) op).val;
			set.add(value);
		}
	}

	/**
	 * Add an Address to an IntSet
	 */
	private void addAddressToSet(NasmAddress op, IntSet set) {
    	if (op.base instanceof NasmRegister)
    		addRegisterNumberToSet((NasmRegister) op.base, set);
    	if (op.offset instanceof NasmRegister)
    		addRegisterNumberToSet((NasmRegister) op.offset, set);
	}

	/**
	 * Add an Operand to an IntSet
	 */
	private void addOperandToSet(NasmOperand op, IntSet set) {
    	if (op instanceof NasmRegister)
    		addRegisterNumberToSet((NasmRegister) op, set);
    	if (op instanceof NasmAddress)
    		addAddressToSet((NasmAddress) op, set);
	}

	/**
	 * Compute Use & Def from the instruction provided, using booleans provided by the inst
	 */
    private void initializeInst(NasmInst inst) {
		in.put(inst, new IntSet(nasm.getTempCounter()));
		out.put(inst, new IntSet(nasm.getTempCounter()));
		def.put(inst, new IntSet(nasm.getTempCounter()));
		use.put(inst, new IntSet(nasm.getTempCounter()));

		 if (inst.srcDef)
			addOperandToSet(inst.source, use.get(inst));
		if (inst.srcUse)
			addOperandToSet(inst.source, use.get(inst));
		if (inst.destDef)
			addOperandToSet(inst.destination, def.get(inst));
		if (inst.destUse)
			addOperandToSet(inst.destination, use.get(inst));
	}

	/**
	 * Compute In & Out algorithm according to the pseudocode
	 */
	private void InOutAlgorithm() {
		for (NasmInst key : fg.inst2Node.keySet()) {
			in.put(key, new IntSet(nasm.getTempCounter()));
			out.put(key, new IntSet(nasm.getTempCounter()));
		}

    	IntSet inPrime;
    	IntSet outPrime;
    	IntSet newIn;
    	IntSet newOut;
		// Needed for the do...while statement
		boolean inOutDiffers = false;

    	do {
			// TODO: Check if the values/addresses are correctly manipulated
			++iterNum;
			inOutDiffers = false;
			for (NasmInst inst : nasm.listeInst) {
				// Store in' and out'
				inPrime = in.get(inst).copy();
				outPrime = out.get(inst).copy();

				// Compute new In
				newIn = use.get(inst).union(out.get(inst).minus(def.get(inst)));
				in.put(inst, newIn);


				// Compute new Out
				newOut = new IntSet(nasm.getTempCounter());
				NodeList succ = fg.inst2Node.get(inst).succ();
				while (succ != null) {
					newOut = newOut.union(in.get(fg.node2Inst.get(succ.head)));
					succ = succ.tail;
				}
				out.put(inst, newOut);

				// Halt condition
				if (!inPrime.equal(newIn) || !outPrime.equal(newOut))
					inOutDiffers = true;
			}
		} while (inOutDiffers);
	}
    
    public void affiche(String baseFileName){
	String fileName;
	PrintStream out = System.out;
	
	if (baseFileName != null){
	    try {
		baseFileName = baseFileName;
		fileName = baseFileName + ".fgs";
		out = new PrintStream(fileName);
	    }
	    
	    catch (IOException e) {
		System.err.println("Error: " + e.getMessage());
	    }
	}
	
	out.println("iter num = " + iterNum);
	for(NasmInst nasmInst : this.nasm.listeInst){
	    out.println("use = "+ this.use.get(nasmInst) + " def = "+ this.def.get(nasmInst) + "\tin = " + this.in.get(nasmInst) + "\t \tout = " + this.out.get(nasmInst) + "\t \t" + nasmInst);
	}
    }
}

    
