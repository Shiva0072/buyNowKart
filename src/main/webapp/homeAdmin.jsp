<%@page import="com.buyNowKart.resources.UrlConstants" %>
<%@page import="com.buyNowKart.entities.User" %>
<%@page import="com.buyNowKart.resources.Constants" %>
<%@ page import="com.buyNowKart.dao.DaoManager" %>
<%@ page import="com.buyNowKart.entities.Category" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute(Constants.current_user);
    if (user == null) {
        session.setAttribute(Constants.MESSAGE, "You are not logged in. Please login.");
        response.sendRedirect(UrlConstants.LOGIN_PAGE);
    } else if (user.getUserType().equals(Constants.normal_user)) {
        response.sendRedirect(UrlConstants.HOME_NORMAL);
    } else {
        session.setAttribute(UrlConstants.REDIRECT_URL, UrlConstants.HOME_ADMIN);
    }
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin home</title>
    <%@include file="components/Common.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container admin">
    <div class="row mt-4">
        <div class="col-md-4">
            <div class="card ">
                <div class="card-body text-center ">
                    <div class="container mb-1">
                        <img style="max-width: 120px;" src="images/usersIcon.png"
                             class="img-fluid">
                    </div>
                    <h2>56</h2>
                    <h3>Users</h3>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-1">
                        <img style="max-width: 120px;" src="images/categoriesIcon.png"
                             class="img-fluid">
                    </div>
                    <h2>6</h2>
                    <h3>Categories</h3>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card">
                <div class="card-body text-center">
                    <div class="container mb-1">
                        <img style="max-width: 120px;" src="images/productsIcon.png"
                             class="img-fluid">
                    </div>
                    <h2>10</h2>
                    <h3>Products</h3>
                </div>
            </div>
        </div>
    </div>
    <div class="row mt-4">
        <div class="col-md-6">
            <div class="card">
                <div class="card-body text-center" data-toggle="modal" data-target="#addCategoryModal">
                    <div class="container mb-1">
                        <img style="max-width: 120px;" src="images/addCategoriesIcon.png"
                             class="img-fluid">
                    </div>
                    <h3>Add a Category</h3>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-body text-center" data-toggle="modal" data-target="#addProductModal">
                    <div class="container mb-1">
                        <img style="max-width: 120px;" src="images/addProductsIcon.png"
                             class="img-fluid">
                    </div>
                    <h3>Add a Product</h3>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal for adding a category -->
<div class="modal fade" id="addCategoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="categoryModalTitle">Add a new category</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="add_category_form">
                    <div class="form-group">
                        <input class="form-control" placeholder="Category name" name="name">
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" placeholder="Category description"
                                  name="description"></textarea>
                    </div>
                    <button type="submit" class="btn btn-outline-success mr-2">Save changes</button>
                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%-- Modal for adding a product --%>
<div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalTitle">Add a new product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="add_product_form">
                    <div class="form-group">
                        <input class="form-control" name="name" placeholder="name of product" required>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" name="description"
                                  placeholder="description of product" required></textarea>
                    </div>
                    <%
                        List<Category> allCategories = DaoManager.categoryDao().getAllCategories();
                    %>
                    <div class="form-group">
                        <select class="form-control" required>
                            <%
                                for (Category category : allCategories) {
                            %>
                            <option name='<%= category.getId() %>'><%= category.getName() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="discount" type="number" placeholder="discount" required>
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="price" type="number" placeholder="price" required>
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="quantity" type="number" placeholder="quantity" required>
                    </div>
                    <div class="form-group">
                        <label for="productImage">Product Image</label>
                        <input type="file" class="form-control-file" id="productImage">
                    </div>

                    <button type="submit" class="btn btn-outline-success mr-2">Save changes</button>
                    <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</div>


</body>

<script>
    $(document).ready(function () {

        $(document).on('submit', 'form.add_category_form', function (event) {
            event.preventDefault();
            let requestParams = {};
            requestParams["name"] = $(this).find('input[name="name"]').val();
            requestParams["description"] = $(this).find('textarea[name="description"]').val();
            console.log("category form data sending to backend : ", requestParams);

            let successValidation = validateNameDescription(requestParams.name, requestParams.description);
            if (successValidation) {
                const url = '<%= UrlConstants.BASE_URL %><%= UrlConstants.CATEGORY_OPERATIONS_SERVLET %>';
                const successFunction = function (response) {
                    displayToast('Success', response['<%= Constants.MESSAGE %>'], 'success', 'bg-success');
                }
                const errorFunction = function (error) {
                    displayToast('Error', error['<%= Constants.MESSAGE %>'], 'error', 'bg-danger');
                }
                makeAjaxRequest(url, 'POST', requestParams, successFunction, errorFunction);
            }
        });

        $(document).on('submit', 'form.add_product_form', function (event){
            event.preventDefault();
            let requestParams={};
            requestParams["name"] = $(this).find('input[name="name"]').val();
            requestParams["description"] = $(this).find('textarea[name="description"]').val();
            requestParams["category"] = $(this).find('option:selected').attr('name');
            requestParams["discount"] = $(this).find('input[name="discount"]').val();
            requestParams["price"] = $(this).find('input[name="price"]').val();
            requestParams["quantity"] = $(this).find('input[name="quantity"]').val();
            requestParams["image"] = $(this).find('input[type="file"]').val();

            console.log("sending request-payload from client : ",requestParams);

            let successValidation = validateNameDescription(requestParams.name,requestParams.description);
            if(successValidation){
                const url = '<%= UrlConstants.BASE_URL %><%= UrlConstants.PRODUCT_OPERATIONS_SERVLET %>';
                const successFunction = function(response){
                    console.log("inside succDFFF : ",response);
                    displayToast('Success',response['<%= Constants.MESSAGE %>'],'success','bg-success');
                }
                const errorFunction = function (error){
                    displayToast('Error',error['<%= Constants.MESSAGE %>'],'error','bg-danger');
                }
                makeAjaxRequest(url,'POST',requestParams,successFunction,errorFunction);
            }
        });

    });

    function makeAjaxRequest(url, method, data, successFunction, errorFunction, defaultFunction) {
        if (defaultFunction === undefined) {
            defaultFunction = function () {
            }
        }
        $.ajax({
            url: url,
            type: method,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                console.log("ajax request success. response : ", response);
                successFunction(response);
                defaultFunction();
            },
            error: function (error) {
                console.log("Error in ajax request :", error);
                errorFunction(error);
                defaultFunction();
            }
        });
    }

    function validateNameDescription(name, description) {
        if (name === "" || description === "") {
            displayToast('Info', 'Name and description of category is mandatory', 'info', 'bg-secondary');
            return false;
        }
        return true;
    }

</script>

</html>