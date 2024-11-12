import com.teste.entrevista.staff.controller.ClienteController;
import com.teste.entrevista.staff.model.Cliente;
import com.teste.entrevista.staff.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setDocumento("123.456.789-00");

        when(clienteService.criarCliente(cliente)).thenReturn(cliente);

        ResponseEntity<Cliente> response = clienteController.criarCliente(cliente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }
}
