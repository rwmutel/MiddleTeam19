package ua.edu.ucu.apps.MiddleTeam19.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CompanyService {
    CompanyRepository companyRepository;
    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company addCompany(String name) {
        List<Company> tries;
        Company res;
        try {
            tries = this.companyRepository.findByDomain(name);
            res = tries.get(0);
        } catch (Exception e){
            res = Company.getCompanyFromDomain(name);
            this.companyRepository.save(res);
        }
        return res;
    }
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Company updateCompany(Map<String, String> infoToUpdate) {
        List<Company> companiesFromDb = this.companyRepository.findByDomain(infoToUpdate.get("domain"));
        Company companyToUpdate = companiesFromDb.get(0);
        if (infoToUpdate.containsKey("address")) {
            companyToUpdate.setAddress(infoToUpdate.get("address"));
        }
        if (infoToUpdate.containsKey("name")) {
            companyToUpdate.setName(infoToUpdate.get("name"));
        }
        if (infoToUpdate.containsKey("twitterURL")) {
            companyToUpdate.setTwitterURL(infoToUpdate.get("twitterURL"));
        }
        if (infoToUpdate.containsKey("facebookURL")) {
            companyToUpdate.setFacebookURL(infoToUpdate.get("facebookURL"));
        }
        if (infoToUpdate.containsKey("companylogos")) {
            companyToUpdate.setCompanyLogos(Collections.singletonList(infoToUpdate.get("companylogos")));
        }
        if (infoToUpdate.containsKey("companyicons")) {
            companyToUpdate.setCompanyIcons(Collections.singletonList(infoToUpdate.get("companyicons")));
        }
        if (infoToUpdate.containsKey("employees")) {
            companyToUpdate.setEmployees(infoToUpdate.get("employees"));
        }
        this.companyRepository.save(companyToUpdate);
        return companyToUpdate;
    }
}
