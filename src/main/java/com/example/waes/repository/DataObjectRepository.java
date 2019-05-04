package com.example.waes.repository;

import com.example.waes.model.DataObject;
import com.example.waes.model.DataObjectPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataObjectRepository extends JpaRepository<DataObject, DataObjectPK> {}