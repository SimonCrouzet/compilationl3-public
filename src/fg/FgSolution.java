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
    	if (op instanceof NasmAddress)
    		addAddressToSet((NasmAddress) op, set);
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
    	IntSet inPrime;
    	IntSet outPrime;
    	IntSet newIn;
    	IntSet newOut;
		boolean inOutDiffers = false;

    	do {
    		// TODO: Halt of the algorithm???
			// TODO: Check if the values/addresses are correctly manipulated
			++iterNum;
			inOutDiffers = false;
			// for (Map.Entry<NasmInst, Node> entry : fg.inst2Node.entrySet()) {
			for (NasmInst inst : nasm.listeInst) {
				inPrime = in.get(inst).copy();
				outPrime = out.get(inst).copy();

				System.out.println("Key " + inst.toString());
				System.out.println("Use " + use.get(inst));
				System.out.println("Def " + def.get(inst));

				newIn = use.get(inst).union(out.get(inst).minus(def.get(inst)));
				in.put(inst, newIn);

				newOut = new IntSet(nasm.getTempCounter());
				NodeList succ = fg.inst2Node.get(inst).succ();
				while (succ != null) {
					System.out.println("Node " + succ.head);
					newOut = newOut.union(in.get(fg.node2Inst.get(succ.head)));
					succ = succ.tail;
				}
				out.put(inst, newOut);

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

    
