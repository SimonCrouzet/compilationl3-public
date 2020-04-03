import c3a.*;
import sa.*;
import ts.TsItemFct;
import ts.Ts;

public class Sa2c3a extends SaDepthFirstVisitor<C3aOperand> {
    private C3a c3a;
    private Ts tableGlobale;
    private Ts tableLocaleCourante;

    public Sa2c3a(SaNode root, Ts tableGlobale) {
        this.c3a = new C3a();
        this.tableGlobale = tableGlobale;
        this.tableLocaleCourante = null;
        root.accept(this);
    }

    @Override
    public C3aOperand visit(SaProg node) {
        node.getFonctions().accept(this);
        node.getVariables().accept(this);
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaDecTab node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExp node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpInt node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpVar node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstEcriture node) {
        C3aOperand arg = node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(arg, "write"));
        return arg; // TODO: Check
    }

    @Override
    public C3aOperand visit(SaInstTantQue node) {
        C3aLabel testLabel = c3a.newAutoLabel();
        c3a.addLabelToNextInst(testLabel);
        C3aOperand test = node.getTest().accept(this);
        C3aLabel suite = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, suite, "while"));
        node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(testLabel, "while"));
        c3a.addLabelToNextInst(suite);
        return test; // TODO: Check
    }

    @Override
    public C3aOperand visit(SaLInst node) {
        node.getTete().accept(this);
        node.getQueue().accept(this);
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaDecFonc node) {
        /*c3a.ajouteInst(new C3aInstFBegin(node.tsItem, node.getNom()));
        // suite ?
        C3aFunction fct = new C3aFunction(node.tsItem);
        node.getCorps().accept(this);
        // suite ?
        c3a.ajouteInst(new C3aInstFEnd(node.getNom()));*/
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaDecVar node) {
        C3aVar var = (C3aVar) super.visit(node);
        // TODO: Finish this
        c3a.ajouteInst(new C3aInstAffect());
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstAffect node) {
        C3aOperand op1 = node.getLhs().accept(this);
        C3aOperand result = node.getRhs().accept(this);
        c3a.ajouteInst(new C3aInstAffect(op1, result, "affect"));
        return op1;
    }

    @Override
    public C3aOperand visit(SaLDec node) {
        node.getTete().accept(this);
        node.getQueue().accept(this);
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    @Override
    public C3aOperand visit(SaAppel node) {
        C3aFunction function = new C3aFunction(node.tsItem);
        // TODO: Comment récupérer la fonction déjà créée ?
        C3aOperand result = super.visit(node);
        c3a.ajouteInst(new C3aInstCall(function, result, "call"));
        return result;
    }

    @Override
    public C3aOperand visit(SaExpAppel node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAdd(op1, op2, result, "add"));
        return result;
    }

    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstSub(op1, op2, result, "sub"));
        return result;
    }

    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstMult(op1, op2, result, "mult"));
        return result;
    }

    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstDiv(op1, op2, result, "div"));
        return result;
    }

    @Override
    public C3aOperand visit(SaExpInf node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp test = c3a.newTemp();
        C3aLabel testLabel = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, testLabel, "inf"));
        node.accept(this);
        c3a.addLabelToNextInst(testLabel);
        return test;
    }

    @Override
    public C3aOperand visit(SaExpEqual node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp test = c3a.newTemp();
        C3aLabel testLabel = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, testLabel, "equal"));
        node.accept(this);
        c3a.addLabelToNextInst(testLabel);
        return test;
    }

    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel label1 = c3a.newAutoLabel();
        C3aLabel label2 = c3a.newAutoLabel();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.False, label1, "and"));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.False, label1, "and"));
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.True, "and"));
        c3a.ajouteInst(new C3aInstJump(label2, "and"));
        c3a.addLabelToNextInst(label1);
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.False, "and"));
        c3a.addLabelToNextInst(label2);
        return temp;
    }

    @Override
    public C3aOperand visit(SaExpOr node) {
        C3aLabel label1 = c3a.newAutoLabel();
        C3aLabel label2 = c3a.newAutoLabel();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.True, label1, "or"));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.True, label1, "or"));
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.False, "or"));
        c3a.ajouteInst(new C3aInstJump(label2, "or"));
        c3a.addLabelToNextInst(label1);
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.True, "or"));
        c3a.addLabelToNextInst(label2);
        return temp;
    }

    @Override
    public C3aOperand visit(SaExpNot node) {
        C3aLabel label1 = c3a.newAutoLabel();
        C3aLabel label2 = c3a.newAutoLabel();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aTemp temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, c3a.True, label1, "not"));
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.False, "not"));
        c3a.ajouteInst(new C3aInstJump(label2, "not"));
        c3a.addLabelToNextInst(label1);
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.True, "not"));
        c3a.addLabelToNextInst(label2);
        return temp;
    }

    @Override
    public C3aOperand visit(SaExpLire node) {
        C3aTemp temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, "read"));
        return temp;
    }

    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstSi node) {
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaInstRetour node) {
        C3aOperand op1 = node.getVal().accept(this);
        c3a.ajouteInst(new C3aInstReturn(op1, "return"));
        return op1;
    }

    @Override
    public C3aOperand visit(SaLExp node) {
        node.getTete().accept(this);
        node.getQueue().accept(this);
        return super.visit(node);
    }

    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return new C3aVar(node.tsItem, node.getIndice().accept(this));
    }


    public C3a getC3a() {
        return c3a;
    }
}
