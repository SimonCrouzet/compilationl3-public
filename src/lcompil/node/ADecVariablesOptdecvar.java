/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class ADecVariablesOptdecvar extends POptdecvar
{
    private PListedecvar _listedecvar_;
    private TSemicolon _semicolon_;

    public ADecVariablesOptdecvar()
    {
        // Constructor
    }

    public ADecVariablesOptdecvar(
        @SuppressWarnings("hiding") PListedecvar _listedecvar_,
        @SuppressWarnings("hiding") TSemicolon _semicolon_)
    {
        // Constructor
        setListedecvar(_listedecvar_);

        setSemicolon(_semicolon_);

    }

    @Override
    public Object clone()
    {
        return new ADecVariablesOptdecvar(
            cloneNode(this._listedecvar_),
            cloneNode(this._semicolon_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADecVariablesOptdecvar(this);
    }

    public PListedecvar getListedecvar()
    {
        return this._listedecvar_;
    }

    public void setListedecvar(PListedecvar node)
    {
        if(this._listedecvar_ != null)
        {
            this._listedecvar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listedecvar_ = node;
    }

    public TSemicolon getSemicolon()
    {
        return this._semicolon_;
    }

    public void setSemicolon(TSemicolon node)
    {
        if(this._semicolon_ != null)
        {
            this._semicolon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._semicolon_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._listedecvar_)
            + toString(this._semicolon_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._listedecvar_ == child)
        {
            this._listedecvar_ = null;
            return;
        }

        if(this._semicolon_ == child)
        {
            this._semicolon_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._listedecvar_ == oldChild)
        {
            setListedecvar((PListedecvar) newChild);
            return;
        }

        if(this._semicolon_ == oldChild)
        {
            setSemicolon((TSemicolon) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
