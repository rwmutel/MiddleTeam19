package ua.edu.ucu.apps.MiddleTeam19.company;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public Company getCompanyData(String domain) {
        // to-do:
        // return Company.getCompanyFromDomain(domain);
        return new Company();
    }
}
