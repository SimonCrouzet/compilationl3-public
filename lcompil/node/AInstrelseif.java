/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class AInstrelseif extends PInstrelseif
{
    private TElseif _elseif_;
    private PInstrbloc _instrbloc_;

    public AInstrelseif()
    {
        // Constructor
    }

    public AInstrelseif(
        @SuppressWarnings("hiding") TElseif _elseif_,
        @SuppressWarnings("hiding") PInstrbloc _instrbloc_)
    {
        // Constructor
        setElseif(_elseif_);

        setInstrbloc(_instrbloc_);

    }

    @Override
    public Object clone()
    {
        return new AInstrelseif(
            cloneNode(this._elseif_),
            cloneNode(this._instrbloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstrelseif(this);
    }

    public TElseif getElseif()
    {
        return this._elseif_;
    }

    public void setElseif(TElseif node)
    {
        if(this._elseif_ != null)
        {
            this._elseif_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._elseif_ = node;
    }

    public PInstrbloc getInstrbloc()
    {
        return this._instrbloc_;
    }

    public void setInstrbloc(PInstrbloc node)
    {
        if(this._instrbloc_ != null)
        {
            this._instrbloc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instrbloc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._elseif_)
            + toString(this._instrbloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._elseif_ == child)
        {
            this._elseif_ = null;
            return;
        }

        if(this._instrbloc_ == child)
        {
            this._instrbloc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._elseif_ == oldChild)
        {
            setElseif((TElseif) newChild);
            return;
        }

        if(this._instrbloc_ == oldChild)
        {
            setInstrbloc((PInstrbloc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
