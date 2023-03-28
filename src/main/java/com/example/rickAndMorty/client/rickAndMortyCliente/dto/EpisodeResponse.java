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
public class EpisodeResponse {

    private int id;
    private String name;
    private String airDate;
    private String episode;
    private String url;
    private List<String> characters;
    private OffsetDateTime created;
}