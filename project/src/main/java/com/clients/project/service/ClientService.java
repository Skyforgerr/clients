package com.clients.project.service;

import java.util.List;

import com.clients.project.model.ClientsModel;
import com.clients.project.model.Email;
import com.clients.project.model.PhoneNumber;

public interface ClientService {
    public ClientsModel addNewClient(ClientsModel client);

    public List<ClientsModel> getAllClients();

    public void addPhoneNumberToClient(Long clientId, String phoneNumber);

    public void addEmailToClient(Long clientId, String mail);

    public List<PhoneNumber> getPhoneNumbersForClient(Long clientId);

    public List<Email> getEmailsForClient(Long clientId);
}
