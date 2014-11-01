package com.jeanchampemont.movieplanner.utils.graph;

import java.util.Set;
import java.util.function.BiFunction;

public class TreeBuilder {
    /**
     * Build a Tree with all children known
     * and a null-valued root
     *
     * @param allChildren all children that might be included in the tree
     * @param eligibleChildrenFinder lambda to filter allChildren with eligible childrens for node-value.
     *                               must return all first-level children when passed a null-value
     *                               must return an empty set when there is no eligible children.
     *                               If it never returns an empty set, tree building will fail.
     * @return the tree
     */
    public static <T> Tree<T> build(Set<T> allChildren, BiFunction<T, Set<T>, Set<T>> eligibleChildrenFinder) {
        return build(null, allChildren, eligibleChildrenFinder);
    }

    /**
     * Build a Tree with all children known
     *
     * @param root root value
     * @param allChildren all children that might be included in the tree
     * @param eligibleChildrenFinder lambda to filter allChildren with eligible childrens for node-value.
     *                               must return an empty set when there is no eligible children.
     *                               If it never returns an empty set, tree building will fail.
     * @return the tree
     */
    public static <T> Tree<T> build(T root, Set<T> allChildren, BiFunction<T, Set<T>, Set<T>> eligibleChildrenFinder) {
        Tree<T> result = new Tree<>(root);
        buildRecursive(result, allChildren, eligibleChildrenFinder);
        return result;
    }

    private static <T> Node<T> buildRecursive(Node<T> parent, Set<T> allChildren, BiFunction<T, Set<T>, Set<T>> eligibleChildrenFinder) {
        Set<T> eligibleChildren = eligibleChildrenFinder.apply(parent.getValue(), allChildren);
        for(T eligibleChild : eligibleChildren) {
            Node<T> childNode = new StandardNode<T>(eligibleChild);
            parent.addChild(childNode);
            buildRecursive(childNode, allChildren, eligibleChildrenFinder);
        }
        return parent;
    }
}
