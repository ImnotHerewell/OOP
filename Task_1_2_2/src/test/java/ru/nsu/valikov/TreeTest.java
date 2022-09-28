package ru.nsu.valikov;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

public class TreeTest {
    @Test
    void main() {
    }

    @Test
    void get() {
    }

    @Test
    void add() {
        Tree <String> kek=new Tree<>();
        Tree <String> tet=new Tree<>();
        kek.add("B");
        kek.add("A");
        kek.add("C");
        Iterator<String> it=kek.iterator();
        while (it.hasNext()){
            String el = it.next();
            System.out.println(el);
        }

    }

    @Test
    void testAdd() {
    }

    @Test
    void iterator() {
    }
}
