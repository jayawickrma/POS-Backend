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

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;


@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    Connection connection;
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
