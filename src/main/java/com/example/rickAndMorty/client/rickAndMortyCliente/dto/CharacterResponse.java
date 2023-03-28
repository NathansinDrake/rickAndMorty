package com.example.rickAndMorty.client.rickAndMortyCliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CharacterResponse {

    private int id;
    private String name;
    private String status;
    private String species;
    private String gender;
    private String image;
    private String dimension;
    private String url;
    private Location origin;
    private Location location;
    private List<String> episode;
    private OffsetDateTime created;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Location{
        @JsonProperty("name")
        private String locationName;
        @JsonProperty("url")
        private String locationUrl;
    }
}