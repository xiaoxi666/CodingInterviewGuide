package p159;

public class Question {

    public Node[] tarJanQuery(Node head, Query[] quries) {
        Node[] ans = new Tarjan().query(head, quries);
        return ans;
    }

    public void solve() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node9 = new Node(9);

        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node5.left = node7;
        node5.right = node8;

        node1.right = node3;
        node3.right = node6;
        node6.left = node9;

        Query query0 = new Query(node4, node7);
        Query query1 = new Query(node7, node8);
        Query query2 = new Query(node8, node9);
        Query query3 = new Query(node9, node3);
        Query query4 = new Query(node6, node6);
        Query query5 = new Query(null, node5);
        Query query6 = new Query(null, null);

        Query[] quries = new Query[]{query0, query1, query2, query3, query4, query5, query6};

        Node[] ans = tarJanQuery(node1, quries);

        for (int i = 0; i < ans.length; ++i) {
            if (null != quries[i].o1 && null != quries[i].o2) {
                System.out.println(quries[i].o1.value + "和" + quries[i].o2.value + "的最近公共父节点是：" + ans[i].value);
            } else {
                System.out.println(null != ans[i] ? ans[i].value : null);
            }
        }
    }


}
