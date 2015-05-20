package com.dct.server.model;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by stukolov_m on 18.05.2015.
 */
@Entity
@Indexed
@Table(name = "incomingmessages")
public class IncomingMessages {
    @Id
    @Column(name = "PackageNo", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer PackageNo;

    @Column(name = "Shopindex", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String Shopindex;

    @Column(name = "ReceivedDateTime", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Date ReceivedDateTime;

    @Column(name = "FinishedDateTime", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Date FinishedDateTime;

    @Column(name = "Status", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer Status;

    @Column(name = "NumRecs", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer NumRecs;

    @Column(name = "TryCount", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer TryCount;

    @Column(name = "MessageString", nullable = false, columnDefinition="LONGTEXT")
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    String MessageString;

    @Column(name = "ErrorNo", nullable = false)
    @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.YES)
    Integer ErrorNo;

    public IncomingMessages() {
    }

    public Integer getPackageNo() {
        return PackageNo;
    }

    public void setPackageNo(Integer packageNo) {
        PackageNo = packageNo;
    }

    public String getShopindex() {
        return Shopindex;
    }

    public void setShopindex(String shopindex) {
        Shopindex = shopindex;
    }

    public Date getReceivedDateTime() {
        return ReceivedDateTime;
    }

    public void setReceivedDateTime(Date receivedDateTime) {
        ReceivedDateTime = receivedDateTime;
    }

    public Date getFinishedDateTime() {
        return FinishedDateTime;
    }

    public void setFinishedDateTime(Date finishedDateTime) {
        FinishedDateTime = finishedDateTime;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getNumRecs() {
        return NumRecs;
    }

    public void setNumRecs(Integer numRecs) {
        NumRecs = numRecs;
    }

    public Integer getTryCount() {
        return TryCount;
    }

    public void setTryCount(Integer tryCount) {
        TryCount = tryCount;
    }

    public String getMessageString() {
        return MessageString;
    }

    public void setMessageString(String messageString) {
        MessageString = messageString;
    }

    public Integer getErrorNo() {
        return ErrorNo;
    }

    public void setErrorNo(Integer errorNo) {
        ErrorNo = errorNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IncomingMessages that = (IncomingMessages) o;

        if (PackageNo != null ? !PackageNo.equals(that.PackageNo) : that.PackageNo != null) return false;
        if (Shopindex != null ? !Shopindex.equals(that.Shopindex) : that.Shopindex != null) return false;
        if (ReceivedDateTime != null ? !ReceivedDateTime.equals(that.ReceivedDateTime) : that.ReceivedDateTime != null)
            return false;
        if (FinishedDateTime != null ? !FinishedDateTime.equals(that.FinishedDateTime) : that.FinishedDateTime != null)
            return false;
        if (Status != null ? !Status.equals(that.Status) : that.Status != null) return false;
        if (NumRecs != null ? !NumRecs.equals(that.NumRecs) : that.NumRecs != null) return false;
        if (TryCount != null ? !TryCount.equals(that.TryCount) : that.TryCount != null) return false;
        if (MessageString != null ? !MessageString.equals(that.MessageString) : that.MessageString != null)
            return false;
        return !(ErrorNo != null ? !ErrorNo.equals(that.ErrorNo) : that.ErrorNo != null);

    }

    @Override
    public int hashCode() {
        int result = PackageNo != null ? PackageNo.hashCode() : 0;
        result = 31 * result + (Shopindex != null ? Shopindex.hashCode() : 0);
        result = 31 * result + (ReceivedDateTime != null ? ReceivedDateTime.hashCode() : 0);
        result = 31 * result + (FinishedDateTime != null ? FinishedDateTime.hashCode() : 0);
        result = 31 * result + (Status != null ? Status.hashCode() : 0);
        result = 31 * result + (NumRecs != null ? NumRecs.hashCode() : 0);
        result = 31 * result + (TryCount != null ? TryCount.hashCode() : 0);
        result = 31 * result + (MessageString != null ? MessageString.hashCode() : 0);
        result = 31 * result + (ErrorNo != null ? ErrorNo.hashCode() : 0);
        return result;
    }
}
