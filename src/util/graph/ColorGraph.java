package util.graph;

import util.graph.*;
import util.intset.*;
import java.util.*;
import java.io.*;

public class ColorGraph {
    public  Graph          G;
    public  int            R;
    public  int            K;
    private Stack<Integer> pile;
    public  IntSet         removed;
    public  IntSet         spill;
    public  int[]          couleur;
    public  Node[]         int2Node;
    static  int            NOCOLOR = -1;

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
    }

    /*-------------------------------------------------------------------------------------------------------------*/
    /* associe une couleur à tous les sommets se trouvant dans la pile */
    /*-------------------------------------------------------------------------------------------------------------*/

    public void selection()
    {
        while (!pile.empty()) {
            int node = pile.pop();
            if (couleursVoisins(node).getSize() != K)
                couleur[node] = choisisCouleur(couleursVoisins(node));
            // else ?
        }
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* récupère les couleurs des voisins de t */
    /*-------------------------------------------------------------------------------------------------------------*/
    
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
    
    public int choisisCouleur(IntSet colorSet)
    {
        for (int i=0 ; i<K ; i++)
            if(!colorSet.isMember(i))
                return i;
        return NOCOLOR;
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /* calcule le nombre de voisins du sommet t */
    /*-------------------------------------------------------------------------------------------------------------*/
    
    public int nbVoisins(int t)
    {
        int count = 0;
        NodeList succ = int2Node[t].succ();
        while (succ != null) {
            if (removed.isMember(succ.head.mykey))
                ++count; // TODO: Check
            succ = succ.tail;
        }
        return count;
    }


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

    public int simplification()
    {
        pile = new Stack<Integer>();
        int nbNodesToCompute = R - nbOfPrecoloredRegisters();
        boolean modif = true;
        while (pile.size() != nbNodesToCompute && modif) {
            modif = false;
            for (int i=0 ; i < R ; i++) {
                if (nbVoisins(i) < K && couleur[i] == NOCOLOR) {
                    pile.push(i);
                    // TODO: Remove i from S ?
                    removed.add(i);
                    modif = true;
                }
            }
        }
        return G.nodeCount() - removed.getSize(); // return nb of nodes still in the graph
    }
    
    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/
    private Queue<Integer> nodesToCompute() {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i=0 ; i<R ; i++)
            if (!(removed.isMember(i)))
                queue.add(i);
        return queue;
    }

    public void debordement()
    {
        if (!spill.isEmpty())
            throw new IllegalStateException("Wrong call to debordement()");
        Queue<Integer> nodesToCompute = nodesToCompute();
        if (removed.getSize() + nodesToCompute.size() != R)
            throw new IllegalStateException("Wrong number of nodes");
        while (pile.size() != R) {
            int node = nodesToCompute.remove();
            pile.push(node);
            removed.add(node);
            spill.add(node);
            simplification();
        }
    }


    /*-------------------------------------------------------------------------------------------------------------*/
    /*-------------------------------------------------------------------------------------------------------------*/

    public void coloration()
    {
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
