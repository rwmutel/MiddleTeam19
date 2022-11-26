package ua.edu.ucu.apps.MiddleTeam19.company;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@Table
@Entity
public class Company {
    @Id
    @GeneratedValue
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
//      this method should use separate class DataGetter,
//      which uses different DataParser's to acquire the data
//    }

}
