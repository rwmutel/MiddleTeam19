package ua.edu.ucu.apps.MiddleTeam19.company;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Table(name = "companies")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    @GeneratedValue
    @Id
    private int id;
    private String domain;
    private String name;
    private String twitterURL;
    private String facebookURL;
    private List<String> companyLogos;
    private List<String> companyIcons;
    private String employees;
    private String address;
//    public static Company getCompanyFromDomain(String domain) {
//      this method is a static factory and should use separate class DataGetter,
//      which uses different DataParser's to acquire the data
//    }
}
