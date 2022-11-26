package ua.edu.ucu.apps.MiddleTeam19.dataGetter;

import java.util.List;
import java.util.Optional;

public class HomepageParser implements DataParser {
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
        return Optional.empty();
    }
}
