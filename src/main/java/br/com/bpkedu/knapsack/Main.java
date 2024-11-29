package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int capacity = 10;

        List<Item> items = new ArrayList<>();
        items.add(new Item(40, 25, 24.00));
        items.add(new Item(5, 5, 200.00));
        items.add(new Item(6, 45, 5.65));
        items.add(new Item(6, 1, 5.65));

        Estoque estoque = new Estoque(capacity, items);

        EstoqueSolverBacktracking solver = new EstoqueSolverBacktracking();
        EstadodoEstoque bestState = solver.solve(estoque);

        System.out.println("Melhor combinação de paletes para o estoque:");
        System.out.println("------------------------------------------------------------");
        for (Item item : bestState.selectedItems) {
            System.out.println(String.format("Id:%s, Paletes: %d, Valor: %.2f%n", item.getId(), item.getPaletes(), item.getValue()));
            System.out.println("------------------------------------------------------------");
        }
        System.out.println(String.format("Número de paletes total: %d", bestState.getTotalPaletes()));
        System.out.println(String.format("Valor total no estoque:R$ %.2f", bestState.getTotalValue()));


    }
}