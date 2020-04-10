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
    
    public FgSolution(Nasm nasm, Fg fg){
		this.nasm = nasm;
		this.fg = fg;
		this.use = new HashMap< NasmInst, IntSet>();
		this.def = new HashMap< NasmInst, IntSet>();
		this.in =  new HashMap< NasmInst, IntSet>();
		this.out = new HashMap< NasmInst, IntSet>();

		for (NasmInst inst : nasm.listeInst)
			initializeInst(inst);

		InOutAlgorithm();
    }

    private void addRegisterNumberToSet(NasmRegister op, IntSet set) {
    	if (op.isGeneralRegister()) {
			int value = ((NasmRegister) op).val;
			set.add(value);
		}
	}

	private void addAddressToSet(NasmAddress op, IntSet set) {
    	if (op.base instanceof NasmRegister)
    		addRegisterNumberToSet((NasmRegister) op.base, set);
    	if (op.offset instanceof NasmRegister)
    		addRegisterNumberToSet((NasmRegister) op.offset, set);
	}

	private void addOperandToSet(NasmOperand op, IntSet set) {
    	if (op instanceof NasmRegister)
    		addRegisterNumberToSet((NasmRegister) op, set);
    	else if (op instanceof NasmAddress)
    		addAddressToSet((NasmAddress) op, set);
    	// TODO: Throw exception here?
		else
			throw new Fg.FgException("ERROR: Operand is not valid (register or address");
	}

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

	private void InOutAlgorithm() {
		for (NasmInst key : fg.inst2Node.keySet()) {
			in.put(key, new IntSet(nasm.getTempCounter()));
			out.put(key, new IntSet(nasm.getTempCounter()));
		}

		// Needed for the do...while statement
    	IntSet inPrime = new IntSet(nasm.getTempCounter());
    	IntSet outPrime = new IntSet(nasm.getTempCounter());
    	IntSet newIn = new IntSet(nasm.getTempCounter());
    	IntSet newOut = new IntSet(nasm.getTempCounter());

    	do {
    		// TODO: Halt of the algorithm???
			// TODO: Check if the values/addresses are correctly manipulated
			for (Map.Entry<NasmInst, Node> entry : fg.inst2Node.entrySet()) {
				inPrime = in.get(entry.getKey()).copy();
				outPrime = out.get(entry.getKey()).copy();

				newIn = use.get(entry.getKey()).union(out.get(entry.getKey()).minus(def.get(entry.getKey())));
				in.put(entry.getKey(), newIn);

				NodeList succ = entry.getValue().succ();
				while (succ != null) {
					newOut = newOut.union(in.get(fg.node2Inst.get(succ.head)));
					succ = succ.tail;
				}
				out.put(entry.getKey(), newOut);
			}
		} while (!inPrime.equal(newIn) || !outPrime.equal(newOut));
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

    
