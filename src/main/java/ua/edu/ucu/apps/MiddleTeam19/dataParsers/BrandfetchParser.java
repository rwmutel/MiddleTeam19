package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BrandfetchParser implements DataParser {
    // more on Brandfetch: https://docs.brandfetch.com/#8eedcbe0-033b-4baa-a51c-382f8e806d08
    // more on JSONObject: https://www.baeldung.com/java-org-json
    // more on OkHTTP3: https://www.baeldung.com/guide-to-okhttp
    private JSONObject jo;
    public BrandfetchParser(String domain) {
        Dotenv dotenv = Dotenv.configure().load();
        Response response;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url("https://api.brandfetch.io/v2/brands/" + domain)
                .addHeader("Authorization",
                        "Bearer " + dotenv.get("BRANDFETCH_BEARER"))
                .build();
        try {
            response = client.newCall(request).execute();
            jo = new JSONObject(response.body().string());
            System.out.println(jo.toString(2));
            response.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Optional<String> getName() {
        if (!Objects.isNull(jo)) {
            if (!jo.isNull("name")) {
                return Optional.of(jo.get("name").toString());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getTwitterURL() {
        if (!Objects.isNull(jo)) {
            JSONArray links = (JSONArray) jo.get("links");
            for (int i = 0; i < links.length(); i++) {
                if (Objects.equals(links.getJSONObject(i).getString("name"), "twitter")) {
                    String url = links.getJSONObject(i).getString("url");
                    return Optional.of(url);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getFacebookURL() {
        if (!Objects.isNull(jo)) {
            JSONArray links = (JSONArray) jo.get("links");
            for (int i = 0; i < links.length(); i++) {
                if (Objects.equals(links.getJSONObject(i).getString("name"), "facebook")) {
                    String url = links.getJSONObject(i).getString("url");
                    return Optional.of(url);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyLogos() {
        if (!Objects.isNull(jo)) {
            JSONArray logos = (JSONArray) jo.get("logos");
            List<String> logosArray = new ArrayList<>();
            for (int i = 0; i < logos.length(); i++) {
                if (Objects.equals(logos.getJSONObject(i).getString("type"), "logo")) {
                    JSONArray formats = (JSONArray) logos.getJSONObject(i).get("formats");
                    logosArray.add(formats.getJSONObject(0).getString("src"));
                }
            }
            return Optional.of(logosArray);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyIcons() {
        if (!Objects.isNull(jo)) {
            JSONArray logos = (JSONArray) jo.get("logos");
            List<String> iconArray = new ArrayList<>();
            for (int i = 0; i < logos.length(); i++) {
                if (Objects.equals(logos.getJSONObject(i).getString("type"), "icon")) {
                    JSONArray formats = (JSONArray) logos.getJSONObject(i).get("formats");
                    iconArray.add(formats.getJSONObject(0).getString("src"));
                }
            }
            return Optional.of(iconArray);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getEmployees() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getAddress() {
        return Optional.empty();
    }
}
