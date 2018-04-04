package com.wjq.domain;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p>Description : bookshop
 * <p>Date : 2018/1/18 11:17
 * <p>@author : wjq
 */
//@Entity
public class PrintBook extends Book {
    @Temporal(TemporalType.TIMESTAMP)
    private Date printDate;

    public Date getPrintDate() {
        return printDate;
    }

    public PrintBook setPrintDate(Date printDate) {
        this.printDate = printDate;
        return this;
    }
}
