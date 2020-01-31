/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.analysis;

import java.util.*;
import lcompil.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProgram().apply(this);
        outStart(node);
    }

    public void inADeclarationsProgram(ADeclarationsProgram node)
    {
        defaultIn(node);
    }

    public void outADeclarationsProgram(ADeclarationsProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclarationsProgram(ADeclarationsProgram node)
    {
        inADeclarationsProgram(node);
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
        }
        if(node.getOptdecvar() != null)
        {
            node.getOptdecvar().apply(this);
        }
        outADeclarationsProgram(node);
    }

    public void inAListeFonctionProgram(AListeFonctionProgram node)
    {
        defaultIn(node);
    }

    public void outAListeFonctionProgram(AListeFonctionProgram node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAListeFonctionProgram(AListeFonctionProgram node)
    {
        inAListeFonctionProgram(node);
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
        }
        outAListeFonctionProgram(node);
    }

    public void inAOptdecvar(AOptdecvar node)
    {
        defaultIn(node);
    }

    public void outAOptdecvar(AOptdecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOptdecvar(AOptdecvar node)
    {
        inAOptdecvar(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getListedecvar() != null)
        {
            node.getListedecvar().apply(this);
        }
        outAOptdecvar(node);
    }

    public void inADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node)
    {
        defaultIn(node);
    }

    public void outADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclarationVariablesMultiplesListedecvar(ADeclarationVariablesMultiplesListedecvar node)
    {
        inADeclarationVariablesMultiplesListedecvar(node);
        if(node.getListedecvarChainee() != null)
        {
            node.getListedecvarChainee().apply(this);
        }
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        outADeclarationVariablesMultiplesListedecvar(node);
    }

    public void inADeclarerVariableListedecvar(ADeclarerVariableListedecvar node)
    {
        defaultIn(node);
    }

    public void outADeclarerVariableListedecvar(ADeclarerVariableListedecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclarerVariableListedecvar(ADeclarerVariableListedecvar node)
    {
        inADeclarerVariableListedecvar(node);
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        outADeclarerVariableListedecvar(node);
    }

    public void inADecvarldecvarListedecvarChainee(ADecvarldecvarListedecvarChainee node)
    {
        defaultIn(node);
    }

    public void outADecvarldecvarListedecvarChainee(ADecvarldecvarListedecvarChainee node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvarldecvarListedecvarChainee(ADecvarldecvarListedecvarChainee node)
    {
        inADecvarldecvarListedecvarChainee(node);
        if(node.getListedecvarChainee() != null)
        {
            node.getListedecvarChainee().apply(this);
        }
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        outADecvarldecvarListedecvarChainee(node);
    }

    public void inADecvarListedecvarChainee(ADecvarListedecvarChainee node)
    {
        defaultIn(node);
    }

    public void outADecvarListedecvarChainee(ADecvarListedecvarChainee node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvarListedecvarChainee(ADecvarListedecvarChainee node)
    {
        inADecvarListedecvarChainee(node);
        if(node.getDecvar() != null)
        {
            node.getDecvar().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        outADecvarListedecvarChainee(node);
    }

    public void inADecvarentierDecvar(ADecvarentierDecvar node)
    {
        defaultIn(node);
    }

    public void outADecvarentierDecvar(ADecvarentierDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvarentierDecvar(ADecvarentierDecvar node)
    {
        inADecvarentierDecvar(node);
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        outADecvarentierDecvar(node);
    }

    public void inADecvartableauDecvar(ADecvartableauDecvar node)
    {
        defaultIn(node);
    }

    public void outADecvartableauDecvar(ADecvartableauDecvar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvartableauDecvar(ADecvartableauDecvar node)
    {
        inADecvartableauDecvar(node);
        if(node.getRCrochet() != null)
        {
            node.getRCrochet().apply(this);
        }
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        if(node.getLCrochet() != null)
        {
            node.getLCrochet().apply(this);
        }
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        outADecvartableauDecvar(node);
    }

    public void inALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node)
    {
        defaultIn(node);
    }

    public void outALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALdecfoncrecListedecfonc(ALdecfoncrecListedecfonc node)
    {
        inALdecfoncrecListedecfonc(node);
        if(node.getListedecfonc() != null)
        {
            node.getListedecfonc().apply(this);
        }
        if(node.getDecfonc() != null)
        {
            node.getDecfonc().apply(this);
        }
        outALdecfoncrecListedecfonc(node);
    }

    public void inALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node)
    {
        defaultIn(node);
    }

    public void outALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALdecfoncfinalListedecfonc(ALdecfoncfinalListedecfonc node)
    {
        inALdecfoncfinalListedecfonc(node);
        outALdecfoncfinalListedecfonc(node);
    }

    public void inADecvarinstrDecfonc(ADecvarinstrDecfonc node)
    {
        defaultIn(node);
    }

    public void outADecvarinstrDecfonc(ADecvarinstrDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADecvarinstrDecfonc(ADecvarinstrDecfonc node)
    {
        inADecvarinstrDecfonc(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getOptdecvar() != null)
        {
            node.getOptdecvar().apply(this);
        }
        if(node.getListeparam() != null)
        {
            node.getListeparam().apply(this);
        }
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        outADecvarinstrDecfonc(node);
    }

    public void inAInstrDecfonc(AInstrDecfonc node)
    {
        defaultIn(node);
    }

    public void outAInstrDecfonc(AInstrDecfonc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrDecfonc(AInstrDecfonc node)
    {
        inAInstrDecfonc(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getListeparam() != null)
        {
            node.getListeparam().apply(this);
        }
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        outAInstrDecfonc(node);
    }

    public void inASansparamListeparam(ASansparamListeparam node)
    {
        defaultIn(node);
    }

    public void outASansparamListeparam(ASansparamListeparam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASansparamListeparam(ASansparamListeparam node)
    {
        inASansparamListeparam(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outASansparamListeparam(node);
    }

    public void inAAvecparamListeparam(AAvecparamListeparam node)
    {
        defaultIn(node);
    }

    public void outAAvecparamListeparam(AAvecparamListeparam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAvecparamListeparam(AAvecparamListeparam node)
    {
        inAAvecparamListeparam(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getListedecvar() != null)
        {
            node.getListedecvar().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAAvecparamListeparam(node);
    }

    public void inAInstraffectInstr(AInstraffectInstr node)
    {
        defaultIn(node);
    }

    public void outAInstraffectInstr(AInstraffectInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstraffectInstr(AInstraffectInstr node)
    {
        inAInstraffectInstr(node);
        if(node.getInstraffect() != null)
        {
            node.getInstraffect().apply(this);
        }
        outAInstraffectInstr(node);
    }

    public void inAInstrblocInstr(AInstrblocInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrblocInstr(AInstrblocInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrblocInstr(AInstrblocInstr node)
    {
        inAInstrblocInstr(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        outAInstrblocInstr(node);
    }

    public void inAInstrsiInstr(AInstrsiInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrsiInstr(AInstrsiInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrsiInstr(AInstrsiInstr node)
    {
        inAInstrsiInstr(node);
        if(node.getInstrif() != null)
        {
            node.getInstrif().apply(this);
        }
        outAInstrsiInstr(node);
    }

    public void inAInstrtantqueInstr(AInstrtantqueInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrtantqueInstr(AInstrtantqueInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrtantqueInstr(AInstrtantqueInstr node)
    {
        inAInstrtantqueInstr(node);
        if(node.getInstrwhile() != null)
        {
            node.getInstrwhile().apply(this);
        }
        outAInstrtantqueInstr(node);
    }

    public void inAInstrappelInstr(AInstrappelInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrappelInstr(AInstrappelInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrappelInstr(AInstrappelInstr node)
    {
        inAInstrappelInstr(node);
        if(node.getInstrappel() != null)
        {
            node.getInstrappel().apply(this);
        }
        outAInstrappelInstr(node);
    }

    public void inAInstrretourInstr(AInstrretourInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrretourInstr(AInstrretourInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrretourInstr(AInstrretourInstr node)
    {
        inAInstrretourInstr(node);
        if(node.getInstrreturn() != null)
        {
            node.getInstrreturn().apply(this);
        }
        outAInstrretourInstr(node);
    }

    public void inAInstrecritureInstr(AInstrecritureInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrecritureInstr(AInstrecritureInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrecritureInstr(AInstrecritureInstr node)
    {
        inAInstrecritureInstr(node);
        if(node.getInstrwrite() != null)
        {
            node.getInstrwrite().apply(this);
        }
        outAInstrecritureInstr(node);
    }

    public void inAInstrvideInstr(AInstrvideInstr node)
    {
        defaultIn(node);
    }

    public void outAInstrvideInstr(AInstrvideInstr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrvideInstr(AInstrvideInstr node)
    {
        inAInstrvideInstr(node);
        if(node.getInstrvoid() != null)
        {
            node.getInstrvoid().apply(this);
        }
        outAInstrvideInstr(node);
    }

    public void inAInstraffect(AInstraffect node)
    {
        defaultIn(node);
    }

    public void outAInstraffect(AInstraffect node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstraffect(AInstraffect node)
    {
        inAInstraffect(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExprComplete() != null)
        {
            node.getExprComplete().apply(this);
        }
        outAInstraffect(node);
    }

    public void inAInstrbloc(AInstrbloc node)
    {
        defaultIn(node);
    }

    public void outAInstrbloc(AInstrbloc node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrbloc(AInstrbloc node)
    {
        inAInstrbloc(node);
        if(node.getRAccolade() != null)
        {
            node.getRAccolade().apply(this);
        }
        if(node.getListeinst() != null)
        {
            node.getListeinst().apply(this);
        }
        if(node.getLAccolade() != null)
        {
            node.getLAccolade().apply(this);
        }
        outAInstrbloc(node);
    }

    public void inALinstrecListeinst(ALinstrecListeinst node)
    {
        defaultIn(node);
    }

    public void outALinstrecListeinst(ALinstrecListeinst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALinstrecListeinst(ALinstrecListeinst node)
    {
        inALinstrecListeinst(node);
        if(node.getListeinst() != null)
        {
            node.getListeinst().apply(this);
        }
        if(node.getInstr() != null)
        {
            node.getInstr().apply(this);
        }
        outALinstrecListeinst(node);
    }

    public void inALinstfinalListeinst(ALinstfinalListeinst node)
    {
        defaultIn(node);
    }

    public void outALinstfinalListeinst(ALinstfinalListeinst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALinstfinalListeinst(ALinstfinalListeinst node)
    {
        inALinstfinalListeinst(node);
        outALinstfinalListeinst(node);
    }

    public void inAAvecsinonInstrif(AAvecsinonInstrif node)
    {
        defaultIn(node);
    }

    public void outAAvecsinonInstrif(AAvecsinonInstrif node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAvecsinonInstrif(AAvecsinonInstrif node)
    {
        inAAvecsinonInstrif(node);
        if(node.getInstrelseif() != null)
        {
            node.getInstrelseif().apply(this);
        }
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        outAAvecsinonInstrif(node);
    }

    public void inASanssinonInstrif(ASanssinonInstrif node)
    {
        defaultIn(node);
    }

    public void outASanssinonInstrif(ASanssinonInstrif node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASanssinonInstrif(ASanssinonInstrif node)
    {
        inASanssinonInstrif(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        outASanssinonInstrif(node);
    }

    public void inAInstrelseif(AInstrelseif node)
    {
        defaultIn(node);
    }

    public void outAInstrelseif(AInstrelseif node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrelseif(AInstrelseif node)
    {
        inAInstrelseif(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getElseif() != null)
        {
            node.getElseif().apply(this);
        }
        outAInstrelseif(node);
    }

    public void inAInstrwhile(AInstrwhile node)
    {
        defaultIn(node);
    }

    public void outAInstrwhile(AInstrwhile node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrwhile(AInstrwhile node)
    {
        inAInstrwhile(node);
        if(node.getInstrbloc() != null)
        {
            node.getInstrbloc().apply(this);
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        outAInstrwhile(node);
    }

    public void inAInstrappel(AInstrappel node)
    {
        defaultIn(node);
    }

    public void outAInstrappel(AInstrappel node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrappel(AInstrappel node)
    {
        inAInstrappel(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getAppelfct() != null)
        {
            node.getAppelfct().apply(this);
        }
        outAInstrappel(node);
    }

    public void inAInstrreturn(AInstrreturn node)
    {
        defaultIn(node);
    }

    public void outAInstrreturn(AInstrreturn node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrreturn(AInstrreturn node)
    {
        inAInstrreturn(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
        }
        outAInstrreturn(node);
    }

    public void inAInstrwrite(AInstrwrite node)
    {
        defaultIn(node);
    }

    public void outAInstrwrite(AInstrwrite node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrwrite(AInstrwrite node)
    {
        inAInstrwrite(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getWrite() != null)
        {
            node.getWrite().apply(this);
        }
        outAInstrwrite(node);
    }

    public void inAInstrvoid(AInstrvoid node)
    {
        defaultIn(node);
    }

    public void outAInstrvoid(AInstrvoid node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInstrvoid(AInstrvoid node)
    {
        inAInstrvoid(node);
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        outAInstrvoid(node);
    }

    public void inAAvecparamAppelfct(AAvecparamAppelfct node)
    {
        defaultIn(node);
    }

    public void outAAvecparamAppelfct(AAvecparamAppelfct node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAvecparamAppelfct(AAvecparamAppelfct node)
    {
        inAAvecparamAppelfct(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getList() != null)
        {
            node.getList().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        outAAvecparamAppelfct(node);
    }

    public void inAOuExpr(AOuExpr node)
    {
        defaultIn(node);
    }

    public void outAOuExpr(AOuExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAOuExpr(AOuExpr node)
    {
        inAOuExpr(node);
        if(node.getExprOu() != null)
        {
            node.getExprOu().apply(this);
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAOuExpr(node);
    }

    public void inASimpleExpr(ASimpleExpr node)
    {
        defaultIn(node);
    }

    public void outASimpleExpr(ASimpleExpr node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExpr(ASimpleExpr node)
    {
        inASimpleExpr(node);
        if(node.getExprOu() != null)
        {
            node.getExprOu().apply(this);
        }
        outASimpleExpr(node);
    }

    public void inAEtExprOu(AEtExprOu node)
    {
        defaultIn(node);
    }

    public void outAEtExprOu(AEtExprOu node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEtExprOu(AEtExprOu node)
    {
        inAEtExprOu(node);
        if(node.getExprEt() != null)
        {
            node.getExprEt().apply(this);
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getExprOu() != null)
        {
            node.getExprOu().apply(this);
        }
        outAEtExprOu(node);
    }

    public void inASimpleExprOu(ASimpleExprOu node)
    {
        defaultIn(node);
    }

    public void outASimpleExprOu(ASimpleExprOu node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExprOu(ASimpleExprOu node)
    {
        inASimpleExprOu(node);
        if(node.getExprEt() != null)
        {
            node.getExprEt().apply(this);
        }
        outASimpleExprOu(node);
    }

    public void inAEgalExprEt(AEgalExprEt node)
    {
        defaultIn(node);
    }

    public void outAEgalExprEt(AEgalExprEt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEgalExprEt(AEgalExprEt node)
    {
        inAEgalExprEt(node);
        if(node.getExprEgal() != null)
        {
            node.getExprEgal().apply(this);
        }
        if(node.getEquals() != null)
        {
            node.getEquals().apply(this);
        }
        if(node.getExprEt() != null)
        {
            node.getExprEt().apply(this);
        }
        outAEgalExprEt(node);
    }

    public void inAInfExprEt(AInfExprEt node)
    {
        defaultIn(node);
    }

    public void outAInfExprEt(AInfExprEt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInfExprEt(AInfExprEt node)
    {
        inAInfExprEt(node);
        if(node.getExprEgal() != null)
        {
            node.getExprEgal().apply(this);
        }
        if(node.getInferior() != null)
        {
            node.getInferior().apply(this);
        }
        if(node.getExprEt() != null)
        {
            node.getExprEt().apply(this);
        }
        outAInfExprEt(node);
    }

    public void inASimpleExprEt(ASimpleExprEt node)
    {
        defaultIn(node);
    }

    public void outASimpleExprEt(ASimpleExprEt node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExprEt(ASimpleExprEt node)
    {
        inASimpleExprEt(node);
        if(node.getExprEgal() != null)
        {
            node.getExprEgal().apply(this);
        }
        outASimpleExprEt(node);
    }

    public void inAPlusExprEgal(APlusExprEgal node)
    {
        defaultIn(node);
    }

    public void outAPlusExprEgal(APlusExprEgal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPlusExprEgal(APlusExprEgal node)
    {
        inAPlusExprEgal(node);
        if(node.getExprPlus() != null)
        {
            node.getExprPlus().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getExprEgal() != null)
        {
            node.getExprEgal().apply(this);
        }
        outAPlusExprEgal(node);
    }

    public void inAMoinsExprEgal(AMoinsExprEgal node)
    {
        defaultIn(node);
    }

    public void outAMoinsExprEgal(AMoinsExprEgal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoinsExprEgal(AMoinsExprEgal node)
    {
        inAMoinsExprEgal(node);
        if(node.getExprPlus() != null)
        {
            node.getExprPlus().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getExprEgal() != null)
        {
            node.getExprEgal().apply(this);
        }
        outAMoinsExprEgal(node);
    }

    public void inASimpleExprEgal(ASimpleExprEgal node)
    {
        defaultIn(node);
    }

    public void outASimpleExprEgal(ASimpleExprEgal node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExprEgal(ASimpleExprEgal node)
    {
        inASimpleExprEgal(node);
        if(node.getExprPlus() != null)
        {
            node.getExprPlus().apply(this);
        }
        outASimpleExprEgal(node);
    }

    public void inAMultiplierExprPlus(AMultiplierExprPlus node)
    {
        defaultIn(node);
    }

    public void outAMultiplierExprPlus(AMultiplierExprPlus node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultiplierExprPlus(AMultiplierExprPlus node)
    {
        inAMultiplierExprPlus(node);
        if(node.getExprMult() != null)
        {
            node.getExprMult().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getExprPlus() != null)
        {
            node.getExprPlus().apply(this);
        }
        outAMultiplierExprPlus(node);
    }

    public void inADiviserExprPlus(ADiviserExprPlus node)
    {
        defaultIn(node);
    }

    public void outADiviserExprPlus(ADiviserExprPlus node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADiviserExprPlus(ADiviserExprPlus node)
    {
        inADiviserExprPlus(node);
        if(node.getExprMult() != null)
        {
            node.getExprMult().apply(this);
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getExprPlus() != null)
        {
            node.getExprPlus().apply(this);
        }
        outADiviserExprPlus(node);
    }

    public void inASimpleExprPlus(ASimpleExprPlus node)
    {
        defaultIn(node);
    }

    public void outASimpleExprPlus(ASimpleExprPlus node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExprPlus(ASimpleExprPlus node)
    {
        inASimpleExprPlus(node);
        if(node.getExprMult() != null)
        {
            node.getExprMult().apply(this);
        }
        outASimpleExprPlus(node);
    }

    public void inANonExprMult(ANonExprMult node)
    {
        defaultIn(node);
    }

    public void outANonExprMult(ANonExprMult node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANonExprMult(ANonExprMult node)
    {
        inANonExprMult(node);
        if(node.getExprMult() != null)
        {
            node.getExprMult().apply(this);
        }
        if(node.getNo() != null)
        {
            node.getNo().apply(this);
        }
        outANonExprMult(node);
    }

    public void inASimpleExprMult(ASimpleExprMult node)
    {
        defaultIn(node);
    }

    public void outASimpleExprMult(ASimpleExprMult node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExprMult(ASimpleExprMult node)
    {
        inASimpleExprMult(node);
        if(node.getExprNo() != null)
        {
            node.getExprNo().apply(this);
        }
        outASimpleExprMult(node);
    }

    public void inAParenthesesExprNo(AParenthesesExprNo node)
    {
        defaultIn(node);
    }

    public void outAParenthesesExprNo(AParenthesesExprNo node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParenthesesExprNo(AParenthesesExprNo node)
    {
        inAParenthesesExprNo(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        outAParenthesesExprNo(node);
    }

    public void inASimpleExprNo(ASimpleExprNo node)
    {
        defaultIn(node);
    }

    public void outASimpleExprNo(ASimpleExprNo node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExprNo(ASimpleExprNo node)
    {
        inASimpleExprNo(node);
        if(node.getExprComplete() != null)
        {
            node.getExprComplete().apply(this);
        }
        outASimpleExprNo(node);
    }

    public void inANombreExprComplete(ANombreExprComplete node)
    {
        defaultIn(node);
    }

    public void outANombreExprComplete(ANombreExprComplete node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANombreExprComplete(ANombreExprComplete node)
    {
        inANombreExprComplete(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANombreExprComplete(node);
    }

    public void inAVarExprComplete(AVarExprComplete node)
    {
        defaultIn(node);
    }

    public void outAVarExprComplete(AVarExprComplete node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarExprComplete(AVarExprComplete node)
    {
        inAVarExprComplete(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        outAVarExprComplete(node);
    }

    public void inALireExprComplete(ALireExprComplete node)
    {
        defaultIn(node);
    }

    public void outALireExprComplete(ALireExprComplete node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALireExprComplete(ALireExprComplete node)
    {
        inALireExprComplete(node);
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getRead() != null)
        {
            node.getRead().apply(this);
        }
        outALireExprComplete(node);
    }

    public void inAAppelfctExprComplete(AAppelfctExprComplete node)
    {
        defaultIn(node);
    }

    public void outAAppelfctExprComplete(AAppelfctExprComplete node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAppelfctExprComplete(AAppelfctExprComplete node)
    {
        inAAppelfctExprComplete(node);
        if(node.getAppelfct() != null)
        {
            node.getAppelfct().apply(this);
        }
        outAAppelfctExprComplete(node);
    }

    public void inAVartabVar(AVartabVar node)
    {
        defaultIn(node);
    }

    public void outAVartabVar(AVartabVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVartabVar(AVartabVar node)
    {
        inAVartabVar(node);
        if(node.getRCrochet() != null)
        {
            node.getRCrochet().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getLCrochet() != null)
        {
            node.getLCrochet().apply(this);
        }
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        outAVartabVar(node);
    }

    public void inAVarsimpleVar(AVarsimpleVar node)
    {
        defaultIn(node);
    }

    public void outAVarsimpleVar(AVarsimpleVar node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarsimpleVar(AVarsimpleVar node)
    {
        inAVarsimpleVar(node);
        if(node.getIdentificator() != null)
        {
            node.getIdentificator().apply(this);
        }
        outAVarsimpleVar(node);
    }

    public void inAElementsmultiplesList(AElementsmultiplesList node)
    {
        defaultIn(node);
    }

    public void outAElementsmultiplesList(AElementsmultiplesList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElementsmultiplesList(AElementsmultiplesList node)
    {
        inAElementsmultiplesList(node);
        if(node.getListChainee() != null)
        {
            node.getListChainee().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        outAElementsmultiplesList(node);
    }

    public void inAVideList(AVideList node)
    {
        defaultIn(node);
    }

    public void outAVideList(AVideList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideList(AVideList node)
    {
        inAVideList(node);
        outAVideList(node);
    }

    public void inAElementsmultiplesListChainee(AElementsmultiplesListChainee node)
    {
        defaultIn(node);
    }

    public void outAElementsmultiplesListChainee(AElementsmultiplesListChainee node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElementsmultiplesListChainee(AElementsmultiplesListChainee node)
    {
        inAElementsmultiplesListChainee(node);
        if(node.getListChainee() != null)
        {
            node.getListChainee().apply(this);
        }
        if(node.getExpr() != null)
        {
            node.getExpr().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        outAElementsmultiplesListChainee(node);
    }

    public void inAVideListChainee(AVideListChainee node)
    {
        defaultIn(node);
    }

    public void outAVideListChainee(AVideListChainee node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVideListChainee(AVideListChainee node)
    {
        inAVideListChainee(node);
        outAVideListChainee(node);
    }
}
