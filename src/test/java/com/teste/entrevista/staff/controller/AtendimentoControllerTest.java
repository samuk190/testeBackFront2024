import com.teste.entrevista.staff.controller.AtendimentoController;
import com.teste.entrevista.staff.model.Atendimento;
import com.teste.entrevista.staff.service.AtendimentoService;
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

public class AtendimentoControllerTest {

    @Mock
    private AtendimentoService atendimentoService;

    @InjectMocks
    private AtendimentoController atendimentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarAtendimento() {
        Atendimento atendimento = new Atendimento();
        atendimento.setDataAgendamento(LocalDate.of(2024, 11, 15));
        atendimento.setHorarioAgendamento(LocalTime.of(9, 0));

        when(atendimentoService.criarAtendimento(atendimento)).thenReturn(atendimento);

        ResponseEntity<Atendimento> response = atendimentoController.criarAtendimento(atendimento);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atendimento, response.getBody());
    }
}
