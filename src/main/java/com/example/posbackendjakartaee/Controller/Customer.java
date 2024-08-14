package com.example.posbackendjakartaee.Controller;

import com.example.posbackendjakartaee.DTO.CustomerDTO;
import com.example.posbackendjakartaee.Dao.CustomerDao;
import com.example.posbackendjakartaee.Dao.IMPL.CustomerDAOIMPL;
import jakarta.json.bind.Jsonb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.json.bind.JsonbBuilder;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = ("/customer"))
public class Customer extends HttpServlet {
    Connection connection;
    public void init() throws ServletException {
        try {
            var driver = getServletContext().getInitParameter("driver-class");
            var dburl = getServletContext().getInitParameter("dbURL");
            var username = getServletContext().getInitParameter("dbUserName");
            var password = getServletContext().getInitParameter("dbPassword");

            Class.forName(driver);
            this.connection = DriverManager.getConnection(dburl, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
            try (Writer writer =resp.getWriter()){
                var jsonb = JsonbBuilder.create();
                CustomerDTO customerDTO =jsonb.fromJson(req.getReader(), CustomerDTO.class);
                var customerDAOIMPL = new CustomerDAOIMPL();
                System.out.println(customerDTO);
                if (customerDAOIMPL.saveCustomer(customerDTO,connection)){
                    writer.write("customer saved successfully");
                }else {
                    writer.write("something went wrong");
                }
            }
    }
}
