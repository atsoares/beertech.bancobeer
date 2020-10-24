package beertech.becks.api.controllers;

import static beertech.becks.api.constants.Constants.STATUS_200_GET_OK;
import static beertech.becks.api.constants.Constants.STATUS_404_NOT_FOUND;
import static beertech.becks.api.constants.Constants.STATUS_500_INTERNAL_SERVER_ERROR;

import beertech.becks.api.exception.payment.PaymentSlipExecutionException;
import beertech.becks.api.service.PaymentSlipService;
import beertech.becks.api.tos.request.PaymentSlipTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-slips")
@Api(value = "Bank Becks Service")
@CrossOrigin(origins = "*")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;


    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND),
                    @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
            })
	@GetMapping
	@ApiOperation(value = "Get all payment slips", authorizations = @Authorization(value = "JWT"))
	public ResponseEntity<Object> getAllPaymentSlips() {
		return new ResponseEntity<>(paymentSlipService.findAll(), HttpStatus.OK);
	}

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND),
                    @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
            })
    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get payment slips by user document number", authorizations = @Authorization(value = "JWT"))
    public ResponseEntity<Object> getPaymentSlipsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(paymentSlipService.findByUserId(userId), HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = STATUS_200_GET_OK),
                    @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND),
                    @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
            })
    @PostMapping("/{paymentCode}")
    @ApiOperation(value = "Executing payment", authorizations = @Authorization(value = "JWT"))
    public ResponseEntity<Object> executePayment(@PathVariable String paymentCode) throws PaymentSlipExecutionException {
        return new ResponseEntity<>(paymentSlipService.executePayment(paymentCode), HttpStatus.OK);
    }

  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = STATUS_200_GET_OK),
          @ApiResponse(code = 404, message = STATUS_404_NOT_FOUND),
          @ApiResponse(code = 500, message = STATUS_500_INTERNAL_SERVER_ERROR)
      })
  @PostMapping("/")
  @ApiOperation(value = "Create a payment", authorizations = @Authorization(value = "JWT"))
  public ResponseEntity<Void> createPaymentSlip(@RequestBody PaymentSlipTO paymentSlipTO)
      throws Exception {
    paymentSlipService.decodeAndSave(paymentSlipTO.getCode());
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
