package co.com.sofka.parches.useCases;

import co.com.sofka.parches.dtos.ParcheDTO;
import co.com.sofka.parches.mappers.ParcheMapper;
import co.com.sofka.parches.repositories.InscripcionRepository;
import co.com.sofka.parches.repositories.ParcheRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Validated
public class ObtenerParchesUseCase {
    private final ParcheRepository parcheRepository;
    private final ParcheMapper parcheMapper;
    private final InscripcionRepository inscripcionRepository;

    public ObtenerParchesUseCase(ParcheRepository parcheRepository, ParcheMapper parcheMapper, InscripcionRepository inscripcionRepository) {
        this.parcheRepository = parcheRepository;
        this.parcheMapper = parcheMapper;
        this.inscripcionRepository = inscripcionRepository;
    }

    public Flux<ParcheDTO> apply(){
        return parcheRepository.findAll()
                .flatMap(parche -> Mono.just(parcheMapper.mapToDTO().apply(parche)));
    }
}
