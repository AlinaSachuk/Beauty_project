package com.beauty_project;

import com.beauty_project.domain.Administrator;
import com.beauty_project.domain.CosmeticProduct;
import com.beauty_project.domain.Customer;
import com.beauty_project.domain.Employee;
import com.beauty_project.domain.Procedure;
import com.beauty_project.domain.Status;
import com.beauty_project.domain.Visit;

import java.sql.Date;

public final class Utils {
    private Utils() {
    }

    public static Status createStatus(String status, int percent, int id) {
        Status testStatus = new Status();
        testStatus.setStatus(status);
        testStatus.setPercent(percent);
        testStatus.setId(id);
        return testStatus;
    }

    public static Procedure createProcedure(Integer id, String serviceName, int duration, int price, String description) {
        Procedure procedure = new Procedure();
        procedure.setId(id);
        procedure.setServiceName(serviceName);
        procedure.setDuration(duration);
        procedure.setPrice(price);
        procedure.setDescription(description);
        return procedure;
    }

    public static Employee createEmployee(Integer id, String employeeName, String position
            , String education, String workExperience) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmployeeName(employeeName);
        employee.setPosition(position);
        employee.setEducation(education);
        employee.setWorkExperience(workExperience);
        return employee;
    }

    public static CosmeticProduct createCosmeticProduct(Integer id, String productName, String manufacture
            , String countryOfOrigin) {
        CosmeticProduct product = new CosmeticProduct();
        product.setId(id);
        product.setProductName(productName);
        product.setManufacture(manufacture);
        product.setCountryOfOrigin(countryOfOrigin);
        return product;
    }

    public static Customer createCustomer(Integer id, String customerName, Date birthDate, String telephoneNumber
            , String email, String instagramAccount, String status, String password) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setCustomerName(customerName);
        customer.setBirthDate(birthDate);
        customer.setTelephoneNumber(telephoneNumber);
        customer.setEmail(email);
        customer.setInstagramAccount(instagramAccount);
        customer.setStatus(status);
        customer.setPassword(password);
        return customer;
    }

    public static Visit createVisit(Integer id, Date dateOfVisit, double finalPrice, int customerId) {
        Visit visit = new Visit();
        visit.setId(id);
        visit.setDateOfVisit(dateOfVisit);
        visit.setFinalPrice(finalPrice);
        visit.setCustomerId(customerId);
        return visit;
    }

    public static Administrator createAdministrator(int id, String login, String password, String role) {
        Administrator administrator = new Administrator();
        administrator.setId(id);
        administrator.setLogin(login);
        administrator.setPassword(password);
        administrator.setRole(role);
        return administrator;
    }
}