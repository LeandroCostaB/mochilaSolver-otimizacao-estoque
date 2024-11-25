package br.com.bpkedu.knapsack;

public class Item {
    private Integer id;
    private String name;
    private int paletes;
    private Double value;

    public Item(Integer id, String name, int paletes, Double value) {
        this.id = id;
        this.name = name;
        this.paletes = paletes;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", name='" + name + '\'' +
                ", Paletes=" + paletes +
                ", value=" + value +
                '}';
    }
}
