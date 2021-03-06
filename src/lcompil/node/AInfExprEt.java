/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class AInfExprEt extends PExprEt
{
    private PExprEt _exprEt_;
    private TInferior _inferior_;
    private PExprEgal _exprEgal_;

    public AInfExprEt()
    {
        // Constructor
    }

    public AInfExprEt(
        @SuppressWarnings("hiding") PExprEt _exprEt_,
        @SuppressWarnings("hiding") TInferior _inferior_,
        @SuppressWarnings("hiding") PExprEgal _exprEgal_)
    {
        // Constructor
        setExprEt(_exprEt_);

        setInferior(_inferior_);

        setExprEgal(_exprEgal_);

    }

    @Override
    public Object clone()
    {
        return new AInfExprEt(
            cloneNode(this._exprEt_),
            cloneNode(this._inferior_),
            cloneNode(this._exprEgal_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInfExprEt(this);
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

    public TInferior getInferior()
    {
        return this._inferior_;
    }

    public void setInferior(TInferior node)
    {
        if(this._inferior_ != null)
        {
            this._inferior_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._inferior_ = node;
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
            + toString(this._inferior_)
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

        if(this._inferior_ == child)
        {
            this._inferior_ = null;
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

        if(this._inferior_ == oldChild)
        {
            setInferior((TInferior) newChild);
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
