import c3a.*;
import sa.*;
import ts.TsItemFct;
import ts.Ts;

public class Sa2c3a extends SaDepthFirstVisitor<C3aOperand> {
    private C3a c3a;

    /**
     * Constructor of the 3-addresses code
     * @param root root of the abstract tree
     */
    public Sa2c3a(SaNode root) {
        this.c3a = new C3a();
        root.accept(this);
    }

    /**
     * Exploration of the program
     */
    @Override
    public C3aOperand visit(SaProg node) {
        return super.visit(node);
    }

    /**
     * Exploration of an array declaration
     */
    @Override
    public C3aOperand visit(SaDecTab node) {
        return null;
    }

    /**
     * Exploration of an expression
     */
    @Override
    public C3aOperand visit(SaExp node) {
        return null;
    }

    /**
     * Exploration of an integer expression
     */
    @Override
    public C3aOperand visit(SaExpInt node) {
        return new C3aConstant(node.getVal());
    }

    /**
     * Exploration of a variable expression
     */
    @Override
    public C3aOperand visit(SaExpVar node) {
        return node.getVar().accept(this);
    }

    /**
     * Exploration of a write instruction
     */
    @Override
    public C3aOperand visit(SaInstEcriture node) {
        C3aOperand arg = node.getArg().accept(this);
        c3a.ajouteInst(new C3aInstWrite(arg, ""));
        return null;
    }

