package com.buyNowKart.servlets;

import com.buyNowKart.dao.DaoManager;
import com.buyNowKart.entities.Category;
import com.buyNowKart.resources.Constants;
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

public class CategoryOperationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CategoryOperationsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
             PrintWriter responseWriter = response.getWriter()) {

            //1
            String jsonString = br.readLine();
            System.out.println("we got the json from client : " + jsonString);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new SimpleModule().addDeserializer(Category.class, new Category.CategoryDeserializer()));

            //2
            Category category = objectMapper.readValue(jsonString, Category.class);
            System.out.println("final Category obj using custom deserializer approach  : " + category);

            //3
            boolean success = DaoManager.categoryDao().AddCategory(category);

            //4
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Map<String,Object> jsonResponse = new HashMap<>();

            if (success) {
                System.out.println("Category added successfully.");
                jsonResponse.put(Constants.SUCCESS,true);
                jsonResponse.put(Constants.MESSAGE,"Category added successfully !");
            } else {
                System.out.println("Error in adding category");
                jsonResponse.put("success",false);
                jsonResponse.put(Constants.MESSAGE,"Category could not be added to db. Internal error !");
            }

            String json = objectMapper.writeValueAsString(jsonResponse);
            responseWriter.write(json);

            //commit (save) the response
            responseWriter.flush();

        } catch (Exception e) {
            System.out.println("Exception in CategoryOperationsServlet : " + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

/*
*
* In the try block : we try to read the request and also get access of the responseWriter
*
* //1: get the json from the request. create instance of ObjectMapper and assign it a custom deserializer using SimpleModule.
* //2: deserialize the json data to corresponding obj structure.
* //3: save in DB.
* //4: construct the json we want to send in response; from servlet to client.
*
* */


/*
// ======>  Approach -1 for json parsing :-
//        try(BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()))){
//            String json = "";
//            json = br.readLine();
//            System.out.println("we got the json : "+json);
//
//            // 2. initiate jackson mapper
//            ObjectMapper mapper = new ObjectMapper();
//
//            Category category = mapper.readValue(json,Category.class);
//            System.out.println("final obj we got  : "+category);
//
//
//        }
//        catch(Exception e){
//            System.out.println("Exception in : "+e.getMessage());
//            e.printStackTrace();
//        }

// ======> Approach -2 for json parsing :-
//        try(BufferedReader reader = request.getReader()){
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonParser jsonParser = objectMapper.getFactory().createParser(reader);
//            Category category = new Category();
//
//            while (jsonParser.nextToken() != null) {
//                String field = jsonParser.getCurrentName();
//
//                if (field != null) {
//                    switch (field) {
//                        case "name":
//                            jsonParser.nextToken();
//                            category.setName(jsonParser.readValueAs(String.class));
//                            break;
//                        case "description":
//                            jsonParser.nextToken();
//                            category.setDescription(jsonParser.readValueAs(String.class));
//                            break;
//                    }
//                }
//            }
//
//            System.out.println("final obj using other approach  : "+category);
//
//        }catch (Exception e){
//            System.out.println("Exception in : "+e.getMessage());
//            e.printStackTrace();
//        }
*
*
* */