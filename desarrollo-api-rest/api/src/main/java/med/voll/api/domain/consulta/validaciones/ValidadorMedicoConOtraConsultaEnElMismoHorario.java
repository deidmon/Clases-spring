package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservarConsulta;

public class ValidadorMedicoConOtraConsultaEnElMismoHorario {
    private ConsultaRepository repository;
    public void validar(DatosReservarConsulta datos){
        var medicoTieneOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if (medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidacionException("Médico ya tiene otra consulta en esa misma fecha y hora");
        }
    }
}
