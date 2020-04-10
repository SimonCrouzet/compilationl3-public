import lcompil.analysis.DepthFirstAdapter;
import lcompil.node.*;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    /**
     * Computes the exploration of the node, and return the returnValue computed
     * @param sw the node to explore
     * @return the returnValue computed
     */
    private SaNode apply(Switchable sw) {
        sw.apply(this);
        return returnValue;
    }

    /**
     * Exploration of the node start
     */
    @Override
    public void caseStart(Start node) {
        apply(node.getPProgram());
    }

    /**
     * Exploration of the program (with global variables)
     */
    @Override
    public void caseADecVaretfctProgram(ADecVaretfctProgram node) {
        SaLDec variable = (SaLDec) apply(node.getOptdecvar());
        SaLDec fonction = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaProg(variable, fonction);
    }

    /**
     * Exploration of the program (without global variables)
     */
    @Override
    public void caseADecFctProgram(ADecFctProgram node) {
        SaLDec fonction = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaProg(null, fonction);
    }

    /**
     * Exploration of the global variables
     */
    @Override
    public void caseADecVariablesOptdecvar(ADecVariablesOptdecvar node) {
        returnValue = (SaLDec) apply(node.getListedecvar());
    }

    /**
     * Exploration of a list of variables declaration (several variables)
     */
    @Override
    public void caseADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node) {
        SaDec headVar = (SaDec) apply(node.getDecvar());
        SaLDec queueVar = (SaLDec) apply(node.getListedecvarChainee());
        returnValue = new SaLDec(headVar, queueVar);
    }

    /**
     * Exploration of a list of variables declaration (only one variable)
     */
    @Override
    public void caseADeclarerVariableListedecvar(ADeclarerVariableListedecvar node) {
        SaDec var = (SaDec) apply(node.getDecvar());
        returnValue = new SaLDec(var, null);
    }

    /**
     * Exploration of the list of variables declaration (more variables incoming)
     */
    @Override
    public void caseAMoreVariablesListedecvarChainee(AMoreVariablesListedecvarChainee node) {
        SaDec var = (SaDec) apply(node.getDecvar());
        SaLDec moreVar = (SaLDec) apply(node.getListedecvarChainee());
        returnValue = new SaLDec(var, moreVar);
    }

    /**
     * Exploration of the list of variables declaration (last variable)
     */
    @Override
    public void caseALastListedecvarChainee(ALastListedecvarChainee node) {
        SaDec lastVar = (SaDec) apply(node.getDecvar());
        returnValue = new SaLDec(lastVar, null);
    }

    /**
     * Exploration of a variable (simple)
     */
    @Override
    public void caseADecvarEntierDecvar(ADecvarEntierDecvar node) {
        String name = node.getIdentificator().getText();
        returnValue = new SaDecVar(name);
    }

    /**
     * Exploration of a variable (array)
     */
    @Override
    public void caseADecvarTableauDecvar(ADecvarTableauDecvar node) {
        // SaVarIndicee var = (SaVarIndicee) apply(node.get)
        String name = node.getIdentificator().getText();
        int length = Integer.parseInt(node.getNumber().getText());
        returnValue = new SaDecTab(name, length);
    }

    /**
     * Exploration of a list of functions declaration (more functions incoming)
     */
    @Override
    public void caseAListedecfoncRecListedecfonc(AListedecfoncRecListedecfonc node) {
        SaDecFonc headFct = (SaDecFonc) apply(node.getDecfonc());
        SaLDec queueFct = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaLDec(headFct, queueFct);
    }

    /**
     * Exploration of an empty list of functions declaration
     */
    @Override
    public void caseAListedecfoncFinalListedecfonc(AListedecfoncFinalListedecfonc node) {
        returnValue = null;
    }

    /**
     * Exploration of a function (with variables)
     */
    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node) {
        String name = node.getIdentificator().getText();
        SaLDec parameters = (SaLDec) apply(node.getListeparam());
        SaLDec variables = (SaLDec) apply(node.getOptdecvar());
        SaInstBloc body = (SaInstBloc) apply(node.getInstrbloc());

        returnValue = new SaDecFonc(name, parameters, variables, body);
    }

    /**
     * Exploration of a function (without variables)
     */
    @Override
    public void caseAInstrDecfonc(AInstrDecfonc node) {
        String name = node.getIdentificator().getText();
        SaLDec parameters = (SaLDec) apply(node.getListeparam());
        SaInstBloc body = (SaInstBloc) apply(node.getInstrbloc());

        returnValue = new SaDecFonc(name, parameters, null, body);
    }

    /**
     * Exploration of an empty list of parameters
     */
    @Override
    public void caseASansparamListeparam(ASansparamListeparam node) {
        returnValue = null;
    }

    /**
     * Exploration of a non-empty list of parameters
     */
    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node) {
        returnValue = (SaLDec) apply(node.getListedecvar());
    }

    /**
     * Exploration of an instruction (type: affect)
     */
    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node) {
        apply(node.getInstraffect());
    }

    /**
     * Exploration of a bloc of instructions
     */
    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node) {
        apply(node.getInstrbloc());
    }

    /**
     * Exploration of an instruction (type: if)
     */
    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node) {
        apply(node.getInstrif());
    }

    /**
     * Exploration of an instruction (type : while)
     */
    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node) {
        apply(node.getInstrwhile());
    }

    /**
     * Exploration of an instruction (type: recall)
     */
    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node) {
        apply(node.getInstrappel());
    }

    /**
     * Exploration of an instruction (type: return)
     */
    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node) {
        apply(node.getInstrreturn());
    }

    /**
     * Exploration of an instruction (type: write)
     */
    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node) {
        apply(node.getInstrwrite());
    }

    /**
     * Exploration of an empty instruction
     */
    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node) {
        returnValue = null;
    }

    /**
     * Exploration of an instruction (type: affect)
     */
    @Override
    public void caseAInstraffect(AInstraffect node) {
        SaVar var = (SaVar) apply(node.getVar());
        SaExp exp = (SaExp) apply(node.getExpr());
        returnValue = new SaInstAffect(var, exp);
    }

    /**
     * Exploration of the list of instructions contained in a bloc
     */
    @Override
    public void caseAInstrbloc(AInstrbloc node) {
        SaLInst bloc = (SaLInst) apply(node.getListeinst());
        returnValue = new SaInstBloc(bloc);
    }

    /**
     * Exploration of a list of instructions (more incoming)
     */
    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node) {
        SaInst head = (SaInst) apply(node.getInstr());
        SaLInst queue = (SaLInst) apply(node.getListeinst());
        returnValue = new SaLInst(head, queue);
    }

    /**
     * Exploration of an empty list of instructions
     */
    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node) {
        returnValue = null;
    }

    /**
     * Exploration of an if-elseif instruction
     */
    @Override
    public void caseAAvecsinonInstrif(AAvecsinonInstrif node) {
        SaExp test = (SaExp) apply(node.getExpr());
        SaInstBloc alors = (SaInstBloc) apply(node.getInstrbloc());
        SaInst sinon = (SaInst) apply(node.getInstrelseif());
        returnValue = new SaInstSi(test, alors, sinon);
    }

    /**
     * Exploration of an if instruction
     */
    @Override
    public void caseASanssinonInstrif(ASanssinonInstrif node) {
        SaExp test = (SaExp) apply(node.getExpr());
        SaInstBloc alors = (SaInstBloc) apply(node.getInstrbloc());
        returnValue = new SaInstSi(test, alors, null);
    }

    /**
     * Exploration of an elseif instruction
     */
    @Override
    public void caseAInstrelseif(AInstrelseif node) {
        returnValue = (SaInstBloc) apply(node.getInstrbloc());
    }

    /**
     * Exploration of a while instruction
     */
    @Override
    public void caseAInstrwhile(AInstrwhile node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaInstBloc inst = (SaInstBloc) apply(node.getInstrbloc());
        returnValue = new SaInstTantQue(exp, inst);
    }

    /**
     * Exploration of a call instruction
     */
    @Override
    public void caseAInstrappel(AInstrappel node) {
        apply(node.getAppelfct());
    }

    /**
     * Exploration of a return instruction
     */
    @Override
    public void caseAInstrreturn(AInstrreturn node) {
        SaExp val = (SaExp) apply(node.getExpr());
        returnValue = new SaInstRetour(val);
    }

    /**
     * Exploration of a write instruction
     */
    @Override
    public void caseAInstrwrite(AInstrwrite node) {
        SaExp arg = (SaExp) apply(node.getExpr());
        returnValue = new SaInstEcriture(arg);
    }

    /**
     * Exploration of a void instruction
     */
    @Override
    public void caseAInstrvoid(AInstrvoid node) {
        returnValue = null;
    }

    /**
     * Exploration of an expression with or
     */
    @Override
    public void caseAOuExpr(AOuExpr node) {
        SaExp op1 = (SaExp) apply(node.getExpr());
        SaExp op2 = (SaExp) apply(node.getExprOu());
        returnValue = new SaExpOr(op1, op2);
    }

    /**
     * Exploration of an expression without or
     */
    @Override
    public void caseASimpleExpr(ASimpleExpr node) {
        returnValue = (SaExp) apply(node.getExprOu());
    }

    /**
     * Exploration of an expression with and
     */
    @Override
    public void caseAEtExprOu(AEtExprOu node) {
        SaExp op1 = (SaExp) apply(node.getExprOu());
        SaExp op2 = (SaExp) apply(node.getExprEt());
        returnValue = new SaExpAnd(op1, op2);
    }

    /**
     * Exploration of an expression without and
     */
    @Override
    public void caseASimpleExprOu(ASimpleExprOu node) {
        returnValue = (SaExp) apply(node.getExprEt());
    }

    /**
     * Exploration of an expression with equals
     */
    @Override
    public void caseAEgalExprEt(AEgalExprEt node) {
        SaExp op1 = (SaExp) apply(node.getExprEt());
        SaExp op2 = (SaExp) apply(node.getExprEgal());
        returnValue = new SaExpEqual(op1, op2);
    }

    /**
     * Exploration of an expression with inf
     */
    @Override
    public void caseAInfExprEt(AInfExprEt node) {
        SaExp op1 = (SaExp) apply(node.getExprEt());
        SaExp op2 = (SaExp) apply(node.getExprEgal());
        returnValue = new SaExpInf(op1, op2);
    }

    /**
     * Exploration of an expression without equals/inf
     */
    @Override
    public void caseASimpleExprEt(ASimpleExprEt node) {
        returnValue = (SaExp) apply(node.getExprEgal());
    }

    /**
     * Exploration of an expression with add
     */
    @Override
    public void caseAPlusExprEgal(APlusExprEgal node) {
        SaExp op1 = (SaExp) apply(node.getExprEgal());
        SaExp op2 = (SaExp) apply(node.getExprPlus());
        returnValue = new SaExpAdd(op1, op2);
    }

    /**
     * Exploration of an expression with sub
     */
    @Override
    public void caseAMoinsExprEgal(AMoinsExprEgal node) {
        SaExp op1 = (SaExp) apply(node.getExprEgal());
        SaExp op2 = (SaExp) apply(node.getExprPlus());
        returnValue = new SaExpSub(op1, op2);
    }

    /**
     * Exploration of an expression without add/sub
     */
    @Override
    public void caseASimpleExprEgal(ASimpleExprEgal node) {
        returnValue = (SaExp) apply(node.getExprPlus());
    }

    /**
     * Exploration of an expression with mult
     */
    @Override
    public void caseAMultiplierExprPlus(AMultiplierExprPlus node) {
        SaExp op1 = (SaExp) apply(node.getExprPlus());
        SaExp op2 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpMult(op1, op2);
    }

    /**
     * Exploration of an expression with div
     */
    @Override
    public void caseADiviserExprPlus(ADiviserExprPlus node) {
        SaExp op1 = (SaExp) apply(node.getExprPlus());
        SaExp op2 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpDiv(op1, op2);
    }

    /**
     * Exploration of an expression without mult/div
     */
    @Override
    public void caseASimpleExprPlus(ASimpleExprPlus node) {
        returnValue = (SaExp) apply(node.getExprMult());
    }

    /**
     * Exploration of an expression with none
     */
    @Override
    public void caseANonExprMult(ANonExprMult node) {
        SaExp op1 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpNot(op1);
    }

    /**
     * Exploration of an expression without none
     */
    @Override
    public void caseASimpleExprMult(ASimpleExprMult node) {
        returnValue = (SaExp) apply(node.getExprComplete());
    }

    /**
     * Exploration of an integer expression
     */
    @Override
    public void caseANombreExprComplete(ANombreExprComplete node) {
        returnValue = new SaExpInt(Integer.parseInt(node.getNumber().getText()));
    }

    /**
     * Exploration of a complete expression
     */
    @Override
    public void caseAVarExprComplete(AVarExprComplete node) {
        SaVar var = (SaVar) apply(node.getVar());
        returnValue = new SaExpVar(var);
    }

    /**
     * Exploration of a read expression
     */
    @Override
    public void caseALireExprComplete(ALireExprComplete node) {
        returnValue = new SaExpLire();
    }

    /**
     * Exploration of an expression under parenthesis
     */
    @Override
    public void caseAParenthesesExprComplete(AParenthesesExprComplete node) {
        returnValue = (SaExp) apply(node.getExpr());
    }

    /**
     * Exploration of a function call expression
     */
    @Override
    public void caseAAppelfctExprComplete(AAppelfctExprComplete node) {
        // returnValue = (SaAppel) apply(node.getAppelfct());
        SaAppel call = (SaAppel) apply(node.getAppelfct());
        returnValue = new SaExpAppel(call);
    }

    /**
     * Exploration of an indiced variable
     */
    @Override
    public void caseAVartabVar(AVartabVar node) {
        SaExp indice = (SaExp) apply(node.getExpr());
        returnValue = new SaVarIndicee(node.getIdentificator().getText(), indice);
    }

    /**
     * Exploration of a simple variable
     */
    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node) {
        returnValue = new SaVarSimple(node.getIdentificator().getText());
    }

    /**
     * Exploration of a list of expressions (more incoming)
     */
    @Override
    public void caseAElementsmultiplesListexpr(AElementsmultiplesListexpr node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp lExp = (SaLExp) apply(node.getListexprChainee());
        returnValue = new SaLExp(exp, lExp);
    }

    /**
     * Exploration of an empty list of expressions
     */
    @Override
    public void caseAVideListexpr(AVideListexpr node) {
        returnValue = null;
    }

    /**
     * Exploration of a chained list of expressions (more incoming)
     */
    @Override
    public void caseAElementsmultiplesListexprChainee(AElementsmultiplesListexprChainee node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp lExp = (SaLExp) apply(node.getListexprChainee());
        returnValue = new SaLExp(exp, lExp);
    }

    /**
     * Exploration of an empty chained list of expressions
     */
    @Override
    public void caseAVideListexprChainee(AVideListexprChainee node) {
        returnValue = null;
    }

    /**
     * Exploration of a function call, with params
     */
    @Override
    public void caseAAvecparamAppelfct(AAvecparamAppelfct node) {
        String name = node.getIdentificator().getText();
        SaLExp params = (SaLExp) apply(node.getListexpr());
        returnValue = new SaAppel(name, params);
    }


    /**
     * Getter for the root of the abstract tree
     */
    public SaNode getRoot() {
        return returnValue;
    }
}
