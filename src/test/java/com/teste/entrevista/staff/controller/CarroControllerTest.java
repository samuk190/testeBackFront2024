import com.teste.entrevista.staff.controller.CarroController;
import com.teste.entrevista.staff.model.Carro;
import com.teste.entrevista.staff.service.CarroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.LocalTime;
public class CarroControllerTest {

    @Mock
    private CarroService carroService;

    @InjectMocks
    private CarroController carroController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarCarro() {
        Carro carro = new Carro();
        carro.setModelo("Fusca");
        carro.setCor("Azul");
        carro.setPlaca("XYZ-1234");

        when(carroService.criarCarro(carro)).thenReturn(carro);

        ResponseEntity<Carro> response = carroController.criarCarro(carro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carro, response.getBody());
    }
}
