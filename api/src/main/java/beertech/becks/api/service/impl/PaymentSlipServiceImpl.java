package beertech.becks.api.service.impl;

import beertech.becks.api.amqp.RabbitProducer;
import beertech.becks.api.converter.PaymentSlipConverter;
import beertech.becks.api.entities.PaymentSlip;
import beertech.becks.api.exception.payment.PaymentSlipExecutionException;
import beertech.becks.api.repositories.PaymentSlipRepository;
import beertech.becks.api.service.PaymentSlipService;
import beertech.becks.api.service.TransactionService;
import beertech.becks.api.tos.request.TransactionPaymentRequestTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentSlipServiceImpl implements PaymentSlipService {

	@Autowired
	PaymentSlipConverter converter;

	@Autowired
	private PaymentSlipRepository paymentSlipRepository;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private RabbitProducer rabbitProducer;

	@Override
	public List<PaymentSlip> findAll() {
		return paymentSlipRepository.findAll();
	}

	@Override
	public Optional<PaymentSlip> findByUserId(Long userId) {
		return paymentSlipRepository.findByUserId(userId);
	}

	@Override
	public PaymentSlip executePayment(String paymentCode) throws PaymentSlipExecutionException {

		// Logica que vai decodificar o "codigo de barras" e vai retornar um PaymentSlip
		PaymentSlip paymentSlip = paymentSlipRepository.findByCode(paymentCode).orElseThrow(PaymentSlipExecutionException::new);

		try {
			if("001".equals(paymentSlip.getDestinationBankCode())) {

				TransactionPaymentRequestTO transactionPaymentRequestTO = new TransactionPaymentRequestTO();
				transactionPaymentRequestTO.setCurrentAccountCode("accountCode"); // alterar estrutura de dados
				transactionPaymentRequestTO.setDestinationAccountCode(paymentSlip.getDestinationAccountCode());
				transactionPaymentRequestTO.setValue(paymentSlip.getValue());

				transactionService.createPayment(transactionPaymentRequestTO);

			} else {
				if(!rabbitProducer.produceBlockingMessageSuccessfully(paymentCode)) {
					//TODO fazer rollback do debito
					System.out.println("Erro!");
				} else {
					//TODO deletar depois, apenas para testes
					System.out.println("Sucesso!");
				}
			}
		}
		catch(Exception e) {
			throw new PaymentSlipExecutionException(); // repensar essa forma de tratar a exception
		}

		return paymentSlip;
	}

	@Override
	public void decodeAndSave(String code) throws Exception {
		PaymentSlip paymentSlip = converter.codeToPaymentSlip(code);
		paymentSlipRepository.save(paymentSlip);
	}
}
