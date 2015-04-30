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
 * Created by stukolov_m on 17.04.2015.
 */
@Entity
@Indexed
@Table(name = "documnentheader")
public class DocumentHeader {

    @Id
    @Column(name = "docNum", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String docNum;

    @Column(name = "docType", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String docType;

    @Column(name = "docDate", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Date docDate;

    @Column(name = "shopindex", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String shopindex;

    public DocumentHeader() {
    }

    public DocumentHeader(String docType, String docNum, Date docDate, String shopindex) {
        this.docType = docType;
        this.docNum = docNum;
        this.docDate = docDate;
        this.shopindex = shopindex;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getShopindex() {
        return shopindex;
    }

    public void setShopindex(String shopindex) {
        this.shopindex = shopindex;
    }

    @Override
    public String toString() {
        return "DocumentHeader{" +
                "docType='" + docType + '\'' +
                ", docNum='" + docNum + '\'' +
                ", docDate='" + docDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentHeader documnent = (DocumentHeader) o;

        if (docType != null ? !docType.equals(documnent.docType) : documnent.docType != null) return false;
        if (docNum != null ? !docNum.equals(documnent.docNum) : documnent.docNum != null) return false;
        return !(docDate != null ? !docDate.equals(documnent.docDate) : documnent.docDate != null);

    }

    @Override
    public int hashCode() {
        int result = docType != null ? docType.hashCode() : 0;
        result = 31 * result + (docNum != null ? docNum.hashCode() : 0);
        result = 31 * result + (docDate != null ? docDate.hashCode() : 0);
        return result;
    }
}
