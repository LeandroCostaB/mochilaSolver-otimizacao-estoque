package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class EstadodoEstoque {

    protected final List<Item> selectedItems;

    protected int totalPaletes;

    protected double totalValue;

    public EstadodoEstoque() {
        this.selectedItems = new ArrayList<>();
        this.totalPaletes = 0;
        this.totalValue = 0.0;
    }

    /**
     *
     *
     *
     * @param selectedItems
     */
    public EstadodoEstoque(List<Item> selectedItems) {
        this.selectedItems = new ArrayList<>(selectedItems);
        for (Item item : selectedItems) {
            this.totalPaletes += item.getPaletes();
            this.totalValue += item.getValue();
        }
    }

    /**
     *
     *
     * @param item
     */
    public void addItem(Item item) {
        selectedItems.add(item);
        totalPaletes += item.getPaletes();
        totalValue += item.getValue();
    }

    /**
     *
     *
     * @return
     */
    public int getTotalPaletes() {
        return totalPaletes;
    }

    /**
     *
     *
     * @return
     */
    public double getTotalValue() {
        return totalValue;
    }

    /**
     *
     *
     *
     *
     *
     * @param capacity
     * @return
     */
    public double heuristic(double capacity) {
        return capacity - totalPaletes;
    }

    @Override
    public String toString() {
        return "KnapsackState{" +
                "totalPaletes=" + totalPaletes +
                ", totalValue=" + totalValue +
                '}';
    }
}
