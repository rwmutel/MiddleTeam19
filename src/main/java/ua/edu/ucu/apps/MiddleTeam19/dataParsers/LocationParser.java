package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LocationParser implements DataParser {
    JSONObject geocoded;
    public LocationParser(String name) {
        String endpoint = "https://eu1.locationiq.com/v1/search";
        Dotenv dotenv = Dotenv.configure().load();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(
                        endpoint +
                        "?key=" + dotenv.get("LOCATION_IQ_KEY") +
                        "&q=" + name +
                        "&format=json"
                )
                .build();
        try {
            Response response = client.newCall(request).execute();
            geocoded = new JSONArray(response.body().string()).getJSONObject(0);
            System.out.println(geocoded.toString(2));
            response.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Optional<String> getName() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getTwitterURL() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getFacebookURL() {
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyLogos() {
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyIcons() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getEmployees() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getAddress() {
        if (!Objects.isNull(geocoded)) {
            if (geocoded.length() > 0) {
                String address = geocoded.getString("display_name");
                address = address.substring(address.indexOf(',')+2);
                return Optional.of(address);
            }
        }
        return Optional.empty();
    }
}
