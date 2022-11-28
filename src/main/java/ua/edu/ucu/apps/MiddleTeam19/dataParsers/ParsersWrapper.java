package ua.edu.ucu.apps.MiddleTeam19.dataParsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParsersWrapper implements DataParser {
    List<DataParser> parsers = new ArrayList<>();
    // here we have somehow to implement chain of responsibility without
    // reflections and iterating over attributes of Company class.
    // When we failed to get particular data with one type of parser, we have to proceed with another
    public ParsersWrapper(String domain) {
        parsers.add(new HomepageParser(domain));
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
