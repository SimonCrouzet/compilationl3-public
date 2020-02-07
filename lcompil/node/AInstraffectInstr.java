/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class AInstraffectInstr extends PInstr
{
    private PInstraffect _instraffect_;

    public AInstraffectInstr()
    {
        // Constructor
    }

    public AInstraffectInstr(
        @SuppressWarnings("hiding") PInstraffect _instraffect_)
    {
        // Constructor
        setInstraffect(_instraffect_);

    }

    @Override
    public Object clone()
    {
        return new AInstraffectInstr(
            cloneNode(this._instraffect_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstraffectInstr(this);
    }

    public PInstraffect getInstraffect()
    {
        return this._instraffect_;
    }

    public void setInstraffect(PInstraffect node)
    {
        if(this._instraffect_ != null)
        {
            this._instraffect_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instraffect_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._instraffect_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._instraffect_ == child)
        {
            this._instraffect_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._instraffect_ == oldChild)
        {
            setInstraffect((PInstraffect) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}