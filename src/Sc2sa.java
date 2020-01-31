import lcompil.analysis.DepthFirstAdapter;
import lcompil.node.Switch;
import sa.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    public void caseSaExpAdd (SaExpAdd node) {
        SaExp op1 = null;
        SaExp op2 = null;
        node.getOp1().accept(this);
    }
}
