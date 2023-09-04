package com.clients.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.clients.project.model.ClientsModel;

public interface ClientRepository extends JpaRepository<ClientsModel, Long> {
    // @Query("""
    // SELECT c FROM Clients WHERE c.id LIKE CONCAT('%', :id, '%')
    // """;)
    // ClientsModel searchClientById(@Param("id") Long id);
    ClientsModel findById(long id);

}
