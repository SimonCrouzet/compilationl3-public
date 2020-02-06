import lcompil.analysis.DepthFirstAdapter;
import lcompil.node.*;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    @Override
    public void caseADeclarationsProgram(ADeclarationsProgram node) {
        super.caseADeclarationsProgram(node);
    }

    @Override
    public void caseADecVariablesOptdecvar(ADecVariablesOptdecvar node) {
        super.caseADecVariablesOptdecvar(node);
    }

    @Override
    public void caseANoVariablesOptdecvar(ANoVariablesOptdecvar node) {
        super.caseANoVariablesOptdecvar(node);
    }

    @Override
    public void caseADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node) {
        super.caseADeclarationVariablesMultiplesListedecvar(node);
    }

    @Override
    public void caseADeclarerVariableListedecvar(ADeclarerVariableListedecvar node) {
        super.caseADeclarerVariableListedecvar(node);
    }

    @Override
    public void caseAMoreVariablesListedecvarChainee(AMoreVariablesListedecvarChainee node) {
        super.caseAMoreVariablesListedecvarChainee(node);
    }

    @Override
    public void caseALastListedecvarChainee(ALastListedecvarChainee node) {
        super.caseALastListedecvarChainee(node);
    }

    @Override
    public void caseADecvarEntierDecvar(ADecvarEntierDecvar node) {
        super.caseADecvarEntierDecvar(node);
    }

    @Override
    public void caseADecvarTableauDecvar(ADecvarTableauDecvar node) {
        super.caseADecvarTableauDecvar(node);
    }

    @Override
    public void caseAListedecfoncRecListedecfonc(AListedecfoncRecListedecfonc node) {
        super.caseAListedecfoncRecListedecfonc(node);
    }

    @Override
    public void caseAListedecfoncFinalListedecfonc(AListedecfoncFinalListedecfonc node) {
        super.caseAListedecfoncFinalListedecfonc(node);
    }

    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node) {
        super.caseADecvarinstrDecfonc(node);
    }

    @Override
    public void caseASansparamListeparam(ASansparamListeparam node) {
        super.caseASansparamListeparam(node);
    }

    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node) {
        super.caseAAvecparamListeparam(node);
    }

    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node) {
        super.caseAInstraffectInstr(node);
    }

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node) {
        super.caseAInstrblocInstr(node);
    }

    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node) {
        super.caseAInstrsiInstr(node);
    }

    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node) {
        super.caseAInstrtantqueInstr(node);
    }

    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node) {
        super.caseAInstrappelInstr(node);
    }

    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node) {
        super.caseAInstrretourInstr(node);
    }

    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node) {
        super.caseAInstrecritureInstr(node);
    }

    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node) {
        super.caseAInstrvideInstr(node);
    }

    @Override
    public void caseAInstraffect(AInstraffect node) {
        super.caseAInstraffect(node);
    }

    @Override
    public void caseAInstrbloc(AInstrbloc node) {
        super.caseAInstrbloc(node);
    }

    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node) {
        super.caseALinstrecListeinst(node);
    }

    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node) {
        super.caseALinstfinalListeinst(node);
    }

    @Override
    public void caseAAvecsinonInstrif(AAvecsinonInstrif node) {
        super.caseAAvecsinonInstrif(node);
    }

    @Override
    public void caseASanssinonInstrif(ASanssinonInstrif node) {
        super.caseASanssinonInstrif(node);
    }

    @Override
    public void caseAInstrelseif(AInstrelseif node) {
        super.caseAInstrelseif(node);
    }

    @Override
    public void caseAInstrwhile(AInstrwhile node) {
        super.caseAInstrwhile(node);
    }

    @Override
    public void caseAInstrappel(AInstrappel node) {
        super.caseAInstrappel(node);
    }

    @Override
    public void caseAInstrreturn(AInstrreturn node) {
        super.caseAInstrreturn(node);
    }

    @Override
    public void caseAInstrwrite(AInstrwrite node) {
        super.caseAInstrwrite(node);
    }

    @Override
    public void caseAInstrvoid(AInstrvoid node) {
        super.caseAInstrvoid(node);
    }

    @Override
    public void caseAAvecparamAppelfct(AAvecparamAppelfct node) {
        super.caseAAvecparamAppelfct(node);
    }

    @Override
    public void caseAOuExpr(AOuExpr node) {
        super.caseAOuExpr(node);
    }

    @Override
    public void caseASimpleExpr(ASimpleExpr node) {
        super.caseASimpleExpr(node);
    }

    @Override
    public void caseAEtExprOu(AEtExprOu node) {
        super.caseAEtExprOu(node);
    }

    @Override
    public void caseASimpleExprOu(ASimpleExprOu node) {
        super.caseASimpleExprOu(node);
    }

    @Override
    public void caseAEgalExprEt(AEgalExprEt node) {
        super.caseAEgalExprEt(node);
    }

    @Override
    public void caseAInfExprEt(AInfExprEt node) {
        super.caseAInfExprEt(node);
    }

    @Override
    public void caseASimpleExprEt(ASimpleExprEt node) {
        super.caseASimpleExprEt(node);
    }

    @Override
    public void caseAPlusExprEgal(APlusExprEgal node) {
        super.caseAPlusExprEgal(node);
    }

    @Override
    public void caseAMoinsExprEgal(AMoinsExprEgal node) {
        super.caseAMoinsExprEgal(node);
    }

    @Override
    public void caseASimpleExprEgal(ASimpleExprEgal node) {
        super.caseASimpleExprEgal(node);
    }

    @Override
    public void caseAMultiplierExprPlus(AMultiplierExprPlus node) {
        super.caseAMultiplierExprPlus(node);
    }

    @Override
    public void caseADiviserExprPlus(ADiviserExprPlus node) {
        super.caseADiviserExprPlus(node);
    }

    @Override
    public void caseASimpleExprPlus(ASimpleExprPlus node) {
        super.caseASimpleExprPlus(node);
    }

    @Override
    public void caseANonExprMult(ANonExprMult node) {
        super.caseANonExprMult(node);
    }

    @Override
    public void caseASimpleExprMult(ASimpleExprMult node) {
        super.caseASimpleExprMult(node);
    }

    @Override
    public void caseAParenthesesExprNo(AParenthesesExprNo node) {
        super.caseAParenthesesExprNo(node);
    }

    @Override
    public void caseASimpleExprNo(ASimpleExprNo node) {
        super.caseASimpleExprNo(node);
    }

    @Override
    public void caseANombreExprComplete(ANombreExprComplete node) {
        super.caseANombreExprComplete(node);
    }

    @Override
    public void caseAVarExprComplete(AVarExprComplete node) {
        super.caseAVarExprComplete(node);
    }

    @Override
    public void caseALireExprComplete(ALireExprComplete node) {
        super.caseALireExprComplete(node);
    }

    @Override
    public void caseAAppelfctExprComplete(AAppelfctExprComplete node) {
        super.caseAAppelfctExprComplete(node);
    }

    @Override
    public void caseAVartabVar(AVartabVar node) {
        super.caseAVartabVar(node);
    }

    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node) {
        super.caseAVarsimpleVar(node);
    }

    @Override
    public void caseAElementsmultiplesList(AElementsmultiplesList node) {
        super.caseAElementsmultiplesList(node);
    }

    @Override
    public void caseAVideList(AVideList node) {
        super.caseAVideList(node);
    }

    @Override
    public void caseAElementsmultiplesListChainee(AElementsmultiplesListChainee node) {
        super.caseAElementsmultiplesListChainee(node);
    }

    @Override
    public void caseAVideListChainee(AVideListChainee node) {
        super.caseAVideListChainee(node);
    }
}
