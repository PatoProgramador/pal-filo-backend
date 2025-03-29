package com.palfilo.demo.daos;

import com.palfilo.demo.models.LocationPermissions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationPermissionsRepository extends JpaRepository<LocationPermissions, Integer> {
}
