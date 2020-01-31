/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.analysis;

import lcompil.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseADeclarationsProgram(ADeclarationsProgram node);
    void caseAListeFonctionProgram(AListeFonctionProgram node);
    void caseAOptdecvar(AOptdecvar node);
    void caseADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node);
    void caseADeclarerVariableListedecvar(ADeclarerVariableListedecvar node);
    void caseADecvarldecvarListedecvarChainee(ADecvarldecvarListedecvarChainee node);
    void caseADecvarListedecvarChainee(ADecvarListedecvarChainee node);
    void caseADecvarentierDecvar(ADecvarentierDecvar node);
    void caseADecvartableauDecvar(ADecvartableauDecvar node);
    void caseALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node);
    void caseALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node);
    void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node);
    void caseAInstrDecfonc(AInstrDecfonc node);
    void caseASansparamListeparam(ASansparamListeparam node);
    void caseAAvecparamListeparam(AAvecparamListeparam node);
    void caseAInstraffectInstr(AInstraffectInstr node);
    void caseAInstrblocInstr(AInstrblocInstr node);
    void caseAInstrsiInstr(AInstrsiInstr node);
    void caseAInstrtantqueInstr(AInstrtantqueInstr node);
    void caseAInstrappelInstr(AInstrappelInstr node);
    void caseAInstrretourInstr(AInstrretourInstr node);
    void caseAInstrecritureInstr(AInstrecritureInstr node);
    void caseAInstrvideInstr(AInstrvideInstr node);
    void caseAInstraffect(AInstraffect node);
    void caseAInstrbloc(AInstrbloc node);
    void caseALinstrecListeinst(ALinstrecListeinst node);
    void caseALinstfinalListeinst(ALinstfinalListeinst node);
    void caseAAvecsinonInstrif(AAvecsinonInstrif node);
    void caseASanssinonInstrif(ASanssinonInstrif node);
    void caseAInstrelseif(AInstrelseif node);
    void caseAInstrwhile(AInstrwhile node);
    void caseAInstrappel(AInstrappel node);
    void caseAInstrreturn(AInstrreturn node);
    void caseAInstrwrite(AInstrwrite node);
    void caseAInstrvoid(AInstrvoid node);
    void caseAAvecparamAppelfct(AAvecparamAppelfct node);
    void caseAOuExpr(AOuExpr node);
    void caseASimpleExpr(ASimpleExpr node);
    void caseAEtExprOu(AEtExprOu node);
    void caseASimpleExprOu(ASimpleExprOu node);
    void caseAEgalExprEt(AEgalExprEt node);
    void caseAInfExprEt(AInfExprEt node);
    void caseASimpleExprEt(ASimpleExprEt node);
    void caseAPlusExprEgal(APlusExprEgal node);
    void caseAMoinsExprEgal(AMoinsExprEgal node);
    void caseASimpleExprEgal(ASimpleExprEgal node);
    void caseAMultiplierExprPlus(AMultiplierExprPlus node);
    void caseADiviserExprPlus(ADiviserExprPlus node);
    void caseASimpleExprPlus(ASimpleExprPlus node);
    void caseANonExprMult(ANonExprMult node);
    void caseASimpleExprMult(ASimpleExprMult node);
    void caseAParenthesesExprNo(AParenthesesExprNo node);
    void caseASimpleExprNo(ASimpleExprNo node);
    void caseANombreExprComplete(ANombreExprComplete node);
    void caseAVarExprComplete(AVarExprComplete node);
    void caseALireExprComplete(ALireExprComplete node);
    void caseAAppelfctExprComplete(AAppelfctExprComplete node);
    void caseAVartabVar(AVartabVar node);
    void caseAVarsimpleVar(AVarsimpleVar node);
    void caseAElementsmultiplesList(AElementsmultiplesList node);
    void caseAVideList(AVideList node);
    void caseAElementsmultiplesListChainee(AElementsmultiplesListChainee node);
    void caseAVideListChainee(AVideListChainee node);

    void caseTNumber(TNumber node);
    void caseTWord(TWord node);
    void caseTLetter(TLetter node);
    void caseTLetterMin(TLetterMin node);
    void caseTIdentificator(TIdentificator node);
    void caseTOr(TOr node);
    void caseTAnd(TAnd node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTDiv(TDiv node);
    void caseTEquals(TEquals node);
    void caseTInferior(TInferior node);
    void caseTNo(TNo node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTLCrochet(TLCrochet node);
    void caseTRCrochet(TRCrochet node);
    void caseTLAccolade(TLAccolade node);
    void caseTRAccolade(TRAccolade node);
    void caseTIf(TIf node);
    void caseTThen(TThen node);
    void caseTElseif(TElseif node);
    void caseTWhile(TWhile node);
    void caseTDo(TDo node);
    void caseTInt(TInt node);
    void caseTReturn(TReturn node);
    void caseTRead(TRead node);
    void caseTWrite(TWrite node);
    void caseTBlank(TBlank node);
    void caseTComment(TComment node);
    void caseTComma(TComma node);
    void caseTSemicolon(TSemicolon node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
