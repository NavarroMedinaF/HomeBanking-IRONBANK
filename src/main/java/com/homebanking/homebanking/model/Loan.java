package com.homebanking.homebanking.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Entity
public class Loan {
/*Propiedades Metodos*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
     private long id;
     private String name;
     private double maxAmount;
     private float percentage;

     @ElementCollection
     @Column(name="payments")
     private List<Integer> paymentLoan =  new ArrayList<>();

    @OneToMany(mappedBy="loanClientOwner", fetch=FetchType.EAGER)
    private Set<ClientLoan> clientLoanOwner = new HashSet<>();



     /*Contructores*/
     public Loan() {
     }

    public Loan(String name, double maxAmount, float percentage, List<Integer> paymentLoan) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.percentage = percentage;
        this.paymentLoan = paymentLoan;
    }

    /*Getters*/

     public long getId() {
          return id;
     }
     public String getName() {
          return name;
     }
     public double getMaxAmount() {
          return maxAmount;
     }
     public float getPercentage() {
        return percentage;
    }
     public List<Integer> getPaymentLoan() {
        return paymentLoan;
    }

     public Set<ClientLoan> getClientLoanOwner() {
        return clientLoanOwner;
    }


    /*Setters*/

     public void setName(String name) {
          this.name = name;
     }

     public void setMaxAmount(double maxAmount) {
          this.maxAmount = maxAmount;
     }

     public void setPaymentLoan(List<Integer> paymentLoan) {
        this.paymentLoan = paymentLoan;
    }

     public void setClientLoanOwner(Set<ClientLoan> clientLoanOwner) {
        this.clientLoanOwner = clientLoanOwner;
    }

    /*Metodos*/

    public List<Client> getClients(){
        return clientLoanOwner.stream().map(ClientLoan::getClientLoadOwner).collect(toList());
    };
}
