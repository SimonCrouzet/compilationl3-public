/* This file was generated by SableCC (http://www.sablecc.org/). */

package lcompil.node;

import lcompil.analysis.*;

@SuppressWarnings("nls")
public final class ADecvarinstrDecfonc extends PDecfonc
{
    private TIdentificator _identificator_;
    private PListeparam _listeparam_;
    private POptdecvar _optdecvar_;
    private PInstrbloc _instrbloc_;

    public ADecvarinstrDecfonc()
    {
        // Constructor
    }

    public ADecvarinstrDecfonc(
        @SuppressWarnings("hiding") TIdentificator _identificator_,
        @SuppressWarnings("hiding") PListeparam _listeparam_,
        @SuppressWarnings("hiding") POptdecvar _optdecvar_,
        @SuppressWarnings("hiding") PInstrbloc _instrbloc_)
    {
        // Constructor
        setIdentificator(_identificator_);

        setListeparam(_listeparam_);

        setOptdecvar(_optdecvar_);

        setInstrbloc(_instrbloc_);

    }

    @Override
    public Object clone()
    {
        return new ADecvarinstrDecfonc(
            cloneNode(this._identificator_),
            cloneNode(this._listeparam_),
            cloneNode(this._optdecvar_),
            cloneNode(this._instrbloc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseADecvarinstrDecfonc(this);
    }

    public TIdentificator getIdentificator()
    {
        return this._identificator_;
    }

    public void setIdentificator(TIdentificator node)
    {
        if(this._identificator_ != null)
        {
            this._identificator_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identificator_ = node;
    }

    public PListeparam getListeparam()
    {
        return this._listeparam_;
    }

    public void setListeparam(PListeparam node)
    {
        if(this._listeparam_ != null)
        {
            this._listeparam_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listeparam_ = node;
    }

    public POptdecvar getOptdecvar()
    {
        return this._optdecvar_;
    }

    public void setOptdecvar(POptdecvar node)
    {
        if(this._optdecvar_ != null)
        {
            this._optdecvar_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._optdecvar_ = node;
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
            + toString(this._identificator_)
            + toString(this._listeparam_)
            + toString(this._optdecvar_)
            + toString(this._instrbloc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identificator_ == child)
        {
            this._identificator_ = null;
            return;
        }

        if(this._listeparam_ == child)
        {
            this._listeparam_ = null;
            return;
        }

        if(this._optdecvar_ == child)
        {
            this._optdecvar_ = null;
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
        if(this._identificator_ == oldChild)
        {
            setIdentificator((TIdentificator) newChild);
            return;
        }

        if(this._listeparam_ == oldChild)
        {
            setListeparam((PListeparam) newChild);
            return;
        }

        if(this._optdecvar_ == oldChild)
        {
            setOptdecvar((POptdecvar) newChild);
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
