package com.example.rickAndMorty.factory;
import com.example.cartoon.client.rickAndMortyClient.RickAndMortyClient;
import com.example.cartoon.client.rickAndMortyClient.dto.EpisodeResponse;
import com.example.cartoon.repository.entity.CharacterEntity;
import com.example.cartoon.repository.entity.EpisodeEntity;
import com.example.cartoon.repository.entity.LocationEntity;
import com.example.cartoon.response.RickAndMortyResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartoonFactory {

    public static final String URL_CHARACTER = "https://rickandmortyapi.com/api/character/";
    private final RickAndMortyClient client;

    public RickAndMortyResponse createRickAndMortyResponse(EpisodeResponse episode) {
        val response = RickAndMortyResponse.builder();
        response.episode(episode.getId());
        response.episodeName(episode.getName());
        response.characters(buildCharacters(episode.getCharacters()));

        return response.build();
    }

    private List<RickAndMortyResponse.Character> buildCharacters(List<String> charactersList) {
        List<RickAndMortyResponse.Character> characters = new ArrayList<>();
        for (String character : charactersList) {
            val id = character.split("/");
            val characterResponse = client.getCharacterById(id[id.length - 1]);
            val location = characterResponse.getLocation().getLocationUrl().split("/");

            characters.add(RickAndMortyResponse.Character.builder()
                    .name(characterResponse.getName())
                    .species(characterResponse.getSpecies())
                    .gender(characterResponse.getGender())
                    .image(characterResponse.getImage())
                    .location(buildLocation(location[location.length - 1]))
                    .build());
        }

        return characters;
    }

    private RickAndMortyResponse.Location buildLocation(String id) {
        val locationResponse = client.getLocationById(id);

        return RickAndMortyResponse.Location.builder()
                .name(locationResponse.getName())
                .type(locationResponse.getType())
                .dimension(locationResponse.getDimension())
                .build();
    }

    public EpisodeEntity createEpisodeEntity(EpisodeResponse response) {
        val entity = EpisodeEntity.builder();
        entity.id(response.getId())
                .episodeName(response.getName())
                .airDate(response.getAirDate())
                .episode(response.getEpisode())
                .url(response.getUrl())
                .characters(buildCharacterIdList(response.getCharacters()))
                .created(OffsetDateTime.now());

        return entity.build();
    }

    public String buildCharacterIdList(List<String> characters) {
        return characters.stream()
                .map(s -> s.replace(URL_CHARACTER, ""))
                .collect(Collectors.joining("-"));
    }

    public String buildEpisodesIdList(List<String> episodes) {
        return episodes.stream()
                .map(s -> s.replace("https://rickandmortyapi.com/api/episode/", ""))
                .collect(Collectors.joining("-"));
    }

    public List<CharacterEntity> buildCharacterEntities(List<String> charactersList) {
        List<CharacterEntity> characters = new ArrayList<>();
        for (String character : charactersList) {
            val id = character.split("/");
            val characterResponse = client.getCharacterById(id[id.length - 1]);

            characters.add(CharacterEntity.builder()
                    .id(characterResponse.getId())
                    .name(characterResponse.getName())
                    .species(characterResponse.getSpecies())
                    .gender(characterResponse.getGender())
                    .image(characterResponse.getImage())
                    .dimension(characterResponse.getDimension())
                    .status(characterResponse.getStatus())
                    .url(characterResponse.getUrl())
                    .created(OffsetDateTime.now())
                    .locationId(characterResponse.getLocation().getLocationUrl().length() < 1 ? null :
                            Integer.parseInt(characterResponse.getLocation().getLocationUrl()
                                    .replace("https://rickandmortyapi.com/api/location/", "")))
                    .originId(characterResponse.getOrigin().getLocationUrl().length() < 1 ? null :
                            Integer.parseInt(characterResponse.getOrigin().getLocationUrl()
                                    .replace("https://rickandmortyapi.com/api/location/", "")))
                    .episodesId(buildEpisodesIdList(characterResponse.getEpisode()))
                    .build());
        }

        return characters;
    }

    public LocationEntity buildLocationEntity(String id) {
        val locationResponse = client.getLocationById(id);

        return LocationEntity.builder()
                .id(locationResponse.getId())
                .name(locationResponse.getName())
                .type(locationResponse.getType())
                .dimension(locationResponse.getDimension())
                .url(locationResponse.getUrl())
                .created(OffsetDateTime.now())
                .build();
    }
}