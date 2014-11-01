package com.jeanchampemont.movieplanner.utils.graph;

import java.util.Set;

/**
 * @author jchampemont
 */
public interface Node<T> {

    boolean isRoot();

    Set<Node<T>> getChildren();

    T getValue();

    void addChild(Node<T> child);
}
