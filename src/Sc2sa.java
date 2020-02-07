import lcompil.analysis.DepthFirstAdapter;
import lcompil.node.*;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    @Override
    public void caseStart(Start node) {
        inStart(node);
        node.getPProgram().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    @Override
    public void caseADecVaretfctProgram(ADecVaretfctProgram node) {
        SaLDec variable = null;
        SaLDec fonction = null;
        inADecVaretfctProgram(node);
        if(node.getOptdecvar() != null)
        {
            node.getOptdecvar().apply(this);
            variable = (SaLDec) returnValue;
        }
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
            fonction = (SaLDec) returnValue;
        }
        outADecVaretfctProgram(node);
        returnValue = new SaProg(variable, fonction);
    }

    @Override
    // ToDo Verifier SaProg avec variable null
    public void caseADecFctProgram(ADecFctProgram node) {
        SaLDec fonction = null;
        SaLDec variable = null;
        inADecFctProgram(node);
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
            fonction = (SaLDec) returnValue;
        }
        outADecFctProgram(node);
        returnValue = new SaProg(variable,fonction);
    }


    @Override
    // ToDo Dec Variable Opt
    public void caseADecVariablesOptdecvar(ADecVariablesOptdecvar node) {

        inADecVariablesOptdecvar(node);
        if(node.getListedecvar() != null)
        {
            node.getListedecvar().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        outADecVariablesOptdecvar(node);
    }

    @Override
    // ToDo Dec Variable Multiple
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
    public void caseAInstrDecfonc(AInstrDecfonc node) {
        super.caseAInstrDecfonc(node);
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
        SaExp test = null;
        SaInst alors = null;
        SaInst sinon = null;
        inAAvecsinonInstrif(node);
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
            test = (SaExp) returnValue;
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
            alors = (SaInst) returnValue;
        }
        if(node.getInstrelseif() != null)
        {
            node.getInstrelseif().apply(this);
            sinon = (SaInst) returnValue;

        }
        outAAvecsinonInstrif(node);
        returnValue = new SaInstSi(test, alors, sinon);
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
    public void caseAParenthesesExprComplete(AParenthesesExprComplete node) {
        super.caseAParenthesesExprComplete(node);
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
    public void caseAElementsmultiplesListexpr(AElementsmultiplesListexpr node) {
        super.caseAElementsmultiplesListexpr(node);
    }

    @Override
    public void caseAVideListexpr(AVideListexpr node) {
        super.caseAVideListexpr(node);
    }

    @Override
    public void caseAElementsmultiplesListexprChainee(AElementsmultiplesListexprChainee node) {
        super.caseAElementsmultiplesListexprChainee(node);
    }

    @Override
    public void caseAVideListexprChainee(AVideListexprChainee node) {
        super.caseAVideListexprChainee(node);
    }

    @Override
    public void caseAAvecparamAppelfct(AAvecparamAppelfct node) {
        super.caseAAvecparamAppelfct(node);
    }
}
