package one.digitalInnovation.desafio_gft.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository < Cliente , Long >{
    // Interface que prove todos os métodos de acesso a dados da determinada Entity


}
