package p159;

import java.util.HashMap;

public class DisjointSets {
    public HashMap<Node, Node> fatherMap;
    public HashMap<Node, Integer> rankMap;

    public DisjointSets() {
        fatherMap = new HashMap<Node, Node>();
        rankMap = new HashMap<Node, Integer>();
    }

    public void makeSets(Node head) {
        fatherMap.clear();
        rankMap.clear();
        preOrderMake(head);
    }

    private void preOrderMake(Node head) {
        if (null == head) {
            return;
        }
        fatherMap.put(head, head);
        rankMap.put(head, 0);
        preOrderMake(head.left);
        preOrderMake(head.right);
    }

    public Node findFather(Node n) {
        Node father = fatherMap.get(n);
        if (father != n) {
            father = findFather(father);
        }
        fatherMap.put(n, father);
        return father;
    }

    public void union(Node a, Node b) {
        if (null == a || null == b) {
            return;
        }
        Node aFather = findFather(a);
        Node bFather = findFather(b);
        if (aFather != bFather) {
            int aFrank= rankMap.get(aFather);
            int bFrank= rankMap.get(bFather);
            if (aFrank < bFrank) {
                fatherMap.put(aFather, bFather);
            } else if (aFrank > bFrank) {
                fatherMap.put(bFather, aFather);
            } else {
                fatherMap.put(bFather, aFather);
                rankMap.put(aFather, aFrank +1);
            }
        }
    }
}
