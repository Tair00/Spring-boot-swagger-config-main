package com.techinterface.model;


import javax.persistence.*;

@Entity
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 128,nullable = false, unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public Child() {
//        super();
//    }
//
//    public Child(Integer id, String name, Category category) {
//        this.id = id;
//        this.name = name;
//        this.category = category;
//    }
}
