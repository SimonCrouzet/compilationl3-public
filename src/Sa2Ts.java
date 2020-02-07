import sa.*;
import ts.Ts;

public class Sa2Ts extends SaDepthFirstVisitor {
    private Ts tableGlobale;

    public Sa2Ts() {
        this.tableGlobale = new Ts();
    }

    @Override
    public Object visit(SaDecTab node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaDecFonc node) {
        tableGlobale.ad
        return super.visit(node);
    }

    @Override
    public Object visit(SaDecVar node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaVarSimple node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaAppel node) {
        return super.visit(node);
    }

    @Override
    public Object visit(SaVarIndicee node) {
        return super.visit(node);
    }
}
