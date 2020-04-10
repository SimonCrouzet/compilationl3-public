package util.graph;

import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class ColorGraph {
    public  Graph          G;
    public  int            R;
    public  int            K;
    private int            N;
    private Stack<Integer> pile;
    public  IntSet         removed;
    public  IntSet         spill;
    public  int[]          couleur;
    public  Node[]         int2Node;
    static  int            NOCOLOR = -1;

    /**
     * Constructor of the colored graph
     * @param G the graph referenced
     * @param K the number of colors available
     * @param phi the colors presets by the pre-NASM code
     */
    public ColorGraph(Graph G, int K, int[] phi){
        this.G       = G;
        this.K       = K;
        pile         = new Stack<Integer>();
        R            = G.nodeCount();
        couleur      = new int[R];
        removed      = new IntSet(R);
        spill        = new IntSet(R);
        int2Node     = G.nodeArray();
        for(int v=0; v < R; v++){
            int preColor = phi[v];
            if(preColor >= 0 && preColor < K)
            couleur[v] = phi[v];
            else
            couleur[v] = NOCOLOR;
        }
        coloration();
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * Selection algorithm, according to the pseudocode provided
     */
    public void selection()
    {
        while (!pile.isEmpty()) {
            int node = pile.pop();
            if (couleursVoisins(node).getSize() != K)
                couleur[node] = choisisCouleur(couleursVoisins(node));
            // else ?
        }
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de t */
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * Explore the colors already required by the neighbours
     * @return the IntSet of non-available colors
     */
    public IntSet couleursVoisins(int t)
    {
        IntSet colorOfNeighbours = new IntSet(K);
        NodeList succ = int2Node[t].succ();
        while (succ != null) {
            int color = couleur[succ.head.mykey];
            if (color != NOCOLOR && !colorOfNeighbours.isMember(color))
                colorOfNeighbours.add(color);
            succ = succ.tail;
        }
        return colorOfNeighbours;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* recherche une couleur absente de colorSet */
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * Choose the color within the available ones
     * @return the color chosen, or NOCOLOR if any color available
     */
    public int choisisCouleur(IntSet colorSet)
    {
        for (int i=0 ; i<K ; i++) {
            if (!colorSet.isMember(i))
                return i;
        }
        return NOCOLOR;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet t */
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * Count and return the number of neighbours
     */
    public int nbVoisins(int t)
    {
        int count = 0;
        NodeList succ = int2Node[t].succ();
        while (succ != null) {
            if (!(removed.isMember(succ.head.mykey)))
                ++count;
            succ = succ.tail;
        }
        return count;
    }

    /**
     * Count and return the precolored registers
     */
    private int nbOfPrecoloredRegisters() {
        int count = 0;
        for (int color : couleur)
            if (color != NOCOLOR)
                count++;

        return count;
    }
    /*-------------------------------------------------------------------------------------------------------------*/
    /* simplifie le graphe d'interférence g                                                                        */
    /* la simplification consiste à enlever du graphe les temporaires qui ont moins de k voisins                   */
    /* et à les mettre dans une pile                                                                               */
    /* à la fin du processus, le graphe peut ne pas être vide, il s'agit des temporaires qui ont au moins k voisin */
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * Simplification algorithm, according to the pseudocode provided
     * @return nb of nodes still in the graph (DEPRECATED)
     */
    public int simplification()
    {
        int nbNodesToCompute = R - nbOfPrecoloredRegisters();
        boolean modif = true;
        while (pile.size() != N && modif) {
            modif = false;
            for (int i=0 ; i < R ; i++) {
                if (removed.isMember(i))
                    continue;
                if (nbVoisins(i) < K && couleur[i] == NOCOLOR) {
                    pile.push(i);
                    removed.add(i);
                    modif = true;
                }
            }
        }
        return G.nodeCount() - removed.getSize();
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * List and return the node to compute (DEPRECATED)
     */
    private Queue<Integer> nodesToCompute() {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i=0 ; i<R ; i++)
            if (!(removed.isMember(i)))
                queue.add(i);
        return queue;
    }

    /**
     * Pick and return the next node to compute
     */
    private int chooseNode() {
        for (int i=0 ; i<R ; i++)
            if ((!pile.contains(i)))
                return i;
        return -1;
    }

    /**
     * Spillover algorithm, according to the pseudocode provided
     */
    public void debordement()
    {
        if (!spill.isEmpty())
            throw new IllegalStateException("Wrong call to debordement()");
        while (pile.size() != R) {
            int node = chooseNode();
            pile.push(node);
            removed.add(node);
            spill.add(node);
            simplification();
        }
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    /**
     * Coloration of the registers
     */
    public void coloration()
    {
        N = R - nbOfPrecoloredRegisters();
        this.simplification();
        this.debordement();
        this.selection();
    }

    void affiche()
    {
	System.out.println("vertex\tcolor");
	for(int i = 0; i < R; i++){
	    System.out.println(i + "\t" + couleur[i]);
	}
    }
    
    

}
