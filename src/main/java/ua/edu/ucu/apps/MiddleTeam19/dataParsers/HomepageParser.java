package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HomepageParser implements DataParser {
    private Document page;
    private boolean connected;
    public HomepageParser(String domain) {
        try {
            page = Jsoup.connect(domain).get();
            connected = true;
        }
        catch (IOException e) {
            connected = false;
        }
    }
    @Override
    public Optional<String> getName() {
        return Optional.of(page.title());
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
