package com.example.demo.repositories;


import com.example.demo.models.DriverModel;
import com.example.demo.models.RequestModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<RequestModel,Long> {

}