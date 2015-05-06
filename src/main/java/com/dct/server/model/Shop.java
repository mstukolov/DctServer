package com.dct.server.model;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by stukolov_m on 06.05.2015.
 */
@Entity
@Indexed
@Table(name = "shop")
public class Shop {
    @Id
    @Column(name = "shopindex", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String shopindex;

    @Column(name = "shopname", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String shopname;

    public Shop() {
    }

    public Shop(String shopindex, String shopname) {
        this.shopindex = shopindex;
        this.shopname = shopname;
    }

    public String getShopindex() {
        return shopindex;
    }

    public void setShopindex(String shopindex) {
        this.shopindex = shopindex;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (shopindex != null ? !shopindex.equals(shop.shopindex) : shop.shopindex != null) return false;
        return !(shopname != null ? !shopname.equals(shop.shopname) : shop.shopname != null);

    }

    @Override
    public int hashCode() {
        int result = shopindex != null ? shopindex.hashCode() : 0;
        result = 31 * result + (shopname != null ? shopname.hashCode() : 0);
        return result;
    }
}
