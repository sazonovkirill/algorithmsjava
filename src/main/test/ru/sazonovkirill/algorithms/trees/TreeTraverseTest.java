package ru.sazonovkirill.algorithms.trees;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeTraverseTest {
    static class Visitor implements IBinaryTreeNodeVisiter<Integer, String> {
        private final List<String> trace = new ArrayList<>();

        @Override
        public void visit(BinaryTreeNode node) {
            String key = node.getKey().toString();
            trace.add(key);
        }

        public List<String> getTrace() {
            return trace;
        }

        public String getTraceString() {
            StringBuilder sb = new StringBuilder();
            for (String s : trace) {
                sb.append(s + ",");
            }
            return sb.toString();
        }
    }

    private final BinaryTreeNode<Integer, String> COMPLETE_3_LEVEL_TREE =
            new BinaryTreeNode<Integer, String>(1,
                new BinaryTreeNode<Integer, String>(2,
                    new BinaryTreeNode<Integer, String>(4,
                            new BinaryTreeNode<Integer, String>(8),
                            new BinaryTreeNode<Integer, String>(9)),
                    new BinaryTreeNode<Integer, String>(5,
                            new BinaryTreeNode<Integer, String>(10),
                            new BinaryTreeNode<Integer, String>(11))),
                new BinaryTreeNode<Integer, String>(3,
                    new BinaryTreeNode<Integer, String>(6,
                            new BinaryTreeNode<Integer, String>(12),
                            new BinaryTreeNode<Integer, String>(13)),
                    new BinaryTreeNode<Integer, String>(7,
                            new BinaryTreeNode<Integer, String>(14),
                            new BinaryTreeNode<Integer, String>(15))));

    private final String COMPLETE_3_LEVEL_TREE_PREORDER = "1,2,4,8,9,5,10,11,3,6,12,13,7,14,15,";
    private final String COMPLETE_3_LEVEL_TREE_INORDER = "8,4,9,2,10,5,11,1,12,6,13,3,14,7,15,";
    private final String COMPLETE_3_LEVEL_TREE_POSTORDER = "8,9,4,10,11,5,2,12,13,6,14,15,7,3,1,";

    private final BinaryTreeNode<Integer, String> COMPLETE_1_LEVEL_TREE =
            new BinaryTreeNode<Integer, String>(1,
                    new BinaryTreeNode<Integer, String>(2),
                    new BinaryTreeNode<Integer, String>(3)
            );

    private final String COMPLETE_1_LEVEL_TREE_PREORDER = "1,2,3,";
    private final String COMPLETE_1_LEVEL_TREE_INORDER = "2,1,3,";
    private final String COMPLETE_1_LEVEL_TREE_POSTORDER = "2,3,1,";


    private final BinaryTreeNode<Integer, String> A_1_NODE_TREE = new BinaryTreeNode<Integer, String>(1);

    private final String A_1_NODE_TREE_PREORDER = "1,";
    private final String A_1_NODE_TREE_INORDER = "1,";
    private final String A_1_NODE_TREE_POSTORDER = "1,";


    static enum Order {
        PREORDER, INORDER, POSTORDER
    }

    static enum ImplType {
        RECUSRIVE, ITERATIVE
    }

    private void test(BinaryTreeNode<Integer, String> tree, Order order, ImplType implType, String expected) {
        Visitor visitor = new Visitor();
        if (implType == ImplType.RECUSRIVE) {
            TreeTraverse<Integer, String> treeTraverse = new TreeTraverse<>(tree);
            if (order == Order.PREORDER) {
                treeTraverse.preorderTraverse(visitor);
            } else if (order == Order.INORDER) {
                treeTraverse.inorderTraverse(visitor);
            } else if (order == Order.POSTORDER) {
                treeTraverse.postorderTraverse(visitor);
            }
        } else if (implType == ImplType.ITERATIVE) {
            IterativeTreeTraverse<Integer, String> treeTraverse = new IterativeTreeTraverse<>(tree);
            if (order == Order.PREORDER) {
                treeTraverse.preorderTraverse(visitor);
            } else if (order == Order.INORDER) {
                treeTraverse.inorderTraverse(visitor);
            } else if (order == Order.POSTORDER) {
                treeTraverse.postorderTraverse(visitor);
            }
        }

        String actual = visitor.getTraceString();
        Assert.assertEquals(expected, actual);
    }

    // Recursive

    @Test
    public void preorder_testComplete3LevelTree() {
        test(COMPLETE_3_LEVEL_TREE, Order.PREORDER, ImplType.RECUSRIVE, COMPLETE_3_LEVEL_TREE_PREORDER);
    }

    @Test
    public void preorder_testComplete1LevelTree() {
        test(COMPLETE_1_LEVEL_TREE, Order.PREORDER, ImplType.RECUSRIVE, COMPLETE_1_LEVEL_TREE_PREORDER);
    }

    @Test
    public void preorder_test1NodeTree() {
        test(A_1_NODE_TREE, Order.PREORDER, ImplType.RECUSRIVE, A_1_NODE_TREE_PREORDER);
    }

    @Test
    public void preorder_testNullTree() {
        test(null, Order.PREORDER, ImplType.RECUSRIVE, "");
    }

    public void preorder_testIncomplete3LevelTree() {

    }

    public void preorder_testIncomplete2LevelTree() {

    }

    public void preorder_testIncomplete1LevelTree() {

    }

    @Test
    public void inorder_testComplete3LevelTree() {
        test(COMPLETE_3_LEVEL_TREE, Order.INORDER, ImplType.RECUSRIVE, COMPLETE_3_LEVEL_TREE_INORDER);
    }

    @Test
    public void inorder_testComplete1LevelTree() {
        test(COMPLETE_1_LEVEL_TREE, Order.INORDER, ImplType.RECUSRIVE, COMPLETE_1_LEVEL_TREE_INORDER);
    }

    @Test
    public void inorder_test1NodeTree() {
        test(A_1_NODE_TREE, Order.INORDER, ImplType.RECUSRIVE, A_1_NODE_TREE_INORDER);
    }

    @Test
    public void inorder_testNullTree() {
        test(null, Order.INORDER, ImplType.RECUSRIVE, "");
    }

    public void inorder_testIncomplete3LevelTree() {

    }

    public void inorder_testIncomplete2LevelTree() {

    }

    public void inorder_testIncomplete1LevelTree() {

    }

    @Test
    public void postorder_testComplete3LevelTree() {
        test(COMPLETE_3_LEVEL_TREE, Order.POSTORDER, ImplType.RECUSRIVE, COMPLETE_3_LEVEL_TREE_POSTORDER);
    }

    @Test
    public void postorder_testComplete1LevelTree() {
        test(COMPLETE_1_LEVEL_TREE, Order.POSTORDER, ImplType.RECUSRIVE, COMPLETE_1_LEVEL_TREE_POSTORDER);
    }

    @Test
    public void postorder_test1NodeTree() {
        test(A_1_NODE_TREE, Order.POSTORDER, ImplType.RECUSRIVE, A_1_NODE_TREE_POSTORDER);
    }

    @Test
    public void postorder_testNullTree() {
        test(null, Order.POSTORDER, ImplType.RECUSRIVE, "");
    }

    public void postorder_testIncomplete3LevelTree() {

    }

    public void postorder_testIncomplete2LevelTree() {

    }

    public void postorder_testIncomplete1LevelTree() {

    }

    // Iterative

    @Test
    public void iterative_preorder_testComplete3LevelTree() {
        test(COMPLETE_3_LEVEL_TREE, Order.PREORDER, ImplType.ITERATIVE, COMPLETE_3_LEVEL_TREE_PREORDER);
    }

    @Test
    public void iterative_preorder_testComplete1LevelTree() {
        test(COMPLETE_1_LEVEL_TREE, Order.PREORDER, ImplType.ITERATIVE, COMPLETE_1_LEVEL_TREE_PREORDER);
    }

    @Test
    public void iterative_preorder_test1NodeTree() {
        test(A_1_NODE_TREE, Order.PREORDER, ImplType.ITERATIVE, A_1_NODE_TREE_PREORDER);
    }

    @Test
    public void iterative_preorder_testNullTree() {
        test(null, Order.PREORDER, ImplType.ITERATIVE, "");
    }

    public void iterative_preorder_testIncomplete3LevelTree() {

    }

    public void iterative_preorder_testIncomplete2LevelTree() {

    }

    public void iterative_preorder_testIncomplete1LevelTree() {

    }

    @Test
    public void iterative_inorder_testComplete3LevelTree() {
        test(COMPLETE_3_LEVEL_TREE, Order.INORDER, ImplType.ITERATIVE, COMPLETE_3_LEVEL_TREE_INORDER);
    }

    @Test
    public void iterative_inorder_testComplete1LevelTree() {
        test(COMPLETE_1_LEVEL_TREE, Order.INORDER, ImplType.ITERATIVE, COMPLETE_1_LEVEL_TREE_INORDER);
    }

    @Test
    public void iterative_inorder_test1NodeTree() {
        test(A_1_NODE_TREE, Order.INORDER, ImplType.ITERATIVE, A_1_NODE_TREE_INORDER);
    }

    @Test
    public void iterative_inorder_testNullTree() {
        test(null, Order.INORDER, ImplType.ITERATIVE, "");
    }

    public void iterative_inorder_testIncomplete3LevelTree() {

    }

    public void iterative_inorder_testIncomplete2LevelTree() {

    }

    public void iterative_inorder_testIncomplete1LevelTree() {

    }

    @Test
    public void iterative_postorder_testComplete3LevelTree() {
        test(COMPLETE_3_LEVEL_TREE, Order.POSTORDER, ImplType.ITERATIVE, COMPLETE_3_LEVEL_TREE_POSTORDER);
    }

    @Test
    public void iterative_postorder_testComplete1LevelTree() {
        test(COMPLETE_1_LEVEL_TREE, Order.POSTORDER, ImplType.ITERATIVE, COMPLETE_1_LEVEL_TREE_POSTORDER);
    }

    @Test
    public void iterative_postorder_test1NodeTree() {
        test(A_1_NODE_TREE, Order.POSTORDER, ImplType.ITERATIVE, A_1_NODE_TREE_POSTORDER);
    }

    @Test
    public void iterative_postorder_testNullTree() {
        test(null, Order.POSTORDER, ImplType.ITERATIVE, "");
    }

    public void iterative_postorder_iterative_testIncomplete3LevelTree() {

    }

    public void iterative_postorder_testIncomplete2LevelTree() {

    }

    public void iterative_postorder_testIncomplete1LevelTree() {

    }
}
