# Knapsack Solver

Este repositório contém uma implementação orientada a objetos do **Problema da Mochila** (Knapsack Problem) utilizando o **Algoritmo A*** em Java, combinado com a **técnica de poda por backtracking** para otimizar ainda mais a busca. O problema e a solução são apresentados com uma abordagem teórica clara e um embasamento técnico robusto.

---

## O Problema da Mochila

O **Problema da Mochila** é um problema clássico de otimização combinatória que pode ser descrito como:

- Dada uma mochila com uma capacidade máxima \( C \), e um conjunto de itens \( N \), onde cada item \( i \) tem:
  - **Peso** (\( w_i \)),
  - **Valor** (\( v_i \)),
- O objetivo é determinar o subconjunto de itens que maximiza o valor total (\( \sum v_i \)), sem que a soma dos pesos (\( \sum w_i \)) ultrapasse \( C \).

Esse problema tem várias aplicações práticas, como alocação de recursos, planejamento financeiro e problemas de logística.

---

## O Algoritmo A* e Poda por Backtracking

O **Algoritmo A*** é um método de busca informada que utiliza:
1. **Custo acumulado (\( g(n) \))**: Representa o valor acumulado dos itens já incluídos no estado atual.
2. **Heurística (\( h(n) \))**: Uma estimativa do valor potencial que pode ser obtido com os itens restantes.

A função de avaliação do A* é:
\[ f(n) = g(n) + h(n) \]

### Técnica de Poda Backtracking

A poda é uma técnica que permite descartar certos caminhos (estados) na árvore de busca, reduzindo drasticamente o espaço de soluções exploradas. No contexto do **Problema da Mochila**, a poda ocorre quando:
- O peso acumulado no estado atual excede a capacidade \( C \) da mochila.
- O valor potencial do estado atual (considerando \( g(n) + h(n) \)) é inferior ao melhor valor já encontrado.

Ao integrar o A* com a poda, o algoritmo ganha eficiência, evitando explorar combinações que já podem ser descartadas de antemão.

### Exemplo de Poda

Considere:
- Capacidade \( C = 10 \),
- Estado atual com peso acumulado \( w = 9 \),
- Próximo item com \( w = 3 \).

Neste caso, a adição do próximo item é imediatamente descartada, pois \( w + 3 > C \).

---

## Estrutura do Código

O código está organizado de forma modular e utiliza classes bem definidas para representar os componentes principais do problema:

### Principais Classes

1. **`Item`**: Representa os itens com seus pesos e valores.
2. **`KnapsackNode`**: Representa um estado na árvore de busca, contendo:
   - Itens selecionados,
   - Peso acumulado,
   - Valor acumulado,
   - Capacidade restante.
3. **`KnapsackSolver`**: Implementa o Algoritmo A* com poda por backtracking. Gera e avalia novos estados, descartando aqueles que:
   - Excedem a capacidade da mochila,
   - Não têm potencial para melhorar a solução ótima.

### Exemplo de Implementação

#### Classe `KnapsackNode` com Poda
```java
public class KnapsackNode {
    private final int value;
    private final int weight;
    private final int remainingCapacity;
    private final List<Item> items;

    public KnapsackNode(int value, int weight, int remainingCapacity, List<Item> items) {
        this.value = value;
        this.weight = weight;
        this.remainingCapacity = remainingCapacity;
        this.items = new ArrayList<>(items);
    }

    public boolean isFeasible() {
        return remainingCapacity >= 0;
    }

    public KnapsackNode generateNextNode(Item item) {
        if (item.getWeight() > remainingCapacity) {
            return null; // Poda: Excede capacidade
        }
        List<Item> newItems = new ArrayList<>(items);
        newItems.add(item);
        return new KnapsackNode(
            value + item.getValue(),
            weight + item.getWeight(),
            remainingCapacity - item.getWeight(),
            newItems
        );
    }

    public int getEstimatedValue(List<Item> remainingItems) {
        int estimatedValue = value;
        for (Item item : remainingItems) {
            if (item.getWeight() <= remainingCapacity) {
                estimatedValue += item.getValue();
            }
        }
        return estimatedValue;
    }
}
```

#### Classe `KnapsackSolver`
```java
public class KnapsackSolver {
    private final int capacity;
    private final List<Item> items;

    public KnapsackSolver(int capacity, List<Item> items) {
        this.capacity = capacity;
        this.items = items;
    }

    public KnapsackNode solve() {
        PriorityQueue<KnapsackNode> queue = new PriorityQueue<>(Comparator.comparingInt(KnapsackNode::getEstimatedValue).reversed());
        queue.add(new KnapsackNode(0, 0, capacity, new ArrayList<>()));

        KnapsackNode bestSolution = null;

        while (!queue.isEmpty()) {
            KnapsackNode current = queue.poll();

            if (!current.isFeasible()) continue; // Poda por backtracking

            if (bestSolution == null || current.getValue() > bestSolution.getValue()) {
                bestSolution = current;
            }

            for (Item item : items) {
                KnapsackNode nextNode = current.generateNextNode(item);
                if (nextNode != null) {
                    queue.add(nextNode);
                }
            }
        }

        return bestSolution;
    }
}
```

---

## Configuração e Execução

### Requisitos
- **Java Development Kit (JDK)** versão 8 ou superior.
- **Apache Maven** para gerenciamento de dependências.

### Passos
1. Clone o repositório:
   ```bash
   git clone https://github.com/jhonieldorschulz/knapsackSolver.git
   cd knapsackSolver
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute o programa:
   ```bash
   java -cp target/knapsackSolver-1.0-SNAPSHOT.jar br.com.bpkedu.Main
   ```

---

## Funcionamento

1. O programa inicia definindo os itens disponíveis com seus pesos e valores.
2. Especifica a capacidade máxima da mochila.
3. Executa o Algoritmo A* combinado com poda por backtracking.
4. Exibe:
   - O subconjunto de itens selecionados,
   - O valor total da solução,
   - O tempo de execução.

---

## Contribuições

Contribuições são bem-vindas. Consulte as instruções no repositório.

---

## Licença

Este projeto está sob a licença MIT.

