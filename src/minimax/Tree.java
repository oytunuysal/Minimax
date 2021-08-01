
package minimax;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree {

    public static int counter;
    public int index;
    public int data;
    Tree parent;
    public List<Tree> children;

    public Tree(int data) {
        this.data = data;
        this.children = new ArrayList<Tree>();
    }

    public Tree addChild(int child) { 
        Tree childNode = new Tree(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    public void addRandomChildren(int childCount) { 
        int rand;
        for (int i = 0; i < childCount; i++) {
            rand = (int) (Math.random() * 1000) + 1;
            //System.out.println(rand);
            this.addChild(rand);
            this.children.get(i).index = i + 1;
            counter++;
        }
    }

    public void crateLayers(int layer) {
        if (layer == 1) {
            return;
        }
        int count = 0;
        for (Iterator<Tree> iterator = children.iterator(); iterator.hasNext();) {
            count++;
            Tree next = iterator.next();
            //System.out.println("Layer:" + layer + "Node:" + count + "Data:" + next.data + "Children List: ");
            next.addRandomChildren(5);
            next.crateLayers(layer - 1);
        }

    }

    public void print(int level) { 
        for (int i = 1; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(data);
        for (Tree child : children) {
            child.print(level + 1);
        }
    }

}
