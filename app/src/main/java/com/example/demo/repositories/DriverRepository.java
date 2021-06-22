package com.example.demo.repositories;

import com.example.demo.models.DriverModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<DriverModel,Long> {

}
