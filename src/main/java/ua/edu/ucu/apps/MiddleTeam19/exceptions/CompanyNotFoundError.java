package ua.edu.ucu.apps.MiddleTeam19.exceptions;

public class CompanyNotFoundError extends Exception{
    public CompanyNotFoundError(String errorMessage) {
        super(errorMessage);
    }
}
