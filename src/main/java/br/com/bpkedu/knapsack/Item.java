package br.com.bpkedu.knapsack;

public class Item {
    private Integer id;
    private int paletes;
    private Double value;

    public Item(Integer id, int paletes, Double value) {
        this.id = id;
        this.paletes = paletes;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPaletes() {
        return paletes;
    }

    public void setPaletes(int paletes) {
        this.paletes = paletes;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", Paletes=" + paletes +
                ", value=" + value +
                '}';
    }
}
