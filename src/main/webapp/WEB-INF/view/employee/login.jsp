<%--
  Created by IntelliJ IDEA.
  User: José Manuel Sánchez Rico
  Date: 13/03/2023
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Polybank - Employee Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<body>
<div class="px-4 py-5 my-5 text-center">
    <h1 class="display-5 fw-bold">Login</h1>
    <div class="col-lg-6 mx-auto">
        <p class="lead mb-4">Log in as an employee:</p>
        <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
            <form action="/employee/login" method="post">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">DNI</span>
                    </div>
                    <input type="text" id="username" name="dni" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"/><br>
                </div>
                <input type="submit">
            </form>
        </div>
    </div>
</div>
<h1></h1>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
