package com.example.rickAndMorty.client.rickAndMortyCliente.impl;

import com.example.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RickAndMortyClientImpl implements RickAndMortyClient {

    @Autowired
    private ClientConfig clientConfig;

    @Override
    public CharacterResponse getCharacterById(String id) {
        return clientConfig.getRestTemplate().getForObject(
                "/character/{id}",
                CharacterResponse.class,
                id
        );
    }

    @Override
    public EpisodeResponse getEpisodeById(String id) {
        return clientConfig.getRestTemplate().getForObject(
                "/episode/{id}",
                EpisodeResponse.class,
                id
        );
    }

    @Override
    public LocationResponse getLocationById(String id) {
        return clientConfig.getRestTemplate().getForObject(
                "/location/{id}",
                LocationResponse.class,
                id
        );
    }
}