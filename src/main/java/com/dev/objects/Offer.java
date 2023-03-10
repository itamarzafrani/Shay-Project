package com.dev.objects;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private double offerAmount;

    @ManyToOne
    @JoinColumn
    private Product product;


    @Column
    private Date createDate;


    @ManyToOne
    @JoinColumn
    private User offerFrom;

    public Offer( double offerAmount, Product product,
                  User offerFrom) {
        this.offerAmount = offerAmount;
        this.product = product;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.createDate = simpleDateFormat.getCalendar().getTime();
        this.offerFrom = offerFrom;
    }

    public Offer() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(int offerAmount) {
        this.offerAmount = offerAmount;
    }

    public User getOfferFrom() {
        return offerFrom;
    }

    public void setOfferFrom(User offerFrom) {
        this.offerFrom = offerFrom;
    }
}
