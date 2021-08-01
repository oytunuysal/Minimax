package minimax;

import java.util.Scanner;

public class Minimax {

    public static void main(String[] args) {
        Tree treeRoot = new Tree(0);
        treeRoot.addRandomChildren(5);
        treeRoot.crateLayers(4);
        System.out.println("Total number of nodes: " + treeRoot.counter);
        minimax(treeRoot, 4, 0);
        System.out.println("Final Point: " + treeRoot.data);
        System.out.println("Path: A" + treeRoot.index);
        treeRoot.print(1);

        game(treeRoot);

    }

    public static void minimax(Tree node, int currentDepth, int player) {
        int bestMove = 0;
        int bestMoveIndex = 0;
        int totalChild = node.children.size();

        // private static String index = "A";
        //0: player1 - max
        //1: player2 - min
        if (currentDepth == 0 || node.children.isEmpty()) {
            return;
        }
        for (int i = 0; i < totalChild; i++) {
            Tree next = node.children.get(i);

            //System.out.println("Layer:" + currentDepth + "Count:" + node.index + "Data:" + next.data);
            if (currentDepth != 1) {
                minimax(next, currentDepth - 1, (player + 1) % 2);

            }
            if (i == 0) {
                bestMove = next.data;
                bestMoveIndex = next.index;
            }
            if (player == 0) {
                if (next.data > bestMove) {
                    bestMove = next.data;
                    bestMoveIndex = next.index;
                }
            } else {
                if (next.data < bestMove) {
                    bestMove = next.data;
                    bestMoveIndex = next.index;
                }
            }

        }

        //Parent node a data ve indeks aktarılır.
        node.data = bestMove;
        node.index = ((node.index) * ((int) Math.pow(10, currentDepth))) + bestMoveIndex;

        //System.out.println("Layer: " + currentDepth + "BestMove: " + node.data);
        //System.out.println("Layer: " + currentDepth + "BestMoveIndex: " + node.index);
    }

    public static void game(Tree node) { //oyunu oynamak için
        Scanner sc = new Scanner(System.in);
        Tree tempTree;
        int tempCounter = 1;
        boolean cont = true;
        do {
            System.out.println("Would you like to play the game? (1:Y 2:N)");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Your goal is to choose moves with max points.");
                    tempTree = node;
                    for (int i = 0; i < 2; i++) {
                        for (Tree child : tempTree.children) {
                            System.out.println(tempCounter + ". " + child.data);
                            tempCounter++;
                        }
                        System.out.print("Enter your choice:");

                        tempTree = tempTree.children.get(sc.nextInt() - 1);
                        System.out.println("Opponent selected: " + (String.valueOf(tempTree.index).charAt(1) - '0'));
                        tempTree = tempTree.children.get(String.valueOf(tempTree.index).charAt(1) - '0' - 1);

                        tempCounter = 1;
                    }
                    System.out.println("Your final score: " + tempTree.data);
                    System.out.println("Best possible score was: " + node.data);
                    break;
                case 2:
                    cont = false;
                    break;
            }

        } while (cont);
    }

}
