package com.clients.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clients.project.model.ClientsModel;
import com.clients.project.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    List<PhoneNumber> findByClient(ClientsModel clientsModel);

}
