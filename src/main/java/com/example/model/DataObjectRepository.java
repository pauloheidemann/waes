package com.example.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataObjectRepository extends JpaRepository<DataObject, Long> {}