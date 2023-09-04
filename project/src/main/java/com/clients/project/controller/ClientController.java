package com.clients.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clients.project.model.ClientsModel;
import com.clients.project.model.Email;
import com.clients.project.model.PhoneNumber;
import com.clients.project.request.ClientRequest;
import com.clients.project.service.ClientService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/addclient") // Добавление клиента
    public String addClient(@RequestBody ClientRequest request) {
        ClientsModel clientsModel = ClientsModel.builder()
                .name(request.getName())
                .build();
        clientService.addNewClient(clientsModel);
        return "Новый клиент был добавлен.";
    }

    @GetMapping("/viewclients") // Просмотр списка клиентов
    public List<ClientsModel> viewClients() {
        return clientService.getAllClients();
    }

    @PostMapping("/{clientId}/addPhoneNumber") // Добавление нового номера телефона в контакты определенного клиента
    public ResponseEntity<String> addPhoneNumberToClient(@PathVariable Long clientId, @RequestBody String phoneNumber) {
        try {
            clientService.addPhoneNumberToClient(clientId, phoneNumber);
            return ResponseEntity.ok("Номер телефона был добавлен.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{clientId}/addEmail") // Добавление новой электронной почты в контакты определенного клиента
    public ResponseEntity<String> addEmailToClient(@PathVariable Long clientId, @RequestBody String mail) {
        try {
            clientService.addEmailToClient(clientId, mail);
            return ResponseEntity.ok("Электронная почта была добавлена.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clientId}/phoneNumbers") // Просмотр телефонны номеров из списка контактов определенного клиента
    public ResponseEntity<List<PhoneNumber>> getContactsOfClient(@PathVariable Long clientId) {
        try {
            List<PhoneNumber> phoneNumbers = clientService.getPhoneNumbersForClient(clientId);
            return ResponseEntity.ok(phoneNumbers);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{clientId}/emails") // Просмотр адресов электронных почт из списка контактов определенного клиента
    public ResponseEntity<List<Email>> getEmailsOfClient(@PathVariable Long clientId) {
        try {
            List<Email> emails = clientService.getEmailsForClient(clientId);
            return ResponseEntity.ok(emails);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{clientId}/PhoneNumbersAndEmails") // Просмотр и адресов электронной почты и номеров телефонов из
                                                    // списка контактов определенного клиента
    public ResponseEntity<Map<String, List<?>>> getPhoneNumbersAndEmailsOfClient(@PathVariable Long clientId) {
        try {
            Map<String, List<?>> result = new HashMap<>();

            List<PhoneNumber> phoneNumbers = clientService.getPhoneNumbersForClient(clientId);
            List<Email> emails = clientService.getEmailsForClient(clientId);

            result.put("phoneNumbers", phoneNumbers);
            result.put("emails", emails);

            return ResponseEntity.ok(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
