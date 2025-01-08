package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosReservarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidadorMedicoActivo {
    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservarConsulta datos) {
        if(datos.idMedico()==null){
            return;
        }
        var medicoActivo=repository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidationException("No se puede permitir agendar citas con medicos inactivos en el sistema");
        }
    }
}
