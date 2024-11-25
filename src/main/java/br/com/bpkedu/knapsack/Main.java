package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int capacity = 10;


        List<Item> items = new ArrayList<>();

        items.add(new Item(1, "Coca-Cola", 1, 8.50));
        items.add(new Item(2, "Água Mineral", 2, 2.00));
        items.add(new Item(3, "Suco de Laranja", 3, 6.00));
        items.add(new Item(4, "Cerveja", 5, 4.50));
        items.add(new Item(5, "Vinho Tinto", 4, 25.00));


        items.forEach(item -> System.out.println(item));

        Estoque estoque = new Estoque(capacity, items);

        KnapsackSolverBacktracking solver = new KnapsackSolverBacktracking();
        EstadodoEstoque bestState = solver.solve(estoque);

        System.out.println("Melhor combinação de paletes para o estoque:");
        for (Item item : bestState.selectedItems) {
            System.out.println(String.format("Id:%s, Nome: %s, Paletes: %d, Valor: %.2f%n", item.getId(), item.getName(), item.getPaletes(), item.getValue()));
        }
        System.out.println(String.format("Número de paletes total: %d", bestState.getTotalPaletes()));
        System.out.println(String.format("Valor total: %.2f", bestState.getTotalValue()));



    }


}