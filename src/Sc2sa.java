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
        apply(node.getPProgram());
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
        SaLDec variable = null;
        SaLDec fonction = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaProg(variable, fonction);
    }


    @Override
    public void caseADecVariablesOptdecvar(ADecVariablesOptdecvar node) {
        returnValue = (SaLDec) apply(node.getListedecvar());
        // SaLDec decVar = (SaLDec) apply(node.getListedecvar());
        // returnValue = new SaLDec(decVar.getTete(), decVar.getQueue());
    }

    @Override
    public void caseADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node) {
        SaDec headVar = (SaDec) apply(node.getDecvar());
        SaLDec queueVar = (SaLDec) apply(node.getListedecvarChainee());
        returnValue = new SaLDec(headVar, queueVar);
    }

    @Override
    public void caseADeclarerVariableListedecvar(ADeclarerVariableListedecvar node) {
        // SaDec var = (SaDec) apply(node.getDecvar());
        // returnValue = new SaDecVar(var.getNom());
        returnValue = (SaDec) apply(node.getDecvar());
    }

    @Override
    public void caseAMoreVariablesListedecvarChainee(AMoreVariablesListedecvarChainee node) {
        SaDec var = (SaDec) apply(node.getDecvar());
        SaLDec moreVar = (SaLDec) apply(node.getListedecvarChainee());
        returnValue = new SaLDec(var, moreVar);
    }

    @Override
    public void caseALastListedecvarChainee(ALastListedecvarChainee node) {
        // SaDec lastVar = (SaDec) apply(node.getDecvar());
        // returnValue = new SaDecVar(lastVar.getNom());
        returnValue = (SaDec) apply(node.getDecvar());
    }

    @Override
    public void caseADecvarEntierDecvar(ADecvarEntierDecvar node) {
        String name = node.getIdentificator().getText();
        returnValue = new SaDecVar(name);
    }

    @Override
    public void caseADecvarTableauDecvar(ADecvarTableauDecvar node) {
        // SaVarIndicee var = (SaVarIndicee) apply(node.get)
        String name = node.getIdentificator().getText();
        int length = Integer.parseInt(node.getNumber().getText());
        returnValue = new SaDecTab(name, length);
    }

    @Override
    public void caseAListedecfoncRecListedecfonc(AListedecfoncRecListedecfonc node) {
        SaDecFonc headFct = (SaDecFonc) apply(node.getDecfonc());
        SaLDec queueFct = (SaLDec) apply(node.getListedecfonc());
        returnValue = new SaLDec(headFct, queueFct);
    }

    @Override
    public void caseAListedecfoncFinalListedecfonc(AListedecfoncFinalListedecfonc node) {
        returnValue = null;
    }

    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node) {
        String name = node.getIdentificator().getText();
        SaLDec parameters = (SaLDec) apply(node.getListeparam());
        SaLDec variables = (SaLDec) apply(node.getOptdecvar());
        SaInstBloc body = (SaInstBloc) apply(node.getInstrbloc());

        returnValue = new SaDecFonc(name, parameters, variables, body);
    }

    @Override
    public void caseAInstrDecfonc(AInstrDecfonc node) {
        String name = node.getIdentificator().getText();
        SaLDec parameters = (SaLDec) apply(node.getListeparam());
        SaInstBloc body = (SaInstBloc) apply(node.getInstrbloc());

        returnValue = new SaDecFonc(name, parameters, null, body);
    }

    @Override
    public void caseASansparamListeparam(ASansparamListeparam node) {
        returnValue = null;
    }

    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node) {
        returnValue = (SaLDec) apply(node.getListedecvar());
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
        returnValue = null;
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
        returnValue = (SaExp) apply(node.getExprOu());
    }

    @Override
    public void caseAEtExprOu(AEtExprOu node) {
        SaExp op1 = (SaExp) apply(node.getExprOu());
        SaExp op2 = (SaExp) apply(node.getExprEt());
        returnValue = new SaExpAnd(op1, op2);
    }

    @Override
    public void caseASimpleExprOu(ASimpleExprOu node) {
        returnValue = (SaExp) apply(node.getExprEt());
    }

    @Override
    public void caseAEgalExprEt(AEgalExprEt node) {
        SaExp op1 = (SaExp) apply(node.getExprEt());
        SaExp op2 = (SaExp) apply(node.getExprEgal());
        returnValue = new SaExpEqual(op1, op2);
    }

    @Override
    public void caseAInfExprEt(AInfExprEt node) {
        SaExp op1 = (SaExp) apply(node.getExprEt());
        SaExp op2 = (SaExp) apply(node.getExprEgal());
        returnValue = new SaExpInf(op1, op2);
    }

    @Override
    public void caseASimpleExprEt(ASimpleExprEt node) {
        returnValue = (SaExp) apply(node.getExprEgal());
    }

    @Override
    public void caseAPlusExprEgal(APlusExprEgal node) {
        SaExp op1 = (SaExp) apply(node.getExprEgal());
        SaExp op2 = (SaExp) apply(node.getExprPlus());
        returnValue = new SaExpAdd(op1, op2);
    }

    @Override
    public void caseAMoinsExprEgal(AMoinsExprEgal node) {
        SaExp op1 = (SaExp) apply(node.getExprEgal());
        SaExp op2 = (SaExp) apply(node.getExprPlus());
        returnValue = new SaExpSub(op1, op2);
    }

    @Override
    public void caseASimpleExprEgal(ASimpleExprEgal node) {
        returnValue = (SaExp) apply(node.getExprPlus());
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
        returnValue = (SaExp) apply(node.getExprMult());
    }

    @Override
    public void caseANonExprMult(ANonExprMult node) {
        SaExp op1 = (SaExp) apply(node.getExprMult());
        returnValue = new SaExpNot(op1);
    }

    @Override
    public void caseASimpleExprMult(ASimpleExprMult node) {
        returnValue = (SaExp) apply(node.getExprComplete());
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
        returnValue = (SaExp) apply(node.getExpr());
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
        returnValue = null;
    }

    @Override
    public void caseAElementsmultiplesListexprChainee(AElementsmultiplesListexprChainee node) {
        SaExp exp = (SaExp) apply(node.getExpr());
        SaLExp lExp = (SaLExp) apply(node.getListexprChainee());
        returnValue = new SaLExp(exp, lExp);
    }

    @Override
    public void caseAVideListexprChainee(AVideListexprChainee node) {
        returnValue = null;
    }

    @Override
    public void caseAAvecparamAppelfct(AAvecparamAppelfct node) {
        String name = node.getIdentificator().getText();
        SaLExp params = (SaLExp) apply(node.getListexpr());
        returnValue = new SaExpAppel(new SaAppel(name, params));
        //returnValue = new SaAppel(name, params);
    }



    public SaNode getRoot() {
        return returnValue;
    }
}
