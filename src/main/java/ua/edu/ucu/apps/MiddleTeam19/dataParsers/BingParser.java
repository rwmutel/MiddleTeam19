package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class BingParser implements DataParser {
    JSONObject jo;
    boolean connected;
    public BingParser(String domain) {
        Dotenv dotenv = Dotenv.configure().load();
        String endpoint = "https://api.bing.microsoft.com/v7.0/search";
        String query = domain.replace("https://", "");
        Response response;
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(endpoint + "?" + "q=" + query + "&" + "mkt=en-GB" + "&" + "count=1")
                .addHeader("Ocp-Apim-Subscription-Key", dotenv.get("BING_KEY_1"))
                .build();
        try {
            response = client.newCall(request).execute();
            jo = new JSONObject(response.body().string());
            System.out.println(jo.toString(2));
            response.close();
            connected = true;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            connected = false;
        }
    }
    @Override
    public Optional<String> getName() {
        if (connected) {
            String name = jo
                    .getJSONObject("webPages")
                    .getJSONArray("value")
                    .getJSONObject(0)
                    .getString("name");
            return Optional.of(name);
        }
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
        return Optional.empty();
    }
}
