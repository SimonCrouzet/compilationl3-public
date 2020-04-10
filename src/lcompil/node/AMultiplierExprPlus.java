/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class AMultiplierExprPlus extends PExprPlus
{
    private PExprPlus _exprPlus_;
    private TMult _mult_;
    private PExprMult _exprMult_;

    public AMultiplierExprPlus()
    {
        // Constructor
    }

    public AMultiplierExprPlus(
        @SuppressWarnings("hiding") PExprPlus _exprPlus_,
        @SuppressWarnings("hiding") TMult _mult_,
        @SuppressWarnings("hiding") PExprMult _exprMult_)
    {
        // Constructor
        setExprPlus(_exprPlus_);

        setMult(_mult_);

        setExprMult(_exprMult_);

    }

    @Override
    public Object clone()
    {
        return new AMultiplierExprPlus(
            cloneNode(this._exprPlus_),
            cloneNode(this._mult_),
            cloneNode(this._exprMult_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMultiplierExprPlus(this);
    }

    public PExprPlus getExprPlus()
    {
        return this._exprPlus_;
    }

    public void setExprPlus(PExprPlus node)
    {
        if(this._exprPlus_ != null)
        {
            this._exprPlus_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exprPlus_ = node;
    }

    public TMult getMult()
    {
        return this._mult_;
    }

    public void setMult(TMult node)
    {
        if(this._mult_ != null)
        {
            this._mult_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._mult_ = node;
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
            + toString(this._exprPlus_)
            + toString(this._mult_)
            + toString(this._exprMult_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._exprPlus_ == child)
        {
            this._exprPlus_ = null;
            return;
        }

        if(this._mult_ == child)
        {
            this._mult_ = null;
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
        if(this._exprPlus_ == oldChild)
        {
            setExprPlus((PExprPlus) newChild);
            return;
        }

        if(this._mult_ == oldChild)
        {
            setMult((TMult) newChild);
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