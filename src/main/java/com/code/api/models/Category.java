package com.code.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catid")
    private int categoryId;

    @Column(name = "catname", length = 40, nullable = false, unique = true)
    private String catname;

    @Column(name = "catdesc", length = 100, nullable = false)
    private String catdesc;

    public Category() {
        // No-arg constructor
    }

    public Category(String catname, String catdesc) {
        this.catname = catname;
        this.catdesc = catdesc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getCatdesc() {
        return catdesc;
    }

    public void setCatdesc(String catdesc) {
        this.catdesc = catdesc;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", catname='" + catname + '\'' +
                ", catdesc='" + catdesc + '\'' +
                '}';
    }
}
