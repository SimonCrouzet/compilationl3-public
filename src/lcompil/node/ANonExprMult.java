/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class ANonExprMult extends PExprMult
{
    private TNo _no_;
    private PExprMult _exprMult_;

    public ANonExprMult()
    {
        // Constructor
    }

    public ANonExprMult(
        @SuppressWarnings("hiding") TNo _no_,
        @SuppressWarnings("hiding") PExprMult _exprMult_)
    {
        // Constructor
        setNo(_no_);

        setExprMult(_exprMult_);

    }

    @Override
    public Object clone()
    {
        return new ANonExprMult(
            cloneNode(this._no_),
            cloneNode(this._exprMult_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANonExprMult(this);
    }

    public TNo getNo()
    {
        return this._no_;
    }

    public void setNo(TNo node)
    {
        if(this._no_ != null)
        {
            this._no_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._no_ = node;
    }

    public PExprMult getExprMult()
    {
        return this._exprMult_;
    }

    public void setExprMult(PExprMult node)
    {
        if(this._exprMult_ != null)
        {
            this._exprMult_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exprMult_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._no_)
            + toString(this._exprMult_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._no_ == child)
        {
            this._no_ = null;
            return;
        }

        if(this._exprMult_ == child)
        {
            this._exprMult_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._no_ == oldChild)
        {
            setNo((TNo) newChild);
            return;
        }

        if(this._exprMult_ == oldChild)
        {
            setExprMult((PExprMult) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
