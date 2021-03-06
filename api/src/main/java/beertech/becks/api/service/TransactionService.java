package beertech.becks.api.service;

import java.math.BigDecimal;

import beertech.becks.api.entities.Account;
import beertech.becks.api.entities.PaymentSlip;
import beertech.becks.api.exception.account.AccountDoesNotExistsException;
import beertech.becks.api.exception.account.AccountDoesNotHaveEnoughBalanceException;
import beertech.becks.api.model.PaymentCategory;
import beertech.becks.api.model.TypeOperation;
import beertech.becks.api.tos.request.TransferRequestTO;
import beertech.becks.api.tos.response.StatementResponseTO;

public interface TransactionService {

	Account createDeposit(String accountCode, BigDecimal value) throws AccountDoesNotExistsException;

	Account createWithdrawal(String accountCode, BigDecimal value)
			throws AccountDoesNotExistsException, AccountDoesNotHaveEnoughBalanceException;

	Account createTransfer(String accountCode, TransferRequestTO transferRequestTO, TypeOperation typeOperation, PaymentCategory paymentCategory)
			throws AccountDoesNotExistsException, AccountDoesNotHaveEnoughBalanceException;

	Account createPayment(PaymentSlip paymentSlip)
			throws AccountDoesNotExistsException, AccountDoesNotHaveEnoughBalanceException;

	Account createPaymentToExternalBank(PaymentSlip paymentSlip)
			throws AccountDoesNotExistsException, AccountDoesNotHaveEnoughBalanceException;

	StatementResponseTO getStatements(String accountCode) throws AccountDoesNotExistsException;
}
