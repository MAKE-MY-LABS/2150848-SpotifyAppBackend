package com.capstone.Spotify.App.ServiceImpl;

import com.capstone.Spotify.App.exception.SongExistException;
import com.capstone.Spotify.App.model.SpotifySong;
import com.capstone.Spotify.App.model.SpotifySongList;
import com.capstone.Spotify.App.repository.SpotifySongRepository;
import com.capstone.Spotify.App.service.SpotifySongService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.capstone.Spotify.App.exception.SongNotFoundException;

import java.util.List;

/**
 * The class should implement the SongService interface.
 * If a song is not found, it should throw SongNotFoundException.
 * If a song already exists, it should throw a SongAlreadyExistsException.
 * Use a RestTemplate bean to make calls to a third-party API.
 * Utilize RestTemplate to retrieve stocks from the third-party API, GAANA_API_URL.
 * The value of GAANA_API_URL should be obtained from application.properties.
 */
public class SpotifySongServiceImpl implements SpotifySongService {


        @Autowired
        private SpotifySongRepository spotifySongRepository;

        @Autowired
        private RestTemplate restTemplate;

        // api url from properties file
        @Value("${GAANA_API_URL}")
        private String gaanaAPIUrl;

        // backup api url
        @Value("${BACKUP_API_URL}")
        private String backupAPIUrl;

        @Override
        public SpotifySong saveSong(SpotifySong spotifySong) throws SongExistException {
            if (spotifySongRepository.existsById(String.valueOf(spotifySong.getEntity_id()))) {
                throw new SongExistException("Song already exists");
            }
            return spotifySongRepository.save(spotifySong);
        }

        @Override
        public String deleteSong(String entityId) throws SongNotFoundException {
            if (!spotifySongRepository.existsById((entityId))) {
                throw new SongNotFoundException("Song not found");
            }
            spotifySongRepository.deleteById((entityId));
            return "Song deleted";
        }

        @Override
        public List<SpotifySong> getSavedSongs() {
            return spotifySongRepository.findAll();
        }

        @Override
        public List<SpotifySong> getAllSongs() throws JSONException {
            return getGaanaSongs();
        }

        public List<SpotifySong> getGaanaSongs() throws JSONException {
            /**
             * construct the URL and utilize RestTemplate to retrieve stocks
             * from the third-party API, GAANA_API_URL.
             * Employ a RestTemplate bean to make calls to the third-party API.
             * Obtain the value of GAANA_API_URL from application.properties.
             */

            JSONObject response = null;

            // use JSONObject from org.json to parse the response from the API surround with
            // try catch
            // try {
            // response = restTemplate.postForObject(gaanaAPIUrl, null, JSONObject.class);
            // } catch (Exception e) {
            // call backup API
            // do {
            ResponseEntity<String> backupResponse = restTemplate.exchange(
                    backupAPIUrl,
                    HttpMethod.GET, null, String.class);

            response = new JSONObject(backupResponse.getBody());
            // } while (!response.has("entities"));
            // }

            ObjectMapper mapper = new ObjectMapper();
            List<SpotifySong> songs;
            try {
                songs = mapper.readValue(response.get("entities").toString(),
                        new TypeReference<List<SpotifySong>>() {
                        });
            } catch (JsonProcessingException | JSONException e) {
                songs = null;
                e.printStackTrace();
            }

            SpotifySongList songList = new SpotifySongList(songs);

            // convert the response.entities to a SongList object
            // SongList songList = new
            // SongList(response.getJSONArray("entities").toString());

            // HttpHeaders headers = new HttpHeaders();
            // headers.set("Host", "www.example.com");
            // headers.set("User-Agent", "whatever");

            return songList.getSongs();

        }
    }



