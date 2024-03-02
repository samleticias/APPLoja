package io.github.samleticias.domain.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "cliente")

public class Cliente {
    @Id
    // define qual é a primary key da entidade, é obrigatorio ter em uma entidade
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String name;

    public Cliente() {
    }

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Cliente(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
