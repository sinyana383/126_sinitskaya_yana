package PactTests;
import PactStore.*;
import org.junit.*;

import java.util.ArrayList;

public class PactTest extends Assert {
    @Test
    public void addPactToList_validArgs_newCorrectPactOk()
    {
        PactsStore pactsStore = new PactsStore();

        pactsStore.addPactToList("123", "20131231");
        Pact pact = pactsStore.getPact("123");
        assertEquals("123", pact.getNumber());
        assertEquals("20131231", pact.getDate());
        assertEquals(1, pactsStore.getSize());
    }
    @Test
    public void addPactToList_invalidArgs_throwsException()
    {
        PactsStore pactsStore = new PactsStore();

        var exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList(null, "20130323"));
        assertEquals("number and date can't be null", exception.getMessage().toLowerCase());
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", null));
        assertEquals("number and date can't be null", exception.getMessage().toLowerCase());
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("", "20130323"));
        assertEquals("number can't be empty string", exception.getMessage().toLowerCase());

        //INVALID DATE// будет время - добавить проверку дней по месяцам
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", "2013"));
        assertEquals("invalid date", exception.getMessage().toLowerCase());
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", "2013"));
        assertEquals("invalid date", exception.getMessage().toLowerCase());
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", "2013132a"));
        assertEquals("invalid date", exception.getMessage().toLowerCase());
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", "20131327"));
        assertEquals("invalid date", exception.getMessage().toLowerCase());
        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", "20131132"));
        assertEquals("invalid date", exception.getMessage().toLowerCase());
    }
    @Test
    public void addPactToList_notUniqueNumber_throwsException()
    {
        PactsStore pactsStore = new PactsStore();
        pactsStore.addPactToList("123", "20131231");

        var exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPactToList("123", "20130323"));
        assertEquals("number is not unique", exception.getMessage().toLowerCase());
    }

    @Test
    public void addPayment_validArgs_newCorrectPaymentOk()
    {
        PactsStore pactsStore = new PactsStore();

        pactsStore.addPayment
                (100, 1, "Платежное поручение", "123", "20131231");
        Payment newPayment = pactsStore.getPact("123").getPaymentsList().get(0);
        assertEquals(100, newPayment.getAmount());
        assertEquals(1, newPayment.getPaymentNumber());
        assertEquals("Платежное поручение", newPayment.getType());
        assertEquals("123", newPayment.getPactNumber());
        assertEquals("20131231", newPayment.getDate());
    }
    @Test
    public void addPayment_invalidArgs_throwsException()
    {
        PactsStore pactsStore = new PactsStore();

        var exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPayment
                        (100, 1, null, "123", "20131231"));
        assertEquals("payment type can't be null", exception.getMessage().toLowerCase());

        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPayment
                        (100, 1, "Платежное поручение", null, "20131231"));
        assertEquals("number and date can't be null", exception.getMessage().toLowerCase());

        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPayment
                        (100, 1, "Платежное поручение", "123", "20131232"));
        assertEquals("invalid date", exception.getMessage().toLowerCase());

        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPayment
                        (0, 1, "Платежное поручение", "123", "20131231"));
        assertEquals("the amount of money should be positive number", exception.getMessage().toLowerCase());

        exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPayment
                        (-2, 1, "Платежное поручение", "123", "20131231"));
        assertEquals("the amount of money should be positive number", exception.getMessage().toLowerCase());
    }
    @Test
    public void addPayment_addTheSamePayment_throwsException()
    {
        PactsStore pactsStore = new PactsStore();

        pactsStore.addPayment
                (100, 1, "Платежное поручение", "123", "20131231");
        var exception = assertThrows
                (IllegalArgumentException.class, () -> pactsStore.addPayment
                        (120, 1, "Платежное поручение", "123", "20131231"));
        assertEquals("payment with the same args is already exist", exception.getMessage().toLowerCase());
    }
    @Test
    public void deletePayments_deletePayments_completelyDeletedPayments()
    {
        PactsStore pactsStore = new PactsStore();

        pactsStore.addPayment
                (100, 1, "Платежное поручение", "123", "20131231");
        pactsStore.addPayment
                (100, 1, "Банковский ордер", "123", "20131231");
        pactsStore.addPayment
                (100, 2, "Платежное поручение", "123", "20131231");

        PactsStore expectedPactsStore = new PactsStore();
        expectedPactsStore.addPayment
                (100, 2, "Платежное поручение", "123", "20131231");
        ArrayList<Payment> expectedPayments = expectedPactsStore.getPact("123").getPaymentsList();

        pactsStore.deletePayments(1,  "123", "20131231");
        ArrayList<Payment> payments = pactsStore.getPact("123").getPaymentsList();
        assertEquals(1,payments.size());
        assertEquals(expectedPayments.size(),payments.size());
        assertEquals(expectedPayments.get(0).getPaymentNumber(), payments.get(0).getPaymentNumber());
    }
}
