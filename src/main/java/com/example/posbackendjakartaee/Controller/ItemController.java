package com.example.posbackendjakartaee.Controller;

import com.example.posbackendjakartaee.DTO.ItemDTO;
import com.example.posbackendjakartaee.Dao.IMPL.ItemDAOIMPL;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    Connection connection;
    public void init() throws ServletException {
        try {
            var ctx = new InitialContext();
            DataSource pool= (DataSource) ctx.lookup("java:comp/env/jdbc/stuRegistration");
            this.connection=pool.getConnection();
//            Class.forName(driverCalss);
//           this.connection =  DriverManager.getConnection(dbUrl,userName,password);
        }catch (NamingException | SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getContentType().toLowerCase().startsWith("application/json")|| req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (Writer writer=resp.getWriter()){
            var jsonb = JsonbBuilder.create();
            var itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
            var itemDAOIMPL = new ItemDAOIMPL();
            System.out.println(itemDTO);
                if (itemDAOIMPL.saveItem(itemDTO,connection)){
                    writer.write("item saved successfully");
                }else{
                    writer.write("something went wrong");
                }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Writer writer=resp.getWriter()){
            var code=req.getParameter("code");
            var jsonb = JsonbBuilder.create();
            ItemDAOIMPL itemDAOIMPL = new ItemDAOIMPL();
            var itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
                if (itemDAOIMPL.updateItem(code,itemDTO,connection)){
                    writer.write("item updated successfully");
                }else{
                    writer.write("something went wrong");
                }


        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(Writer writer=resp.getWriter()){
            var code=req.getParameter("code");
            var itemDAOIMPL = new ItemDAOIMPL();
                if (itemDAOIMPL.deleteItem(code,connection)){
                    writer.write("item deleted successfully");
                }else{
                    writer.write("something went wrong");
                }
        }
    }
}
