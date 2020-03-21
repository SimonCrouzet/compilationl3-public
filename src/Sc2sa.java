import lcompil.analysis.DepthFirstAdapter;
import lcompil.node.*;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    private SaNode apply(Switchable sw) {
        sw.apply(this);
        return returnValue;
    }

    // TODO : Check
    @Override
    public void caseStart(Start node) {
        node.getPProgram().apply(this);
        // node.getEOF().apply(this);
    }

    @Override
    public void caseADecVaretfctProgram(ADecVaretfctProgram node) {
        SaLDec variable = (SaLDec) apply(node.getOptdecvar());
        SaLDec fonction = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaProg(variable, fonction);
    }

    @Override
    public void caseADecFctProgram(ADecFctProgram node) {
        SaLDec fonction = (SaLDec) apply(node.getListedecfonc());
        SaLDec variable = null;
        returnValue = new SaProg(variable, fonction);
    }


    @Override
    public void caseADecVariablesOptdecvar(ADecVariablesOptdecvar node) {
        SaLDec decVar = (SaLDec) apply(node.getListedecvar());
        returnValue = new SaLDec(null, decVar);
    }

    @Override
    public void caseADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node) {
        SaDec headVar = (SaDec) apply(node.getDecvar());
        SaLDec queueVar = (SaLDec) apply(node.getListedecvarChainee());
        returnValue = new SaLDec(headVar, queueVar);
    }

    @Override
    public void caseADeclarerVariableListedecvar(ADeclarerVariableListedecvar node) {
        SaDec var = (SaDec) apply(node.getDecvar());
        returnValue = new SaLDec(var, null);
    }

    @Override
    public void caseAMoreVariablesListedecvarChainee(AMoreVariablesListedecvarChainee node) {
        SaDec var = (SaDec) apply(node.getDecvar());
        SaLDec moreVar = (SaLDec) apply(node.getListedecvarChainee());
        returnValue = new SaLDec(var, moreVar);
    }

    @Override
    public void caseALastListedecvarChainee(ALastListedecvarChainee node) {
        SaDec lastVar = (SaDec) apply(node.getDecvar());
        returnValue = new SaLDec(lastVar, null);
    }

    @Override
    public void caseADecvarEntierDecvar(ADecvarEntierDecvar node) {
        returnValue = new SaDecVar(node.getIdentificator().getText());
    }

    // TODO : Check
    @Override
    public void caseADecvarTableauDecvar(ADecvarTableauDecvar node) {
        returnValue = new SaDecTab(node.getIdentificator().getText(), node.getInt().getLine());
    }

    @Override
    public void caseAListedecfoncRecListedecfonc(AListedecfoncRecListedecfonc node) {
        SaDec headFct = (SaDec) apply(node.getDecfonc());
        SaLDec queueFct = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaLDec(headFct, queueFct);
    }

    @Override
    public void caseAListedecfoncFinalListedecfonc(AListedecfoncFinalListedecfonc node) {
        returnValue = new SaLDec(null, null);
    }

    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node) {
        String name = node.getIdentificator().getText();
        SaLDec parameters = (SaLDec) apply(node.getListeparam());
        SaLDec variables = (SaLDec) apply(node.getOptdecvar());
        SaInst body = (SaInst) apply(node.getInstrbloc());

        returnValue = new SaDecFonc(name, parameters, variables, body);
    }

    @Override
    public void caseAInstrDecfonc(AInstrDecfonc node) {
        String name = node.getIdentificator().getText();
        SaLDec parameters = (SaLDec) apply(node.getListeparam());
        SaInst body = (SaInst) apply(node.getInstrbloc());

        returnValue = new SaDecFonc(name, parameters, null, body);
    }

    @Override
    public void caseASansparamListeparam(ASansparamListeparam node) {
        returnValue = new SaLExp(null, null);
    }

    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node) {
        SaLExp param = (SaLExp) apply(node.getListedecvar());
        returnValue = new SaLExp(null, param);
    }

    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node) {
        apply(node.getInstraffect());
    }

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node) {
        apply(node.getInstrbloc());
    }

    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node) {
        apply(node.getInstrif());
    }

    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node) {
        apply(node.getInstrwhile());
    }

    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node) {
        apply(node.getInstrappel());
    }

    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node) {
        apply(node.getInstrreturn());
    }

    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node) {
        apply(node.getInstrwrite());
    }

    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node) {
        returnValue = null;
    }

    @Override
    public void caseAInstraffect(AInstraffect node) {
        SaVar var = (SaVar) apply(node.getVar());
        SaExp exp = (SaExp) apply(node.getExpr());
        returnValue = new SaInstAffect(var, exp);
    }

    @Override
    public void caseAInstrbloc(AInstrbloc node) {
        SaLInst bloc = (SaLInst) apply(node.getListeinst());
        returnValue = new SaInstBloc(bloc);
    }

    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node) {
        SaInst head = (SaInst) apply(node.getInstr());
        SaLInst queue = (SaLInst) apply(node.getListeinst());
        returnValue = new SaLInst(head, queue);
    }

    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node) {
        returnValue = new SaLInst(null, null);
    }

    @Override
    public void caseAAvecsinonInstrif(AAvecsinonInstrif node) {
        SaExp test = (SaExp) apply(node.getExpr());
        SaInstBloc alors = (SaInstBloc) apply(node.getInstrbloc());
        SaInst sinon = (SaInst) apply(node.getInstrelseif());
        returnValue = new SaInstSi(test, alors, sinon);
    }

    @Override
    public void caseASanssinonInstrif(ASanssinonInstrif node) {
        SaExp test = (SaExp) apply(node.getExpr());
        SaInstBloc alors = (SaInstBloc) apply(node.getInstrbloc());
        returnValue = new SaInstSi(test, alors, null);
    }

    @Override
    public void caseAInstrelseif(AInstrelseif node) {
        returnValue = (SaInstBloc) apply(node.getInstrbloc());
    }

    @Override
    public void caseAInstrwhile(AInstrwhile node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaInstBloc inst = (SaInstBloc) apply(node.getInstrbloc());
        returnValue = new SaInstTantQue(exp, inst);
    }

    // TODO : String à trouver
    @Override
    public void caseAInstrappel(AInstrappel node) {
        apply(node.getAppelfct());
    }

    @Override
    public void caseAInstrreturn(AInstrreturn node) {
        SaExp val = (SaExp) apply(node.getExpr());
        returnValue = new SaInstRetour(val);
    }

    @Override
    public void caseAInstrwrite(AInstrwrite node) {
        SaExp arg = (SaExp) apply(node.getExpr());
        returnValue = new SaInstEcriture(arg);
    }

    @Override
    public void caseAInstrvoid(AInstrvoid node) {
        returnValue = null;
    }

    @Override
    public void caseAOuExpr(AOuExpr node) {
        SaExp op1 = (SaExp) apply(node.getExpr());
        SaExp op2 = (SaExp) apply(node.getExprOu());
        returnValue = new SaExpOr(op1, op2);
    }

    @Override
    public void caseASimpleExpr(ASimpleExpr node) {
        SaExp op2 = (SaExp) apply(node.getExprOu());
        returnValue = new SaExpOr(null, op2);
    }

    @Override
    public void caseAEtExprOu(AEtExprOu node) {
        SaExpOr op1 = (SaExpOr) apply(node.getExprOu());
        SaExpOr op2 = (SaExpOr) apply(node.getExprEt());
        returnValue = new SaExpAnd(op1, op2);
    }

    @Override
    public void caseASimpleExprOu(ASimpleExprOu node) {
        SaExpOr op2 = (SaExpOr) apply(node.getExprEt());
        returnValue = new SaExpAnd(null, op2);
    }

    @Override
    public void caseAEgalExprEt(AEgalExprEt node) {
        SaExpAnd op1 = (SaExpAnd) apply(node.getExprEt());
        SaExpAnd op2 = (SaExpAnd) apply(node.getExprEgal());
        returnValue = new SaExpEqual(op1, op2);
    }

    @Override
    public void caseAInfExprEt(AInfExprEt node) {
        SaExpAnd op1 = (SaExpAnd) apply(node.getExprEt());
        SaExpAnd op2 = (SaExpAnd) apply(node.getExprEgal());
        returnValue = new SaExpEqual(op1, op2);
    }

    @Override
    public void caseASimpleExprEt(ASimpleExprEt node) {
        SaExpAnd op1 = (SaExpAnd) apply(node.getExprEgal());
        returnValue = new SaExpEqual(op1, null);
    }

    @Override
    public void caseAPlusExprEgal(APlusExprEgal node) {
        SaExpEqual op1 = (SaExpEqual) apply(node.getExprEgal());
        SaExpEqual op2 = (SaExpEqual) apply(node.getExprPlus());
        returnValue = new SaExpAdd(op1, op2);
    }

    @Override
    public void caseAMoinsExprEgal(AMoinsExprEgal node) {
        SaExpEqual op1 = (SaExpEqual) apply(node.getExprEgal());
        SaExpEqual op2 = (SaExpEqual) apply(node.getExprPlus());
        returnValue = new SaExpSub(op1, op2);
    }

    @Override
    public void caseASimpleExprEgal(ASimpleExprEgal node) {
        SaExpEqual op1 = (SaExpEqual) apply(node.getExprPlus());
        returnValue = new SaExpAdd(op1, null);
    }

    @Override
    public void caseAMultiplierExprPlus(AMultiplierExprPlus node) {
        SaExp op1 = (SaExp) apply(node.getExprPlus());
        SaExp op2 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpMult(op1, op2);
    }

    @Override
    public void caseADiviserExprPlus(ADiviserExprPlus node) {
        SaExp op1 = (SaExp) apply(node.getExprPlus());
        SaExp op2 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpDiv(op1, op2);
    }

    @Override
    public void caseASimpleExprPlus(ASimpleExprPlus node) {
        SaExp op1 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpMult(op1, null);
    }

    @Override
    public void caseANonExprMult(ANonExprMult node) {
        SaExp op1 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpNot(op1);
    }

    @Override
    public void caseASimpleExprMult(ASimpleExprMult node) {
        returnValue = apply(node.getExprComplete());
    }

    @Override
    public void caseANombreExprComplete(ANombreExprComplete node) {
        returnValue = new SaExpInt(Integer.parseInt(node.getNumber().getText()));
    }

    @Override
    public void caseAVarExprComplete(AVarExprComplete node) {
        SaVar var = (SaVar) apply(node.getVar());
        returnValue = new SaExpVar(var);
    }

    @Override
    public void caseALireExprComplete(ALireExprComplete node) {
        returnValue = new SaExpLire();
    }

    @Override
    public void caseAParenthesesExprComplete(AParenthesesExprComplete node) {

    }

    @Override
    public void caseAAppelfctExprComplete(AAppelfctExprComplete node) {
        returnValue = (SaAppel) apply(node.getAppelfct());
    }

    @Override
    public void caseAVartabVar(AVartabVar node) {
        SaExp indice = (SaExp) apply(node.getExpr());
        returnValue = new SaVarIndicee(node.getIdentificator().getText(), indice);
    }

    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node) {
        returnValue = new SaVarSimple(node.getIdentificator().getText());
    }

    @Override
    public void caseAElementsmultiplesListexpr(AElementsmultiplesListexpr node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp lExp = (SaLExp) apply(node.getListexprChainee());
        returnValue = new SaLExp(exp, lExp);
    }

    @Override
    public void caseAVideListexpr(AVideListexpr node) {
        returnValue = new SaLExp(null, null);
    }

    @Override
    public void caseAElementsmultiplesListexprChainee(AElementsmultiplesListexprChainee node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp lExp = (SaLExp) apply(node.getListexprChainee());
        returnValue = new SaLExp(exp, lExp);
    }

    @Override
    public void caseAVideListexprChainee(AVideListexprChainee node) {
        returnValue = new SaLExp(null, null);
    }

    @Override
    public void caseAAvecparamAppelfct(AAvecparamAppelfct node) {
        String name = node.getIdentificator().getText();
        SaLExp params = (SaLExp) apply(node.getListexpr()) ;
        returnValue = new SaAppel(name, params);
    }



    public SaNode getRoot() {
        return returnValue;
    }
}
