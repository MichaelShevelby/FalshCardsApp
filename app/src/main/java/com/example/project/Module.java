package com.example.project;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Module {
    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    private long dataChange;
    private long dateCreate;

    public Module(String name) {
        this.name = name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDataChange() {
        return dataChange;
    }

    public void setDataChange(long dataChange) {
        this.dataChange = dataChange;
    }

    public long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(long dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Module addCard(String t, String d) {
        cards.add(new Card(t, d));

        return this;
    }


    public Module clone1()  {
        Module module = new Module(this.getName());
        module.setCards((ArrayList<Card>) this.getCards().clone());
        return module;
    }
}
