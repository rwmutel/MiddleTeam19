package ua.edu.ucu.apps.MiddleTeam19.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public Company getCompanyData(@RequestParam String name) {
        return this.companyService.addCompany(name);
    }
    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return this.companyService.getAllCompanies();
    }

    @PostMapping
    public Company updateCompanyData(@RequestParam Map<String, String> reqParam) {
        return this.companyService.updateCompany(reqParam);
    }
}
