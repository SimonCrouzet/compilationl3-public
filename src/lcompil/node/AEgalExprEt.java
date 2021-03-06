/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class AEgalExprEt extends PExprEt
{
    private PExprEt _exprEt_;
    private TEquals _equals_;
    private PExprEgal _exprEgal_;

    public AEgalExprEt()
    {
        // Constructor
    }

    public AEgalExprEt(
        @SuppressWarnings("hiding") PExprEt _exprEt_,
        @SuppressWarnings("hiding") TEquals _equals_,
        @SuppressWarnings("hiding") PExprEgal _exprEgal_)
    {
        // Constructor
        setExprEt(_exprEt_);

        setEquals(_equals_);

        setExprEgal(_exprEgal_);

    }

    @Override
    public Object clone()
    {
        return new AEgalExprEt(
            cloneNode(this._exprEt_),
            cloneNode(this._equals_),
            cloneNode(this._exprEgal_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAEgalExprEt(this);
    }

    public PExprEt getExprEt()
    {
        return this._exprEt_;
    }

    public void setExprEt(PExprEt node)
    {
        if(this._exprEt_ != null)
        {
            this._exprEt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exprEt_ = node;
    }

    public TEquals getEquals()
    {
        return this._equals_;
    }

    public void setEquals(TEquals node)
    {
        if(this._equals_ != null)
        {
            this._equals_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._equals_ = node;
    }

    public PExprEgal getExprEgal()
    {
        return this._exprEgal_;
    }

    public void setExprEgal(PExprEgal node)
    {
        if(this._exprEgal_ != null)
        {
            this._exprEgal_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exprEgal_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._exprEt_)
            + toString(this._equals_)
            + toString(this._exprEgal_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exprEt_ == child)
        {
            this._exprEt_ = null;
            return;
        }

        if(this._equals_ == child)
        {
            this._equals_ = null;
            return;
        }

        if(this._exprEgal_ == child)
        {
            this._exprEgal_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._exprEt_ == oldChild)
        {
            setExprEt((PExprEt) newChild);
            return;
        }

        if(this._equals_ == oldChild)
        {
            setEquals((TEquals) newChild);
            return;
        }

        if(this._exprEgal_ == oldChild)
        {
            setExprEgal((PExprEgal) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
