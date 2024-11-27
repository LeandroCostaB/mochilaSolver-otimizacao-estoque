package br.com.bpkedu.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapsackTest {
    public static void main(String[] args) {
        
        int capacity = 50;
        int numberOfItems = 100;
        
        List<Item> items = generateRandomItems(numberOfItems, 5, 50, 0.5, 300.0);

        items.forEach(item -> System.out.println(item));

        
        Estoque estoque = new Estoque(capacity, items);
        
        KnapsackSolverBacktracking solver = new KnapsackSolverBacktracking();
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

    private static List<Item> generateRandomItems(int count, int minPaletes, int maxPaletes, double minValue, double maxValue) {
        List<Item> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int paletes = random.nextInt(maxPaletes - minPaletes + 1) + minPaletes;
            double value = minValue + (maxValue - minValue) * random.nextDouble();
            items.add(new Item(i + 1, paletes, value));
        }

        return items;
    }
}
