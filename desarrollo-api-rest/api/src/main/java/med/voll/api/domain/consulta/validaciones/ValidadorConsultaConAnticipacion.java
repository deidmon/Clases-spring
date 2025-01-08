package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservarConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorConsultaConAnticipacion {
    public void validar(DatosReservarConsulta datos){
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();
        if (diferenciaEnMinutos < 30){
            throw new ValidacionException("Horario seleccionado con menos de 30 minutos de anticipación");
        }

    }
}
