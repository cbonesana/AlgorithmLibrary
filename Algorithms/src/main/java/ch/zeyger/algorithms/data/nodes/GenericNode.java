package ch.zeyger.algorithms.data.nodes;

import java.util.*;

/**
 * Author:  Claudio Bonesana
 * Date:    13.02.2016
 * Project: Algorithms
 *
 * This class represents a generic node. A generic node is composed by an array of elements
 * each with a position (or index) in this array and a name. The name and the position can
 * be used to take the saved values.
 *
 * This class can be used to represent a single node or a collection of nodes (a graph).
 */
public class GenericNode<T> implements Iterable<T> {

    /**
     * Each dimension has a coordinate.
     */
    protected List<T> elements = new ArrayList<>();
    /**
     * Each dimension can have a name
     */
    protected Map<String, Integer> names = new HashMap<>();

    /**
     * Empty constructor.
     */
    public GenericNode() {}

    public GenericNode(GenericNode<T> another) {
        // TODO: currently this is a shallow copy, should became a deep copy
        this.elements = new ArrayList<>(another.elements);
        this.names = new HashMap<>(another.names);
    }

    /**
     * Add a new element with a specified name and index to the elements.
     * The name and the index will be the given parameters.
     * @param name the name of the new value
     * @param index the position of the new value
     * @param value the new value
     */
    public void add(String name, int index, T value) {
        elements.add(value);
        names.put(name, index);
    }

    /**
     * Add a new value in the specified index of the elements.
     * The name and index of the new value will be the given index value.
     * @param index the position of the new value
     * @param value the new value
     */
    public void add(int index, T value) {
        add("" + index, index, value);
    }

    /**
     * Add a new value with a specified name to the elements.
     * The name of the new value will be the given name.
     * The index of the new value will be the current size of the elements.
     * @param name the name of the new value
     * @param value the new value
     */
    public void add(String name, T value) {
        int index = elements.size();
        add(name, index, value);
    }

    /**
     * Add a new value to the elements.
     * The name and index of the new value will be the current size of the elements.
     * @param value the new value
     */
    public void add(T value) {
        int index = elements.size();
        add("" + index, index, value);
    }

    /**
     * Given its position, return the relative value.
     * @param index the position of the value
     * @return the saved value
     */
    public T get(int index) {
        return elements.get(index);
    }

    /**
     * Given its name, return the relative value.
     * @param name the name of the value
     * @return the saved value
     */
    public T get(String name) {
        return elements.get(names.get(name));
    }

    /**
     * @return the number of elements in this node
     */
    public int size() {
        return elements.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericNode)) return false;

        GenericNode<?> that = (GenericNode<?>) o;

        return elements != null ? elements.equals(that.elements) : that.elements == null;
    }

    @Override
    public int hashCode() {
        return elements != null ? elements.hashCode() : 0;
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }
}
