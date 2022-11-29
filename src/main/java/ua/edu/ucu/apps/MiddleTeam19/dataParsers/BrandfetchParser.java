package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BrandfetchParser implements DataParser {
    // more on Brandfetch: https://docs.brandfetch.com/#8eedcbe0-033b-4baa-a51c-382f8e806d08
    // more on JSONObject: https://www.baeldung.com/java-org-json
    // more on OkHTTP3: https://www.baeldung.com/guide-to-okhttp
    private JSONObject jo;
    private boolean connected;
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
            connected = true;
        }
        catch (IOException e) {
            connected = false;
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Optional<String> getName() {
        if (connected) {
            if (!jo.isNull("name")) {
                return Optional.of(jo.get("name").toString());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getTwitterURL() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getFacebookURL() {
        if (connected) {
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
        return Optional.empty();
    }
}
