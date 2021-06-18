package org.codejudge.sb.repositories;

import org.codejudge.sb.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author TAYYAB
 */

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
