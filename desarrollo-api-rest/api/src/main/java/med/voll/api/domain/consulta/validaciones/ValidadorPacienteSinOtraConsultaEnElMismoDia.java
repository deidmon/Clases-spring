package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservarConsulta;

public class ValidadorPacienteSinOtraConsultaEnElMismoDia {
    private ConsultaRepository repository;

    public void validar(DatosReservarConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if (pacienteTieneOtraConsultaEnElDia){
            throw new ValidacionException("El Paciente ya tiene una consulta reservada por ese día");
        }
    }

}
