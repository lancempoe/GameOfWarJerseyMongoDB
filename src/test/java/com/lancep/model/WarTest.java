package com.lancep.model;

import com.lancep.utils.Card;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WarTest {

    War subject = new War();

    @Test
    public void getId() throws Exception {
        String id = "123rthgfd";
        subject.setId(id);
        assertThat(subject.getId(), is(id));
    }

    @Test
    public void getPlayer1DrawStack() throws Exception {
        Deque<Card> deque = new ArrayDeque<>();
        subject.setPlayer1DrawStack(deque);
        assertThat(subject.getPlayer1DrawStack(), is(deque));
    }

    @Test
    public void getPlayer2DrawStack() throws Exception {
        Deque<Card> deque = new ArrayDeque<>();
        subject.setPlayer2DrawStack(deque);
        assertThat(subject.getPlayer2DrawStack(), is(deque));
    }

}