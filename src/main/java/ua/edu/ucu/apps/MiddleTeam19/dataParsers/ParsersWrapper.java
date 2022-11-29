package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParsersWrapper implements DataParser {
    List<DataParser> parsers = new ArrayList<>();
    public ParsersWrapper(String domain) {
        if (domain.startsWith("http")) {
            domain = domain.replace("https://", "");
        }
        parsers.add(new HomepageParser(domain));
        parsers.add(new BrandfetchParser(domain));
    }
    @Override
    public Optional<String> getName() {
        Optional<String> name;
        for (DataParser parser: parsers) {
            name = parser.getName();
            if (name.isPresent()) {
                return name;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getTwitterURL() {
        Optional<String> twitterURL;
        for (DataParser parser: parsers) {
            twitterURL = parser.getTwitterURL();
            if (twitterURL.isPresent()) {
                return twitterURL;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> getFacebookURL() {
        Optional<String> facebookURL;
        for (DataParser parser: parsers) {
            facebookURL = parser.getFacebookURL();
            if (facebookURL.isPresent()) {
                return facebookURL;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyLogos() {
        List<String> logos = new ArrayList<>();
        for (DataParser parser: parsers) {
            Optional<List<String>> newLogos = parser.getCompanyLogos();
            newLogos.ifPresent(logos::addAll);
        }
        if (logos.size() > 0) {
            return Optional.of(logos);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getCompanyIcons() {
        List<String> icons = new ArrayList<>();
        for (DataParser parser: parsers) {
            Optional<List<String>> newIcons = parser.getCompanyIcons();
            newIcons.ifPresent(icons::addAll);
        }
        if (icons.size() > 0) {
            return Optional.of(icons);
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
