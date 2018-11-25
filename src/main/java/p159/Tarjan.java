package p159;

import java.util.HashMap;
import java.util.LinkedList;

public class Tarjan {
    private HashMap<Node, LinkedList<Node>> queryMap;
    private HashMap<Node, LinkedList<Integer>> indexMap;
    private HashMap<Node, Node> ancestorMap;
    private DisjointSets sets;

    public Tarjan() {
        queryMap = new HashMap<Node, LinkedList<Node>>();
        indexMap = new HashMap<Node, LinkedList<Integer>>();
        ancestorMap = new HashMap<Node, Node>();
        sets = new DisjointSets();
    }

    public Node[] query(Node head, Query[] ques) {
        Node[] ans = new Node[ques.length];
        setQueries(ques, ans);
        sets.makeSets(head);
        setAnswers(head, ans);
        return ans;
    }

    private void setQueries(Query[] ques, Node[] ans) {
        Node o1 = null;
        Node o2 = null;
        for (int i = 0; i != ans.length; ++i) {
            o1 = ques[i].o1;
            o2 = ques[i].o2;
            if (o1 == o2 || null == o1 || null == o2) {
                ans[i] = o1 != null ? o1 : o2;
            } else {
                if (!queryMap.containsKey(o1)) {
                    queryMap.put(o1, new LinkedList<Node>());
                    indexMap.put(o1, new LinkedList<Integer>());
                }
                if (!queryMap.containsKey(o2)) {
                    queryMap.put(o2, new LinkedList<Node>());
                    indexMap.put(o2, new LinkedList<Integer>());
                }
                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i);
                queryMap.get(o2).add(o1);
                indexMap.get(o2).add(i);
            }
        }
    }

    private void setAnswers(Node head, Node[] ans) {
        if (null == head) {
            return;
        }
        setAnswers(head.left, ans);
        sets.union(head.left, head);
        ancestorMap.put(sets.findFather(head), head);

        setAnswers(head.right, ans);
        sets.union(head.right, head);
        ancestorMap.put(sets.findFather(head), head);

        LinkedList<Node> nList = queryMap.get(head);
        LinkedList<Integer> iList = indexMap.get(head);

        Node node = null;
        Node nodeFather = null;
        int index = 0;
        while (null != nList && !nList.isEmpty()) {
            node = nList.poll();
            index = iList.poll();
            nodeFather = sets.findFather(node);
            if (ancestorMap.containsKey(nodeFather)) {
                ans[index] = ancestorMap.get(nodeFather);
            }
        }
    }

}
