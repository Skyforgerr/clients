package com.clients.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clients.project.model.ClientsModel;
import com.clients.project.model.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByClient(ClientsModel client);
}
