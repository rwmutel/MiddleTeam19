package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class HomepageParser implements DataParser {
    private String domain;
    private Document page;
    public HomepageParser(String domain) {
        try {
            if (!domain.startsWith("https://")) {
                domain = "https://" + domain;
            }
            this.domain = domain;
            page = Jsoup.connect(domain).get();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Optional<String> getName() {
        if (!Objects.isNull(page)) {
            return Optional.of(page.title());
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getTwitterURL() {
        if (!Objects.isNull(page)) {
            Elements el = page.getElementsByAttributeValueMatching(
                    "href",
                    "https:\\/\\/twitter\\.com.*"
            );
            if (el.size() > 0){
                return Optional.of(el.last().attr("href"));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getFacebookURL() {
        if (!Objects.isNull(page)) {
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
        if (!Objects.isNull(page)) {
            Elements el = page.getElementsByAttributeValueMatching("src", ".*logo.*svg");
            if (el.size() > 0) {
                String logo = el.attr("src");
                return Optional.of(List.of(logo));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyIcons() {
        if (!Objects.isNull(page)) {
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
