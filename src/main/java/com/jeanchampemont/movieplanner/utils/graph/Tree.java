package com.jeanchampemont.movieplanner.utils.graph;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jean on 21/10/14.
 */
public class Tree<T> implements Node<T> {

    private Set<Node<T>> children = new HashSet<>();

    private T value;

    public Tree(T value) {
        this.value = value;
    }

    public Tree() {}

    @Override
    public boolean isRoot() {
        return true;
    }

    @Override
    public Set<Node<T>> getChildren() {
        return ImmutableSet.copyOf(children);
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void addChild(Node<T> child) {
        children.add(child);
    }
}
