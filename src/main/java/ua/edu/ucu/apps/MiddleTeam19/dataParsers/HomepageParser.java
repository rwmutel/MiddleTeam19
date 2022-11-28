package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class HomepageParser implements DataParser {
    private String domain;
    private Document page;
    private boolean connected;
    public HomepageParser(String domain) {
        try {
            this.domain = domain;
            page = Jsoup.connect(domain).get();
            connected = true;
        }
        catch (IOException e) {
            connected = false;
        }
    }
    @Override
    public Optional<String> getName() {
        if (connected) {
            return Optional.of(page.title());
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
            Elements el = page.getElementsByAttributeValueMatching(
                    "href",
                    "https:\\/\\/www\\.facebook\\.com.*\\/"
            );
            if (el.size() > 0){
                return Optional.of(el.last().attr("href"));
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
        if (connected) {
            Elements el = page.head().getElementsByAttributeValueMatching("href", ".*favicon\\.ico");
            if (el.size() > 0) {
                String favicon = el.attr("href");
                if (!favicon.startsWith("https://")) {
                    favicon = domain + favicon;
                    favicon = favicon.replace("//favicon", "/favicon");
                }
                return Optional.of(List.of(favicon));
            }
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
