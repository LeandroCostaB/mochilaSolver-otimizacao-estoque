package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private int capacity;
    private List<Item> items;


    public Estoque(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    public Estoque(int capacity, List<Item> itens) {
        this.capacity = capacity;
        this.items = itens;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }
}
