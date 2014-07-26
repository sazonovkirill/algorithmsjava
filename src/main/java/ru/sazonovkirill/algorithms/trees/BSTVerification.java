package ru.sazonovkirill.algorithms.trees;

public class BSTVerification {
    public static boolean verify(BinaryTreeNode<Integer, String> node) {
        return doVerify(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean doVerify(BinaryTreeNode<Integer, String> node, int min, int max) {
        if (node == null) return true;

        boolean nodeIsGood = (node.getKey().compareTo(min) >= min) && (node.getKey().compareTo(max) < max);
        boolean leftSubtreeIsGood = doVerify(node.getLeft(), min, node.getKey());
        boolean rightSubtreeIsGood = doVerify(node.getRight(), node.getKey(), max);

        return nodeIsGood && leftSubtreeIsGood && rightSubtreeIsGood;
    }

    public static boolean verifyIterative(BinaryTreeNode<Integer, String> node) {
        TreeTraverse<Integer, String> treeTraverse = new TreeTraverse<>(node);
        Visitor visitor = new Visitor();
        treeTraverse.inorderTraverse(visitor);
        return visitor.isResult();
    }

    static class Visitor implements IBinaryTreeNodeVisiter<Integer, String> {
        private Integer last = null;
        private boolean result = true;

        @Override
        public void visit(BinaryTreeNode<Integer, String> node) {
            if (!result) return;

            if (last == null) last = node.getKey();
            else {
                if (node.getKey().compareTo(last) < 0) result = false;
            }
        }

        public boolean isResult() {
            return result;
        }
    }
}
