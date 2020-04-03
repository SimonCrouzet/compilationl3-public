import sa.*;
import ts.Ts;
import ts.TsItemFct;
import ts.TsItemVar;

public class Sa2ts extends SaDepthFirstVisitor {
    enum Context {LOCAL, PARAM, GLOBAL}
    private Ts tableGlobale;
    private Ts tableLocaleCourante = null;
    private Context context;

    public Sa2ts(SaNode saRoot) {
        this.tableGlobale = new Ts();
        saRoot.accept(this);

        if (tableGlobale.getFct("main") == null)
            throw new IllegalStateException("No function main!");
        if (tableGlobale.getFct("main").getNbArgs() != 0)
            throw new IllegalStateException("Function main shouldn't have any arguments!");
    }

    @Override
    public Object visit(SaDecTab node) {
        // node.accept(this);

        // TODO: Statuer sur le cas des tableaux de 1 case
        if (node.getTaille()<2)
            throw new IllegalCallerException("Array declared isn't enough large!");

        if (context.equals(Context.LOCAL)) {
            if (tableLocaleCourante.getVar(node.getNom()) != null)
                throw new IllegalCallerException("Tab " + node.getNom() + " already exists!");
            if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local tab " + node.getNom() + " will cover global tab.");
            tableLocaleCourante.addVar(node.getNom(), node.getTaille());
        }
        else if (context.equals(Context.PARAM)) {
            /*if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local param " + node.getNom() + " will cover global var.");*/
            tableLocaleCourante.addParam(node.getNom());
        }
        else if (context.equals(Context.GLOBAL)) {
            if (tableGlobale.getVar(node.getNom()) != null)
                throw new IllegalCallerException("Global tab " + node.getNom() + " already exists!");
            tableGlobale.addVar(node.getNom(), node.getTaille());
        }
        return null;
    }

    @Override
    public Object visit(SaDecFonc node) {
        if (tableGlobale.getFct(node.getNom()) != null)
            throw new IllegalCallerException("Function " + node.getNom() + " is already defined!");

        tableLocaleCourante = node.tsItem.getTable();

        context = Context.PARAM;
        if(node.getParametres() != null) node.getParametres().accept(this);

        context = Context.LOCAL;
        if(node.getVariable() != null) node.getVariable().accept(this);
        node.getCorps().accept(this);

        context = Context.GLOBAL;
        this.tableGlobale.addFct(node.getNom(), tableLocaleCourante.nbArg(), tableLocaleCourante, node);
        return null;
    }

    @Override
    public Object visit(SaDecVar node) {
        // node.accept(this);
        if (context.equals(Context.LOCAL)) {
            if (tableLocaleCourante.getVar(node.getNom()) != null)
                throw new IllegalCallerException("Var " + node.getNom() + " already exists!");
            if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local var " + node.getNom() + " will cover global var.");
            tableLocaleCourante.addVar(node.getNom(), 1);
        }
        else if (context.equals(Context.PARAM)) {
            /*if (tableGlobale.getVar(node.getNom()) != null)
                System.out.println("Warning: Local param " + node.getNom() + " will cover global var.");*/
            tableLocaleCourante.addParam(node.getNom());
        }
        else if (context.equals(Context.GLOBAL)) {
            if (tableGlobale.getVar(node.getNom()) != null)
                throw new IllegalCallerException("Global var " + node.getNom() + " already exists!");
            tableGlobale.addVar(node.getNom(), 1);
        }
        return null;
    }

    @Override
    public Object visit(SaVarSimple node) {
        // node.accept(this);
        if (context.equals(Context.LOCAL)) {
            TsItemVar varSimple = tableLocaleCourante.getVar(node.getNom());

            if (varSimple == null) {
                varSimple = tableGlobale.getVar(node.getNom());

                if (varSimple == null)
                    throw new IllegalCallerException("Var " + node.getNom() + " doesn't exist!");
            }
            node.tsItem = varSimple;
        }
        /*else if (context.equals(Context.PARAM)) {
            //TODO: A Vérifier
            TsItemVar varSimple = tableLocaleCourante.getVar(node.getNom());

            if (varSimple == null || !varSimple.isParam)
                throw new IllegalCallerException("Param " + node.getNom() + " doesn't exist!");

            node.tsItem = varSimple;
        }
        else if (context.equals(Context.GLOBAL)) {*/
        else{
            TsItemVar varSimple = tableGlobale.getVar(node.getNom());
            if (varSimple == null)
                throw new IllegalCallerException("Global var " + node.getNom() + " doesn't exist!");

            node.tsItem = varSimple;
        }
        if (node.tsItem.getTaille() > 1)
            throw new IllegalCallerException("Wrong call to indexed variable.");

        return null;
    }

    @Override
    public Object visit(SaAppel node) {
        // node.accept(this);
        TsItemFct fct = tableGlobale.getFct(node.getNom());

        if (fct == null)
            throw new IllegalCallerException("Function " + node.getNom() + "doesn't exist!");

        node.tsItem = fct;

        if (node.getArguments() != null)
            node.getArguments().accept(this);

        if (node.tsItem.getNbArgs() != node.getArguments().length())
            throw new IllegalStateException("Conflict during arguments exploration!");

        return null;
    }

    @Override
    public Object visit(SaVarIndicee node) {
        // node.accept(this);
        // TODO: Statuer sur le cas des tableaux de 1 case
        // TODO: Statuer si besoin de vérifier la véracité de l'indice (et si oui, comment ?)

        if (context.equals(Context.LOCAL)) {
            TsItemVar varIndicee = tableLocaleCourante.getVar(node.getNom());

            if (varIndicee == null) {
                varIndicee = tableGlobale.getVar(node.getNom());

                if (varIndicee == null)
                    throw new IllegalCallerException("Var " + node.getNom() + " doesn't exist!");
            }
            node.tsItem = varIndicee;
        }
        /*else if (context.equals(Context.PARAM)) {
            //TODO: A Vérifier
            TsItemVar varIndicee = tableLocaleCourante.getVar(node.getNom());

            if (varIndicee == null)
                throw new IllegalCallerException("Param " + node.getNom() + " doesn't exist!");

            node.tsItem = varIndicee;
        }
        else if (context.equals(Context.GLOBAL)) {*/
        else{
            TsItemVar varIndicee = tableGlobale.getVar(node.getNom());
            if (varIndicee == null)
                throw new IllegalCallerException("Global var " + node.getNom() + " doesn't exist!");

            node.tsItem = varIndicee;
        }

        if (node.tsItem.getTaille() < 1)
            throw new IllegalCallerException("Wrong call to indexed variable.");

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
}
