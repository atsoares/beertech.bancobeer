package beertech.becks.api.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {
/*
  @Mock private TransactionService transactionService;

  @InjectMocks private TransactionController transactionController;

  private MockMvc mockMvc;
  private MockHttpServletResponse response;
  private TransactionRequestTO requestTO;

  @BeforeEach
  void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
  }

  @Test
  void postTransactionCreditSuccessfully() throws Exception {
    givenValidTransactionRequestTO(TypeOperation.DEPOSITO);
    whenCallPostTransaction();
    thenExpectCreatedStatus();
    thenExpectTransactionServiceCreateTransactionCall();
  }

  @Test
  void postTransactionDebitSuccessfully() throws Exception {
    givenValidTransactionRequestTO(TypeOperation.SAQUE);
    whenCallPostTransaction();
    thenExpectCreatedStatus();
    thenExpectTransactionServiceCreateTransactionCall();
  }

  @Test
  void postTransactionTransferSuccessfully() throws Exception {
    givenValidTransactionRequestTO(TypeOperation.TRANSFERENCIA);
    whenCallPostTransaction();
    thenExpectCreatedStatus();
    thenExpectTransactionServiceCreateTransactionCall();
  }

  @Test
  void postTransactionUnsuccessfully() throws Exception {
    givenInvalidTransactionRequestTO();
    whenCallPostTransaction();
    thenExpectBadRequestStatus();
    thenExpectTransactionServiceCreateTransactionNoCall();
  }

  private void givenValidTransactionRequestTO(TypeOperation operation) {
    requestTO = new TransactionRequestTO();
    requestTO.setOperation(operation.getDescription());
    requestTO.setValue(BigDecimal.valueOf(12));
    requestTO.setOriginAccountCode("123456");
    if (operation.equals(TypeOperation.TRANSFERENCIA)) {
      requestTO.setDestinationAccountCode("654321");
    }
  }

  private void givenInvalidTransactionRequestTO() {
    requestTO = null;
  }

  private void whenCallPostTransaction() throws Exception {
    response =
        mockMvc
            .perform(
                post("/transactions")
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .content(objectToJson(requestTO)))
            .andReturn()
            .getResponse();
  }

  private void thenExpectCreatedStatus() {
    assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
  }

  private void thenExpectBadRequestStatus() {
    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  private void thenExpectTransactionServiceCreateTransactionCall()
          throws AccountDoesNotExistsException, InvalidTransactionOperationException {
    verify(transactionService, times(1)).createTransaction(requestTO);
  }

  private void thenExpectTransactionServiceCreateTransactionNoCall() throws AccountDoesNotExistsException, InvalidTransactionOperationException {
    verify(transactionService, times(0)).createTransaction(requestTO);
  }

  private String objectToJson(Object o) throws JsonProcessingException {
    return new ObjectMapper().writeValueAsString(o);
  }*/
}
