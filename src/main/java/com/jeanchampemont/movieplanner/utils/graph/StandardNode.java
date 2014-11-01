package com.jeanchampemont.movieplanner.utils.graph;

import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jean on 21/10/14.
 */
public class StandardNode<T> implements Node<T> {
    private Set<Node<T>> children = new HashSet<>();

    private T value;

    public StandardNode(T value) {
        this.value = value;
    }

    @Override
    public boolean isRoot() {
        return false;
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
