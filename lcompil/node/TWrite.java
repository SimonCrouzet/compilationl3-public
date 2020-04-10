/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class TWrite extends Token
{
    public TWrite()
    {
        super.setText("ecrire");
    }

    public TWrite(int line, int pos)
    {
        super.setText("ecrire");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TWrite(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTWrite(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TWrite text.");
    }
}