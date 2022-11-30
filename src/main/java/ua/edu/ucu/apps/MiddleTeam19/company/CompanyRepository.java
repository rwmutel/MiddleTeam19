package ua.edu.ucu.apps.MiddleTeam19.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
    ArrayList<Company> findByDomain(String domain);
}
