import c3a.*;
import nasm.*;
import ts.Ts;
import ts.TsItemFct;

public class C3a2nasm implements C3aVisitor<NasmOperand> {
    private NasmRegister ebp;
    private NasmRegister esp;
    private C3a c3a;
    private Nasm nasm;
    private Ts globalTable;
    private Ts localTable;
    private TsItemFct currentFct;

    public C3a2nasm(C3a c3a, Ts globalTable) {
        this.c3a = c3a;
        this.globalTable = globalTable;

        this.nasm = new Nasm(globalTable);
        ebp = new NasmRegister(Nasm.REG_EBP);
        esp = new NasmRegister(Nasm.REG_ESP);
        ebp.colorRegister(Nasm.REG_EBP);
        esp.colorRegister(Nasm.REG_ESP);
        nasm.setTempCounter(c3a.getTempCounter());


        nasm.ajouteInst(new NasmCall(null, new NasmLabel("main"), ""));
        // NasmRegister eax = new NasmRegister(Nasm.REG_EAX);
        // NasmRegister ebx = new NasmRegister(Nasm.REG_EBX);
        NasmRegister eax = nasm.newRegister();
        NasmRegister ebx = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        ebx.colorRegister(Nasm.REG_EBX);
        nasm.ajouteInst(new NasmMov(null, ebx, new NasmConstant(0), "valeur de retour du programme"));
        nasm.ajouteInst(new NasmMov(null, eax, new NasmConstant(1), ""));

        nasm.ajouteInst(new NasmInt(null, ""));


        for (C3aInst inst : c3a.listeInst)
            inst.accept(this);
    }