    /**
     * Exploration of a while instruction
     */
    @Override
    public C3aOperand visit(SaInstTantQue node) {
        C3aLabel testLabel = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        c3a.addLabelToNextInst(testLabel);
        C3aOperand test = node.getTest().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, suite, "end while"));
        node.getFaire().accept(this);
        c3a.ajouteInst(new C3aInstJump(testLabel, "do while"));
        c3a.addLabelToNextInst(suite);
        return null;
    }

    /**
     * Exploration of a list of instructions
     */
    @Override
    public C3aOperand visit(SaLInst node) {
        return super.visit(node);
    }

    /**
     * Exploration of a function declaration
     */
    @Override
    public C3aOperand visit(SaDecFonc node) {
        c3a.ajouteInst(new C3aInstFBegin(node.tsItem, "fct begin"));
        if (node.getParametres() != null) node.getParametres().accept(this);
        if (node.getVariable() != null) node.getVariable().accept(this);
        node.getCorps().accept(this);
        c3a.ajouteInst(new C3aInstFEnd("fct end"));
        return null;
    }

    /**
     * Exploration of a variable declaration
     */
    @Override
    public C3aOperand visit(SaDecVar node) {
        return super.visit(node);
    }

    /**
     * Exploration of an affect instruction
     * @return the operand computed
     */
    @Override
    public C3aOperand visit(SaInstAffect node) {
        C3aOperand left = node.getLhs().accept(this);
        C3aOperand right = node.getRhs().accept(this);
        c3a.ajouteInst(new C3aInstAffect(right, left, ""));
        return right;
    }

    /**
     * Exploration of a list of declarations
     */
    @Override
    public C3aOperand visit(SaLDec node) {
        node.getTete().accept(this);
        if (node.getQueue() != null) node.getQueue().accept(this);
        return null;
    }

    /**
     * Exploration of a variable
     * @return the variable
     */
    @Override
    public C3aOperand visit(SaVarSimple node) {
        return new C3aVar(node.tsItem, null);
    }

    /**
     * Exploration of a call
     * @return the function called
     */
    @Override
    public C3aOperand visit(SaAppel node) {
        C3aFunction function = new C3aFunction(node.tsItem);
        if (node.getArguments() != null) node.getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(function, null, ""));
        return function;
    }

    /**
     * Exploration of a call expression
     * @return the temp which contains the data returned by the call
     */
    @Override
    public C3aOperand visit(SaExpAppel node) {
        C3aTemp temp = c3a.newTemp();

        C3aFunction function = new C3aFunction(node.getVal().tsItem);
        if (node.getVal().getArguments() != null) node.getVal().getArguments().accept(this);
        c3a.ajouteInst(new C3aInstCall(function, temp, ""));

        return temp;
    }

    /**
     * Exploration of an add expression
     * @return the result of the addition
     */
    @Override
    public C3aOperand visit(SaExpAdd node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstAdd(op1, op2, result, ""));
        return result;
    }

    /**
     * Exploration of a sub expression
     * @return the result of the subtraction
     */
    @Override
    public C3aOperand visit(SaExpSub node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstSub(op1, op2, result, ""));
        return result;
    }

    /**
     * Exploration of a mult expression
     * @return the result of the multiplication
     */
    @Override
    public C3aOperand visit(SaExpMult node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstMult(op1, op2, result, ""));
        return result;
    }

    /**
     * Exploration of a div expression
     * @return the result of the division
     */
    @Override
    public C3aOperand visit(SaExpDiv node) {
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aTemp result = c3a.newTemp();
        c3a.ajouteInst(new C3aInstDiv(op1, op2, result, ""));
        return result;
    }

    /**
     * Exploration of an inf expression
     * @return the result of the comparaison 'inf'
     */
    @Override
    public C3aOperand visit(SaExpInf node) {
        C3aTemp test = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aLabel testLabel = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect(c3a.True, test, ""));
        c3a.ajouteInst(new C3aInstJumpIfLess(op1, op2, testLabel, ""));
        c3a.ajouteInst(new C3aInstAffect( c3a.False, test, ""));
        c3a.addLabelToNextInst(testLabel);
        return test;
    }

    /**
     * Exploration of an equals expression
     * @return the result of the comparaison 'equals'
     */
    @Override
    public C3aOperand visit(SaExpEqual node) {
        C3aTemp test = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aLabel testLabel = c3a.newAutoLabel();
        c3a.ajouteInst(new C3aInstAffect(c3a.True, test, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, op2, testLabel, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, test, ""));
        c3a.addLabelToNextInst(testLabel);
        return test;
    }

    /**
     * Exploration of an and expression
     * @return the result of the comparaison 'and'
     */
    @Override
    public C3aOperand visit(SaExpAnd node) {
        C3aLabel trueLabel = c3a.newAutoLabel();
        C3aLabel falseLabel = c3a.newAutoLabel();
        C3aTemp temp = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfEqual(op1, c3a.False, falseLabel, ""));
        c3a.ajouteInst(new C3aInstJumpIfEqual(op2, c3a.False, falseLabel, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.True, temp,""));
        c3a.ajouteInst(new C3aInstJump(trueLabel, ""));
        c3a.addLabelToNextInst(falseLabel);
        c3a.ajouteInst(new C3aInstAffect(c3a.False, temp, ""));
        c3a.addLabelToNextInst(trueLabel);
        return temp;
    }

    /**
     * Exploration of an or expression
     * @return the result of the comparaison 'or'
     */
    @Override
    public C3aOperand visit(SaExpOr node) {
        C3aLabel falseLabel = c3a.newAutoLabel();
        C3aLabel trueLabel = c3a.newAutoLabel();
        C3aTemp temp = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, c3a.False, trueLabel, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op2, c3a.False, trueLabel, ""));
        c3a.ajouteInst(new C3aInstAffect(c3a.False, temp,""));
        c3a.ajouteInst(new C3aInstJump(falseLabel, ""));
        c3a.addLabelToNextInst(trueLabel);
        c3a.ajouteInst(new C3aInstAffect(c3a.True, temp,""));
        c3a.addLabelToNextInst(falseLabel);
        return temp;
    }

    /**
     * Exploration of a none expression
     * @return the result of the comparaison 'not'
     */
    @Override
    public C3aOperand visit(SaExpNot node) {
        C3aLabel label = c3a.newAutoLabel();
        C3aTemp temp = c3a.newTemp();
        C3aOperand op1 = node.getOp1().accept(this);
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.True, ""));
        c3a.ajouteInst(new C3aInstJumpIfNotEqual(op1, c3a.True, label, ""));
        c3a.ajouteInst(new C3aInstAffect(temp, c3a.False, ""));
        c3a.addLabelToNextInst(label);
        return temp;
    }

    /**
     * Exploration of a read expression
     * @return the temp where the reading data are
     */
    @Override
    public C3aOperand visit(SaExpLire node) {
        C3aTemp temp = c3a.newTemp();
        c3a.ajouteInst(new C3aInstRead(temp, ""));
        return temp;
    }

    /**
     * Exploration of a bloc of instructions
     */
    @Override
    public C3aOperand visit(SaInstBloc node) {
        return super.visit(node);
    }

    /**
     * Exploration of an if instruction
     */
    @Override
    public C3aOperand visit(SaInstSi node) {
        C3aLabel elseLabel = c3a.newAutoLabel();
        C3aLabel suite = c3a.newAutoLabel();
        C3aOperand test = node.getTest().accept(this);
        if (node.getSinon() != null) { // Case with elseif
            c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, elseLabel, "if"));
            node.getAlors().accept(this);
            c3a.ajouteInst(new C3aInstJump(suite, "else if"));
            c3a.addLabelToNextInst(elseLabel);
            node.getSinon().accept(this);
        }
        else { // Case without elseif
            c3a.ajouteInst(new C3aInstJumpIfEqual(test, c3a.False, suite, "if"));
            node.getAlors().accept(this);
        }

        c3a.addLabelToNextInst(suite);
        return null;
    }

    /**
     * Exploration of a return instruction
     */
    @Override
    public C3aOperand visit(SaInstRetour node) {
        C3aOperand op1 = node.getVal().accept(this);
        c3a.ajouteInst(new C3aInstReturn(op1, ""));
        return op1;
    }

    /**
     * Exploration of a list of expressions
     */
    @Override
    public C3aOperand visit(SaLExp node) {
        C3aOperand op1 = node.getTete().accept(this);
        c3a.ajouteInst(new C3aInstParam(op1, ""));
        if (node.getQueue() != null) node.getQueue().accept(this);
        return null;
    }

    /**
     * Exploration of an indiced variable
     */
    @Override
    public C3aOperand visit(SaVarIndicee node) {
        return new C3aVar(node.tsItem, node.getIndice().accept(this));
    }

    /**
     * Getter
     */
    public C3a getC3a() {
        return c3a;
    }
}
