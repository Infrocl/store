package com.example.oxygen.repository;

import com.example.oxygen.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Long> {
    @Query(value = "SELECT a.id, a.shipment_date, a.delivery_date, a.user_id" +
            " FROM Packages a LEFT JOIN Users b on a.user_id=b.id" +
            " WHERE a.user_id = :id", nativeQuery = true)
    List<Package> findAllByUserId(@Param("id") long userId);

}
