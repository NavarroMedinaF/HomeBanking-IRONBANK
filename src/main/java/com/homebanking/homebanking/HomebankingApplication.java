package com.homebanking.homebanking;

import com.homebanking.homebanking.model.*;
import com.homebanking.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.homebanking.homebanking.model.ColorCard.GOLD;
import static com.homebanking.homebanking.model.ColorCard.TITANIUM;
import static com.homebanking.homebanking.model.TypeCard.CREDITO;
import static com.homebanking.homebanking.model.TypeCard.DEBITO;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository,
									  TransactionRepository transactionRepository, LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository, CardRepository cardRepository){

		return (args) -> {

			/*Fechas con horas*/
			LocalDateTime creationDate= LocalDateTime.now();
			LocalDateTime creationDateTomorrow= creationDate.plusDays(1);

			/*Fechas*/
			LocalDate date=LocalDate.now();
			LocalDate date5Years = date.plusYears(5);

			/*CLIENTES*/

			/*Client client1 = new Client("Facundo1","Navarro1", "navarromedina.facu1@gmail.com", passwordEncoder.encode("pelota123"));
			clientRepository.save(client1);
			Client client2 = new Client("Ezequiel", "Medina","naverromedina.ezequiel@gmail.com", passwordEncoder.encode("pelota456"));
			clientRepository.save(client2);
			Client client3 = new Client("Diana", "Medina", "medina.Diana@gmail.com", passwordEncoder.encode("pelota789"));
			clientRepository.save(client3);
			Client client4= new Client("admin","admin","admin@admin", passwordEncoder.encode("admin123"));
			clientRepository.save(client4);*/


			/*CUENTAS*/

			/*Account accountFacu1 = new Account("VIN-00000001", creationDate, 5000,client1);
			accountRepository.save(accountFacu1);
			Account accountFacu2 = new Account("VIN-00000002", creationDateTomorrow, 7500,client1);
			accountRepository.save(accountFacu2);

			Account accountEze1= new Account("VIN-00000003", creationDate,4521,client2);
			accountRepository.save(accountEze1);*/

			/*TRANSACCIONES*/

			/*Transaction transaction1 = new Transaction(TransactionType.DEBITO,-1587.23, "Compra PIZZERIA LA FIACA",creationDate,accountFacu1);
			transactionRepository.save(transaction1);
			Transaction transaction2 = new Transaction(TransactionType.CREDITO,25000, "Pago por trabajo en pagina DR.ZAPATO",creationDate,accountFacu1);
			transactionRepository.save(transaction2);
			Transaction transaction3 = new Transaction(TransactionType.DEBITO,-9897.56, "Pago cuota COMPRAGAMER",creationDate,accountFacu1);
			transactionRepository.save(transaction3);
			Transaction transaction4 = new Transaction(TransactionType.DEBITO,-956.43, "Compra Kiosko 24HORAS MARCICO",creationDate,accountFacu1);
			transactionRepository.save(transaction4);

			Transaction transaction5 = new Transaction(TransactionType.DEBITO,-7894.23, "Compra en DR.ZAPATO",creationDate,accountFacu2);
			transactionRepository.save(transaction5);
			Transaction transaction6 = new Transaction(TransactionType.CREDITO,100000, "SUELDO MES JULIO",creationDate,accountFacu2);
			transactionRepository.save(transaction6);
			Transaction transaction7 = new Transaction(TransactionType.DEBITO,-19897.56, "Pago cuota GARBARINO",creationDate,accountFacu2);
			transactionRepository.save(transaction7);
			Transaction transaction8 = new Transaction(TransactionType.DEBITO,-1956.43, "RESTO RIE",creationDate,accountFacu2);
			transactionRepository.save(transaction8);*/


			/*PRESTAMOS*/

			/*List<Integer> paymentsMortgage = List.of(12,24,36,48,60);
			List<Integer> paymentsPersonal=List.of(6,12,24);
			List<Integer> paymentsCar=List.of(6,12,24,36);*/

			/*Loan mortageLoan = new Loan("Mortgage Loan", 500000, paymentsMortgage);
			loanRepository.save(mortageLoan);

			Loan personalLoan = new Loan("Personal Loan", 100000, paymentsPersonal);
			loanRepository.save(personalLoan);

			Loan carLoan = new Loan("Automotive Loan", 300000, paymentsCar);
			loanRepository.save(carLoan);*/

			/*CLIENTLOANS  instanciamos los objetos para las relaciones*/

			/*ClientLoan clientLoan1 = new ClientLoan(400000, 60, client1, mortageLoan);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(50000, 12, client1, personalLoan);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3= new ClientLoan(100000, 24, client2, personalLoan);
			clientLoanRepository.save(clientLoan3);

			ClientLoan clientLoan4 = new ClientLoan(200000, 36, client2,carLoan);
			clientLoanRepository.save(clientLoan4);*/

			/*CARDS*/

			/*Card card1 = new Card(client1.getName()+" "+client1.getLastName(),DEBITO,GOLD,"4568-2361-4597-6543",322,date5Years, date,client1);
			cardRepository.save(card1);
			Card card2 = new Card(client1.getName()+" "+client1.getLastName(),CREDITO,TITANIUM,"4897-9652-1234-6542",569,date5Years, date,client1);
			cardRepository.save(card2);
			Card card3 = new Card(client2.getName()+" "+client2.getLastName(),DEBITO,GOLD,"5649-4587-1315-1234",478,date5Years, date,client2);
			cardRepository.save(card3);*/


		};

	}
}
