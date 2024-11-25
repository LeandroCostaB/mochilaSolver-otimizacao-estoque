package br.com.bpkedu.knapsack;

import java.util.*;


public class EstoqueSolucao {

    /**
     * @param estoque
     * @return
     */
    public EstadodoEstoque solve(Estoque estoque) {
        System.out.println("Solucionando o problema de otimização");
        System.out.println("Ordenando os itens...");
        List<Item> sortedItems = new ArrayList<>(estoque.getItems());
        sortedItems.sort((a, b) -> Double.compare(b.getValue() / b.getPaletes(), a.getValue() / a.getPaletes()));

        System.out.println("Criando fila de prioridade para explorar os estados com base no valor total");
        PriorityQueue<EstadodoEstoque> openList = new PriorityQueue<>(Comparator.comparingDouble(EstadodoEstoque::getTotalValue).reversed());
        openList.add(new EstadodoEstoque());

        EstadodoEstoque bestState = null;

        System.out.println("Buscando a melhor combinação...");
        while (!openList.isEmpty()) {
            EstadodoEstoque current = openList.poll();

            if (current.getTotalPaletes() <= estoque.getCapacity() &&
                    (bestState == null || current.getTotalValue() > bestState.getTotalValue())) {
                System.out.println("Substituindo a combinação: " + bestState + " por " + current);
                bestState = current;
            }

            for (Item item : sortedItems) {

                if (current.getTotalPaletes() + item.getPaletes() <= estoque.getCapacity() &&
                        !current.selectedItems.contains(item)) {
                    EstadodoEstoque newState = new EstadodoEstoque(current.selectedItems);
                    newState.addItem(item);
                    openList.add(newState);
                }
            }
        }

        return bestState;
    }
}
