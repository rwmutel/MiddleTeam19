package ua.edu.ucu.apps.MiddleTeam19.dataGetter;

import java.util.List;
import java.util.Optional;

public interface DataParser {
    // a guide on "Optional" attributes: https://www.baeldung.com/java-optional
    // imho it will be cleaner to implement chain of reponsibility with Optional<>
    Optional<String> getName();
    Optional<String> getTwitterURL();
    Optional<String> getFacebookURL();
    Optional<List<String>> getCompanyLogos();
    Optional<List<String>> getCompanyIcons();
    Optional<String> getEmployees();
    Optional<String> getAddress();
}
