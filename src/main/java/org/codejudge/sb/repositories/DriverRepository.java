package org.codejudge.sb.repositories;

import org.codejudge.sb.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author TAYYAB
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
