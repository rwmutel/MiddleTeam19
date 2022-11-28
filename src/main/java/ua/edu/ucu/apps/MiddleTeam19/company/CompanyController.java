package ua.edu.ucu.apps.MiddleTeam19.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public String getCompanyData(@RequestParam String name) {
        return name;
//        return Company.getCompanyFromDomain(domain);
    }
}
