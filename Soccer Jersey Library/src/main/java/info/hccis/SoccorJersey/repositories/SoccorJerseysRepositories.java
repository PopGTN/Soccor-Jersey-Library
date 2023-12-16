package info.hccis.SoccorJersey.repositories;

import info.hccis.SoccorJersey.jpa.entity.SoccorJerseys;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SoccorJerseysRepositories extends CrudRepository<SoccorJerseys, Integer> {
    List<SoccorJerseys> findByNameCountry(String name);
    List<SoccorJerseys> findByNamePlayer(String name);


}
