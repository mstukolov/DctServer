package com.dct.server.model;

/**
 * Created by stukolov_m on 28.04.2015.
 */
import org.hibernate.search.annotations.*;
import javax.persistence.*;

@Entity
@Indexed
@Table(name = "inventitembarcode")
public class InventItemBarcode {
    @Id
    @Column(name = "barcode", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    public String barcode;

    @Column(name = "scu", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    public String scu;

    @Column(name = "size", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    public Integer size;

    public InventItemBarcode(String barcode, String scu, Integer size) {
        this.barcode = barcode;
        this.scu = scu;
        this.size = size;
    }

    public InventItemBarcode() {
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getScu() {
        return scu;
    }

    public void setScu(String scu) {
        this.scu = scu;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
