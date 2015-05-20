package com.dct.server.model;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;

/**
 * Created by stukolov_m on 17.04.2015.
 */
@Entity
@Indexed
@Table(name = "documentlines")
public class DocumentLines {
    @Id
    @Column(name = "recid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer recid;

    @Column(name = "scu", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String scu;

    @Column(name = "size", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String size;

    @Column(name = "qty", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer qty;

    @Column(name = "planqty", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer planqty;

    @Column(name = "docRef", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String docRef;

    @Column(name = "docType", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String docType;

    public DocumentLines() {
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
    }

    public String getScu() {
        return scu;
    }

    public void setScu(String scu) {
        this.scu = scu;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPlanqty() {
        return planqty;
    }

    public void setPlanqty(Integer planqty) {
        this.planqty = planqty;
    }

    public String getDocRef() {
        return docRef;
    }

    public void setDocRef(String docRef) {
        this.docRef = docRef;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentLines that = (DocumentLines) o;

        if (recid != null ? !recid.equals(that.recid) : that.recid != null) return false;
        if (scu != null ? !scu.equals(that.scu) : that.scu != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (qty != null ? !qty.equals(that.qty) : that.qty != null) return false;
        if (planqty != null ? !planqty.equals(that.planqty) : that.planqty != null) return false;
        return !(docRef != null ? !docRef.equals(that.docRef) : that.docRef != null);

    }

    @Override
    public int hashCode() {
        int result = recid != null ? recid.hashCode() : 0;
        result = 31 * result + (scu != null ? scu.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (qty != null ? qty.hashCode() : 0);
        result = 31 * result + (planqty != null ? planqty.hashCode() : 0);
        result = 31 * result + (docRef != null ? docRef.hashCode() : 0);
        return result;
    }
}
