package com.example.rickAndMorty.client.rickAndMortyCliente.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponse {

    private int id;
    private String name;
    private String type;
    private String dimension;
    private String url;
    private List<String> residents;
    private OffsetDateTime created;
}