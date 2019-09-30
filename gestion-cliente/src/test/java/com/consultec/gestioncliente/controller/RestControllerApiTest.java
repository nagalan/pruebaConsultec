// Tester del Controlador Rest del Microservicio.
package com.consultec.gestioncliente.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import com.consultec.gestioncliente.domainobjects.Customer;
import com.consultec.gestioncliente.domainobjects.Office;
import com.consultec.gestioncliente.domainobjects.Status;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RestControllerApiTest {

    @Autowired
    private MockMvc mvc;

    public Office newOffice(Long id, String name) {
        Office newOffice = new Office();
        newOffice.setId(id);
        newOffice.setDateCreate(new Date());
        newOffice.setName(name);
        return newOffice;
    }

    public Customer newCustomer(Long id, String name, String lastName, String userName, String password, String email,
            String address, Long officeId, String officeName) {
        Customer newCust = new Customer();
        newCust.setId(id);
        newCust.setDateCreate(new Date());
        newCust.setName(name);
        newCust.setLastName(lastName);
        newCust.setUserName(userName);
        newCust.setPassword(password);
        newCust.setEmail(email);
        newCust.setAddress(address);
        newCust.setStatus(Status.ENABLED);
        newCust.setOffice(new Office());
        newCust.getOffice().setId(officeId);
        newCust.getOffice().setName(officeName);
        return newCust;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void failedLoginfindAllOffices() throws Exception {

        mvc.perform(get("/offices")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulfindAllOffices() throws Exception {

        mvc.perform(get("/offices")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    public void failedLoginNewOffice() throws Exception {

        mvc.perform(post("/offices")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulNewOffice() throws Exception {

        mvc.perform(post("/offices").content(asJsonString(newOffice(0L, "oficinaNueva")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.data.id").exists());
    }

    @Test
    public void failedLoginFindOfficeById() throws Exception {

        mvc.perform(get("/offices/1")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulfindOfficeById() throws Exception {

        mvc.perform(get("/offices/1")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"));
    }

    @Test
    public void failedLoginFindOfficeByQuery() throws Exception {

        mvc.perform(get("/offices/query?name=concesionario2")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulFindOfficeByQuery() throws Exception {

        mvc.perform(get("/offices/query?name=concesionario2")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("2"));
    }

    @Test
    public void failedLoginUpdateOffice() throws Exception {
        mvc.perform(put("/offices/{id}", 3).content(asJsonString(newOffice(3L, "oficinaNombreNew")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulUpdateOffice() throws Exception {
        mvc.perform(put("/offices/{id}", 3).content(asJsonString(newOffice(3L, "oficinaNombreNew")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("oficinaNombreNew"));
    }

    @Test
    public void failedLoginDeleteOffice() throws Exception {
        mvc.perform(delete("/offices/{id}", 3)).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulDeleteOffice() throws Exception {
        mvc.perform(delete("/offices/{id}", 3)).andExpect(status().isOk());
    }

    @Test
    public void failedLoginFindAllCustomers() throws Exception {

        mvc.perform(get("/customers")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulfindAllCustomers() throws Exception {

        mvc.perform(get("/customers")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    public void failedLoginNewCustomer() throws Exception {

        mvc.perform(post("/customers")
                .content(asJsonString(newCustomer(0L, "nombrePru", "apellidoPru", "userNamePru", "1234",
                        "emailPru@gmail.com", "direccionPru", new Long("1"), "concesionario1")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulnewCustomer() throws Exception {

        mvc.perform(post("/customers")
                .content(asJsonString(newCustomer(0L, "nombrePru", "apellidoPru", "userNamePru", "1234",
                        "emailPru@gmail.com", "direccionPru", new Long("1"), "concesionario1")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.data.id").exists());
    }

    @Test
    public void failedLoginFindCustomerById() throws Exception {

        mvc.perform(get("/customers/1")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulFindCustomerById() throws Exception {

        mvc.perform(get("/customers/1")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value("1"));
    }

    @Test
    public void failedLoginFindCustomerByQuery() throws Exception {

        mvc.perform(get("/customers/query?name=nombre2")).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulFindCustomerByQuery() throws Exception {

        mvc.perform(get("/customers/query?name=nombre2")).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id").value("2"));
    }

    @Test
    public void failedLoginUpdateCustomer() throws Exception {
        mvc.perform(put("/customers/{id}", 2)
                .content(asJsonString(newCustomer(2L, "nombreNew", "apellidoNew", "userNameNew", "1234",
                        "emailNew@gmail.com", "direccionNew", new Long("1"), "concesionario1")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulUpdateCustomer() throws Exception {
        mvc.perform(put("/customers/{id}", 2)
                .content(asJsonString(newCustomer(2L, "nombreNew", "apellidoNew", "userNameNew", "1234",
                        "emailNew@gmail.com", "direccionNew", new Long("1"), "concesionario1")))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value("nombreNew"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.lastName").value("apellidoNew"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("emailNew@gmail.com"));
    }

    @Test
    public void failedLoginDeleteEmployeeAPI() throws Exception {
        mvc.perform(delete("/customers/{id}", 1)).andExpect(status().isUnauthorized());
    }

    @WithMockUser("USER")
    @Test
    public void successfulDeleteEmployeeAPI() throws Exception {
        mvc.perform(delete("/customers/{id}", 4)).andExpect(status().isOk());
    }

}
