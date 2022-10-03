package com.homebanking.homebanking.model;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Card {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
   @GenericGenerator(name = "native", strategy = "native")
   private long id;
   private String cardholder;
   private TypeCard type;
   private ColorCard color;
   private String number;
   private int cvv;
   private LocalDate thruDate;
   private LocalDate fromDate;

   private boolean condition;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="clientCardOwner_id")
   private Client clientCardOwner;

   /*CONTRUCTORES*/

   public Card() {
   }

   public Card(String cardholder, TypeCard type, ColorCard color, String number, int cvv, LocalDate thruDate, LocalDate fromDate, Client clientCardOwner,boolean condition) {
      this.cardholder = cardholder;
      this.type = type;
      this.color = color;
      this.number = number;
      this.cvv = cvv;
      this.thruDate = thruDate;
      this.fromDate = fromDate;
      this.clientCardOwner = clientCardOwner;
      this.condition = condition;
   }

   /*GETTERS*/

   public long getId() {
      return id;
   }

   public String getCardholder() {
      return cardholder;
   }

   public TypeCard getType() {
      return type;
   }

   public ColorCard getColor() {
      return color;
   }

   public String getNumber() {
      return number;
   }

   public int getCvv() {
      return cvv;
   }

   public LocalDate getThruDate() {
      return thruDate;
   }

   public LocalDate getFromDate() {
      return fromDate;
   }

   public Client getClientCardOwner() {
      return clientCardOwner;
   }

   public boolean isCondition() {
      return condition;
   }

   /*SETTERS*/

   public void setCardholder(String cardholder) {
      this.cardholder = cardholder;
   }

   public void setType(TypeCard type) {
      this.type = type;
   }

   public void setColor(ColorCard color) {
      this.color = color;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   public void setCvv(int cvv) {
      this.cvv = cvv;
   }

   public void setThruDate(LocalDate thruDate) {
      this.thruDate = thruDate;
   }

   public void setFromDate(LocalDate fromDate) {
      this.fromDate = fromDate;
   }

   public void setClientCardOwner(Client clientCardOwner) {
      this.clientCardOwner = clientCardOwner;
   }

   public void setCondition(boolean condition) {
      this.condition = condition;
   }
}