    @Override
    public NasmOperand visit(C3aInstAdd inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(getLabel(inst), result, op1, ""));
        nasm.ajouteInst(new NasmAdd(null, result, op2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstCall inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand result = inst.result.accept(this);
        Ts table = inst.op1.getValue().getTable();

        nasm.ajouteInst(new NasmSub(getLabel(inst), esp, new NasmConstant(4), "Call"));
        nasm.ajouteInst(new NasmCall(null, op1, ""));
        nasm.ajouteInst(new NasmPop(null, result, ""));

        if (table.nbArg() > 0)
            nasm.ajouteInst(new NasmAdd(null, esp, new NasmConstant(table.nbArg() * 4), ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFBegin inst) {
        localTable = inst.val.getTable();
        NasmLabel fctLabel = new NasmLabel(inst.val.getIdentif());
        nasm.ajouteInst(new NasmPush(fctLabel, ebp, "sauvegarde la valeur de ebp"));
        nasm.ajouteInst(new NasmMov(null, ebp, esp, "nouvelle valeur de ebp"));
        nasm.ajouteInst(new NasmSub(null, esp, new NasmConstant(localTable.nbVar() * 4), "allocation des variables locales"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInst inst) {
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfLess inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);
        nasm.ajouteInst(new NasmCmp(getLabel(inst), op1, op2, "JumpIfLess"));
        nasm.ajouteInst(new NasmJl(null, result, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstMult inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(getLabel(inst), result, op1, ""));
        nasm.ajouteInst(new NasmMul(null, result, op2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstRead inst) {
        NasmLabel read = new NasmLabel("readline");
        NasmLabel atoi = new NasmLabel("atoi");
        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);
        NasmOperand result = inst.result.accept(this);

        // TODO: Check NasmConstant
        nasm.ajouteInst(new NasmMov(getLabel(inst), eax, new NasmConstant(3), "Read"));
        nasm.ajouteInst(new NasmCall(null, read, ""));
        nasm.ajouteInst(new NasmCall(null, atoi, ""));
        nasm.ajouteInst(new NasmMov(null, result, eax, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aInstSub inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        nasm.ajouteInst(new NasmMov(getLabel(inst), result, op1, ""));
        nasm.ajouteInst(new NasmSub(null, result, op2, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstAffect inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand result = inst.result.accept(this);

        // TODO: Check if we have to avoid any mov between two adresses
        if (result instanceof NasmAddress && op1 instanceof NasmAddress) {
            // throw new IllegalStateException("InstAffect should concern a valid register!");
            NasmRegister register = nasm.newRegister();
            nasm.ajouteInst(new NasmMov(getLabel(inst), register, op1, "Affect"));
            nasm.ajouteInst(new NasmMov(null, result, register, ""));
        }
        else
            nasm.ajouteInst(new NasmMov(getLabel(inst), result, op1, "Affect"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstDiv inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);

        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);

        nasm.ajouteInst(new NasmMov(getLabel(inst), eax, op1, ""));

        // TODO: Re-check this
        NasmRegister register;
        if ( !(op2 instanceof NasmRegister)) {
            register = nasm.newRegister(); // TODO: Check if we have to color this one
            nasm.ajouteInst(new NasmMov(getLabel(inst), register, op2, ""));
            nasm.ajouteInst(new NasmDiv(null, register, ""));
        }
        else
            nasm.ajouteInst(new NasmDiv(getLabel(inst), op2, ""));

        nasm.ajouteInst(new NasmMov(null, result, eax, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstFEnd inst) {
        nasm.ajouteInst(new NasmAdd(getLabel(inst), esp, new NasmConstant(localTable.nbVar() * 4), "désallocation des" +
                " variables locales"));
        nasm.ajouteInst(new NasmPop(null, ebp, "restaure la valeur de ebp"));
        nasm.ajouteInst(new NasmRet(null, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfEqual inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);
        nasm.ajouteInst(new NasmCmp(getLabel(inst), op1, op2, "JumpIfEqual"));
        nasm.ajouteInst(new NasmJe(null, result, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJumpIfNotEqual inst) {
        NasmOperand op1 = inst.op1.accept(this);
        NasmOperand op2 = inst.op2.accept(this);
        NasmOperand result = inst.result.accept(this);
        nasm.ajouteInst(new NasmCmp(getLabel(inst), op1, op2, "JumpIfNotEqual"));
        nasm.ajouteInst(new NasmJne(null, result, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstJump inst) {
        NasmOperand result = inst.result.accept(this);
        nasm.ajouteInst(new NasmJmp(getLabel(inst), result, ""));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstParam inst) {
        NasmOperand param = inst.op1.accept(this);
        nasm.ajouteInst(new NasmPush(getLabel(inst), param, "Param"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstReturn inst) {
        NasmOperand ret = inst.op1.accept(this);
        NasmAddress address = new NasmAddress(ebp, '+', new NasmConstant(2));
        nasm.ajouteInst(new NasmMov(getLabel(inst), address, ret, "Return"));
        return null;
    }

    @Override
    public NasmOperand visit(C3aInstWrite inst) {
        NasmOperand op1 = inst.op1.accept(this);

        NasmRegister eax = nasm.newRegister();
        eax.colorRegister(Nasm.REG_EAX);

        nasm.ajouteInst(new NasmMov(getLabel(inst), eax, op1, "Write"));
        NasmLabel print = new NasmLabel("iprintLF");
        nasm.ajouteInst(new NasmCall(null, print, ""));

        return null;
    }

    @Override
    public NasmOperand visit(C3aConstant oper) {
        return new NasmConstant(oper.val);
    }

    @Override
    public NasmOperand visit(C3aLabel oper) {
        return new NasmLabel(oper.toString());
    }

    @Override
    public NasmOperand visit(C3aTemp oper) {
        return new NasmRegister(oper.num);
    }

    @Override
    public NasmOperand visit(C3aVar oper) {
        NasmOperand base;
        char direction;

        if (oper.item.portee.equals(globalTable)) { // TODO: Check if equals() is well defined
            base = new NasmLabel(oper.item.getIdentif());
            if (oper.item.getTaille() == 1)
                return new NasmAddress(base);
            direction = '+';
        }
        else {
            base = ebp;
            if (oper.item.isParam)
                direction = '+';
            else
                direction = '-';
        }
        NasmConstant offset;
        if (oper.item.getTaille() > 1)
            offset = new NasmConstant(((C3aConstant) oper.index).val);
        else if (oper.item.isParam)
            offset = new NasmConstant(2 + (oper.item.portee.nbArg() - oper.item.getAdresse()));
        else
            offset = new NasmConstant(1 + oper.item.getAdresse());

        return new NasmAddress(base, direction, offset);
    }

    @Override
    public NasmOperand visit(C3aFunction oper) {
        return new NasmLabel(oper.toString());
    }

    public Nasm getNasm() {
        return nasm;
    }
    private NasmOperand getLabel(C3aInst inst) {
        if (inst.label != null)
            return inst.label.accept(this);
        else
            return null;
    }
}
