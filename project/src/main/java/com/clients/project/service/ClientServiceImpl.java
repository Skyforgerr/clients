package com.clients.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clients.project.model.ClientsModel;
import com.clients.project.model.Email;
import com.clients.project.model.PhoneNumber;
import com.clients.project.repository.ClientRepository;
import com.clients.project.repository.EmailRepository;
import com.clients.project.repository.PhoneNumberRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public ClientsModel addNewClient(ClientsModel client) {
        return clientRepository.save(client);
    }

    @Override
    public List<ClientsModel> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void addPhoneNumberToClient(Long clientId, String phoneNumber) {
        ClientsModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден..."));
        PhoneNumber newPhoneNumber = PhoneNumber.builder().number(phoneNumber).client(client).build();
        phoneNumberRepository.save(newPhoneNumber);
    }

    @Override
    public void addEmailToClient(Long clientId, String mail) {
        ClientsModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден..."));
        Email newEmail = Email.builder().mail(mail).client(client).build();
        emailRepository.save(newEmail);
    }

    @Override
    public List<PhoneNumber> getPhoneNumbersForClient(Long clientId) {
        ClientsModel clientsModel = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден..."));
        return phoneNumberRepository.findByClient(clientsModel);
    }

    @Override
    public List<Email> getEmailsForClient(Long clientId) {
        ClientsModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Клиент не найден..."));
        return emailRepository.findByClient(client);
    }
}
