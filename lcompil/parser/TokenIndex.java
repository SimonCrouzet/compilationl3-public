/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.parser;

import lcompil.node.*;
import lcompil.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTNumber(@SuppressWarnings("unused") TNumber node)
    {
        this.index = 0;
    }

    @Override
    public void caseTWord(@SuppressWarnings("unused") TWord node)
    {
        this.index = 1;
    }

    @Override
    public void caseTLetter(@SuppressWarnings("unused") TLetter node)
    {
        this.index = 2;
    }

    @Override
    public void caseTLetterMin(@SuppressWarnings("unused") TLetterMin node)
    {
        this.index = 3;
    }

    @Override
    public void caseTIdentificator(@SuppressWarnings("unused") TIdentificator node)
    {
        this.index = 4;
    }

    @Override
    public void caseTOr(@SuppressWarnings("unused") TOr node)
    {
        this.index = 5;
    }

    @Override
    public void caseTAnd(@SuppressWarnings("unused") TAnd node)
    {
        this.index = 6;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 7;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 8;
    }

    @Override
    public void caseTMult(@SuppressWarnings("unused") TMult node)
    {
        this.index = 9;
    }

    @Override
    public void caseTDiv(@SuppressWarnings("unused") TDiv node)
    {
        this.index = 10;
    }

    @Override
    public void caseTEquals(@SuppressWarnings("unused") TEquals node)
    {
        this.index = 11;
    }

    @Override
    public void caseTInferior(@SuppressWarnings("unused") TInferior node)
    {
        this.index = 12;
    }

    @Override
    public void caseTNo(@SuppressWarnings("unused") TNo node)
    {
        this.index = 13;
    }

    @Override
    public void caseTLPar(@SuppressWarnings("unused") TLPar node)
    {
        this.index = 14;
    }

    @Override
    public void caseTRPar(@SuppressWarnings("unused") TRPar node)
    {
        this.index = 15;
    }

    @Override
    public void caseTComma(@SuppressWarnings("unused") TComma node)
    {
        this.index = 16;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 17;
    }
}
