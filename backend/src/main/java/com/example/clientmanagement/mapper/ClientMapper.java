package com.example.clientmanagement.mapper;

import com.example.clientmanagement.dto.ClientDTO;
import com.example.clientmanagement.model.Client;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import fr.xebia.extras.selma.IoC;
/**
 * Interface de mapping entre l'entité Client et son DTO.
 */
@Mapper(withIgnoreMissing = fr.xebia.extras.selma.IgnoreMissing.ALL, withIoC = IoC.SPRING)
public interface ClientMapper {
    
    @Maps
    ClientDTO asClientDTO(Client client);

    @Maps
    Client asClient(ClientDTO clientDTO);

    default Iterable<ClientDTO> asClientDTOs(Iterable<Client> clients) {
        if (clients == null) return null;
        java.util.List<ClientDTO> dtos = new java.util.ArrayList<>();
        for (Client client : clients) {
            dtos.add(asClientDTO(client));
        }
        return dtos;
    }
}