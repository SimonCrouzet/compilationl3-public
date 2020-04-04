import sa.*;
import ts.Ts;
import ts.TsItemFct;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor<Void> {
    enum Context {LOCAL, PARAM, GLOBAL}
    private Ts tableGlobale;
    private Ts tableLocaleCourante = null;
    private Context context;

    public Sa2ts(SaNode saRoot) {
        this.tableGlobale = new Ts();
        context = Context.GLOBAL;
        visit((SaProg) saRoot);

        if (tableGlobale.getFct("main") == null)
            throw new IllegalStateException("No function main!");
        if (tableGlobale.getFct("main").getNbArgs() != 0)
            throw new IllegalStateException("Function main shouldn't have any arguments!");
    }


    @Override
    public Void visit(SaDecVar node) {
        if (context.equals(Context.LOCAL)) {
            if (tableLocaleCourante.getVar(node.getNom()) != null)
                throw new TsException("Var " + node.getNom() + " already exists!");
            if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local var " + node.getNom() + " will cover global var.");
            node.tsItem = tableLocaleCourante.addVar(node.getNom(), 1);
        }
        else if (context.equals(Context.PARAM)) {
            /*if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local param " + node.getNom() + " will cover global var.");*/
            node.tsItem = tableLocaleCourante.addParam(node.getNom());
        }
        else if (context.equals(Context.GLOBAL)) {
            if (tableGlobale.getVar(node.getNom()) != null)
                throw new TsException("Global var " + node.getNom() + " already exists!");
            node.tsItem = tableGlobale.addVar(node.getNom(), 1);
        }
        return null;
    }

    @Override
    public Void visit(SaDecTab node) {
        // TODO: Statuer sur le cas des tableaux de 1 case
        if (node.getTaille()<2)
            throw new TsException("Array declared isn't enough large!");

        if (context.equals(Context.LOCAL)) {
            if (tableLocaleCourante.getVar(node.getNom()) != null)
                throw new TsException("Tab " + node.getNom() + " already exists!");
            if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local tab " + node.getNom() + " will cover global tab.");
            node.tsItem = tableLocaleCourante.addVar(node.getNom(), node.getTaille());
        }
        else if (context.equals(Context.PARAM)) {
            /*if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local param " + node.getNom() + " will cover global var.");*/
            node.tsItem = tableLocaleCourante.addParam(node.getNom());
        }
        else if (context.equals(Context.GLOBAL)) {
            if (tableGlobale.getVar(node.getNom()) != null)
                throw new TsException("Global tab " + node.getNom() + " already exists!");
            node.tsItem = tableGlobale.addVar(node.getNom(), node.getTaille());
        }
        return null;
    }

    @Override
    public Void visit(SaDecFonc node) {
        if (tableGlobale.getFct(node.getNom()) != null)
            throw new TsException("Function " + node.getNom() + " is already defined!");
        tableLocaleCourante = new Ts();

        context = Context.PARAM;
        if(node.getParametres() != null) node.getParametres().accept(this);

        context = Context.LOCAL;
        if(node.getVariable() != null) node.getVariable().accept(this);
        node.getCorps().accept(this);

        context = Context.GLOBAL;
        node.tsItem = tableGlobale.addFct(node.getNom(), tableLocaleCourante.nbArg(), tableLocaleCourante, node);
        return null;
    }

    @Override
    public Void visit(SaVarSimple node) {
        if (context.equals(Context.LOCAL)) {
            TsItemVar varSimple = tableLocaleCourante.getVar(node.getNom());

            if (varSimple == null) {
                varSimple = tableGlobale.getVar(node.getNom());

                if (varSimple == null)
                    throw new TsException("Var " + node.getNom() + " doesn't exist!");
            }
            node.tsItem = varSimple;
        }
        else{
            TsItemVar varSimple = tableGlobale.getVar(node.getNom());
            if (varSimple == null)
                throw new TsException("Global var " + node.getNom() + " doesn't exist!");

            node.tsItem = varSimple;
        }
        if (node.tsItem.getTaille() > 1)
            throw new TsException("Wrong call to indexed variable.");

        return null;
    }

    @Override
    public Void visit(SaVarIndicee node) {
        if (context.equals(Context.LOCAL)) {
            TsItemVar varIndicee = tableLocaleCourante.getVar(node.getNom());

            if (varIndicee == null) {
                varIndicee = tableGlobale.getVar(node.getNom());

                if (varIndicee == null)
                    throw new TsException("Var " + node.getNom() + " doesn't exist!");
            }
            node.tsItem = varIndicee;
        }
        else{
            TsItemVar varIndicee = tableGlobale.getVar(node.getNom());
            if (varIndicee == null)
                throw new TsException("Global var " + node.getNom() + " doesn't exist!");

            node.tsItem = varIndicee;
        }

        // TODO: Statuer sur le cas des tableaux de 1 case
        if (node.tsItem.getTaille() < 1)
            throw new TsException("Wrong call to indexed variable.");

        return null;
    }

    @Override
    public Void visit(SaAppel node) {
        TsItemFct fct = tableGlobale.getFct(node.getNom());

        if (fct == null)
            throw new TsException("Function " + node.getNom() + "doesn't exist!");

        node.tsItem = fct;

        if (node.getArguments() != null)
            node.getArguments().accept(this);

        if (node.getArguments() != null && node.tsItem.getNbArgs() != node.getArguments().length())
            throw new IllegalStateException("Conflict during arguments exploration!");

        return null;
    }

    public Ts getTableGlobale() {
        return tableGlobale;
    }

    public Ts getTableLocaleCourante() {
        return tableLocaleCourante;
    }

    public Context getContext() {
        return context;
    }

    static class TsException extends RuntimeException{
        TsException(String error) {
            super(error);
        }
    }
}
