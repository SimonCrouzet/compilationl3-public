/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class ADeclarationVariablesMultiplesListedecvar extends PListedecvar
{
    private PDecvar _decvar_;
    private PListedecvarChainee _listedecvarChainee_;

    public ADeclarationVariablesMultiplesListedecvar()
    {
        // Constructor
    }

    public ADeclarationVariablesMultiplesListedecvar(
        @SuppressWarnings("hiding") PDecvar _decvar_,
        @SuppressWarnings("hiding") PListedecvarChainee _listedecvarChainee_)
    {
        // Constructor
        setDecvar(_decvar_);

        setListedecvarChainee(_listedecvarChainee_);

    }

    @Override
    public Object clone()
    {
        return new ADeclarationVariablesMultiplesListedecvar(
            cloneNode(this._decvar_),
            cloneNode(this._listedecvarChainee_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADeclarationVariablesMultiplesListedecvar(this);
    }

    public PDecvar getDecvar()
    {
        return this._decvar_;
    }

    public void setDecvar(PDecvar node)
    {
        if(this._decvar_ != null)
        {
            this._decvar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._decvar_ = node;
    }

    public PListedecvarChainee getListedecvarChainee()
    {
        return this._listedecvarChainee_;
    }

    public void setListedecvarChainee(PListedecvarChainee node)
    {
        if(this._listedecvarChainee_ != null)
        {
            this._listedecvarChainee_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listedecvarChainee_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._decvar_)
            + toString(this._listedecvarChainee_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._decvar_ == child)
        {
            this._decvar_ = null;
            return;
        }

        if(this._listedecvarChainee_ == child)
        {
            this._listedecvarChainee_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._decvar_ == oldChild)
        {
            setDecvar((PDecvar) newChild);
            return;
        }

        if(this._listedecvarChainee_ == oldChild)
        {
            setListedecvarChainee((PListedecvarChainee) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
