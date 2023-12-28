package com.buyNowKart.servlets;

import com.buyNowKart.entities.Category;
import com.buyNowKart.entities.Product;
import com.buyNowKart.resources.Constants;
import com.ctc.wstx.shaded.msv_core.verifier.jarv.Const;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductOperationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductOperationsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        Map<String, Object> jsonResponse = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
             PrintWriter responseWriter = response.getWriter()) {

            String jsonString = br.readLine();
            System.out.println("json from the client : " + jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new SimpleModule().addDeserializer(Product.class, new Product.ProductDeserializer()));

            Product product = objectMapper.readValue(jsonString, Product.class);
            System.out.println("final Product obj using custom deserializer approach  : " + product);

            ////TODO : add in db
            boolean success = true;

            if (success) {
                System.out.println("Product added successfully.");
                jsonResponse.put(Constants.SUCCESS, true);
                jsonResponse.put(Constants.MESSAGE, "Product added successfully !");
            } else {
                System.out.println("Error in adding product.");
                jsonResponse.put(Constants.SUCCESS, false);
                jsonResponse.put(Constants.MESSAGE, "Product not added.  Internal error !");
            }

            String json = objectMapper.writeValueAsString(jsonResponse);
            responseWriter.write(json);

            responseWriter.flush();

        } catch (Exception e) {
            System.out.println("Exception in ProductOperationsServlet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}