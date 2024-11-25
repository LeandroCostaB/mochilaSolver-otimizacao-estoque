package br.com.bpkedu.knapsack;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class KnapsackSolverBacktracking {
    private static final int MAX_QUEUE_SIZE = 1000;
    private static final int MAX_DEPTH = 50;

    /**
     * Resolve o problema da mochila utilizando uma versão otimizada do Algoritmo A* com limite de profundidade e controle de memória.
     *
     * @param estoque
     * @return
     */
    public EstadodoEstoque solve(Estoque estoque) {
        List<Item> items = estoque.getItems();
        double capacity = estoque.getCapacity();

        PriorityQueue<EstadodoEstoque> openList = new PriorityQueue<>(Comparator.comparingDouble(this::calculatePriority).reversed());

        EstadodoEstoque initialState = new EstadodoEstoque();
        openList.add(initialState);
        EstadodoEstoque bestState = initialState;

        while (!openList.isEmpty() && openList.size() <= MAX_QUEUE_SIZE) {
            EstadodoEstoque current = openList.poll();

            if (current.getTotalValue() > bestState.getTotalValue() && current.getTotalPaletes() <= capacity) {
                bestState = current;
            }

            if (current.selectedItems.size() < MAX_DEPTH) {
                for (Item item : items) {
                    if (!current.selectedItems.contains(item)) {
                        EstadodoEstoque newState = new EstadodoEstoque(current.selectedItems);
                        newState.addItem(item);

                        if (newState.getTotalPaletes() <= capacity && calculateBound(newState, items, capacity) > bestState.getTotalValue()) {
                            openList.add(newState);

                            if (openList.size() > MAX_QUEUE_SIZE) {
                                openList.poll();
                            }
                        }
                    }
                }
            }
        }

        return bestState;
    }

    /**
     *
     *
     * @param state
     * @return
     */
    private double calculatePriority(EstadodoEstoque state) {
        return state.getTotalValue() / (state.getTotalPaletes() + 1e-5);
    }

    /**
     *
     *
     * @param state
     * @param items
     * @param capacity
     * @return
     */
    private double calculateBound(EstadodoEstoque state, List<Item> items, double capacity) {
        double bound = state.getTotalValue();
        double remainingCapacity = capacity - state.getTotalPaletes();

        for (Item item : items) {
            if (!state.selectedItems.contains(item)) {
                if (item.getPaletes() <= remainingCapacity) {
                    bound += item.getValue();
                    remainingCapacity -= item.getPaletes();
                } else {
                    bound += item.getValue() * (remainingCapacity / item.getPaletes());
                    break;
                }
            }
        }

        return bound;
    }

}
