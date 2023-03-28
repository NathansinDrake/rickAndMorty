
import com.example.rickAndMorty.client.*;

public interface RickAndMortyClient {

    CharacterResponse getCharacterById(String id);
    EpisodeResponse getEpisodeById(String id);
    LocationResponse getLocationById(String id);
}