/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class AMoreVariablesListedecvarChainee extends PListedecvarChainee
{
    private TComma _comma_;
    private PDecvar _decvar_;
    private PListedecvarChainee _listedecvarChainee_;

    public AMoreVariablesListedecvarChainee()
    {
        // Constructor
    }

    public AMoreVariablesListedecvarChainee(
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PDecvar _decvar_,
        @SuppressWarnings("hiding") PListedecvarChainee _listedecvarChainee_)
    {
        // Constructor
        setComma(_comma_);

        setDecvar(_decvar_);

        setListedecvarChainee(_listedecvarChainee_);

    }

    @Override
    public Object clone()
    {
        return new AMoreVariablesListedecvarChainee(
            cloneNode(this._comma_),
            cloneNode(this._decvar_),
            cloneNode(this._listedecvarChainee_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMoreVariablesListedecvarChainee(this);
    }

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
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
            + toString(this._comma_)
            + toString(this._decvar_)
            + toString(this._listedecvarChainee_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

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
        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

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
