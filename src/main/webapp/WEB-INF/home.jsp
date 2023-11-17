<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!-- New line below to use the JSP Standard Tag Library -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!-- New line below to use the JSP Standard Tag Library : Form -->
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!-- gestion ds erreurs -->
            <%@ page isErrorPage="true" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>All Books :</title>
                    <!-- for Bootstrap CSS -->
                    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css" />
                    <!-- YOUR own local CSS -->
                    <link rel="stylesheet" href="/css/style.css" />
                </head>

                <body>
                    <div class="container-fluid p-5" style="justify-content: center;width: 90%;">
                        <div class="title">
                            <div class="title-left">
                                <h1>Welcome,
                                    <span class="text-primary"><c:out value="${currentUser.userName}" /></span>
                                </h1>
                                <h6><a href="/logout" style="text-decoration: none;">Logout</a></h6>
                            </div>
                            <div class="title-right">
                                <h6>
                                    books from everyone shelves:</h6>
                                <h6>
                                    <a href="/book/new">+Add a to my shelf!</a>
                                </h6>
                            </div>
                        </div>
                        <table class="table table-striped border border-5 my-3">
                            <thead>
                                <tr style="text-align: center;">
                                    <th scope="col">ID</th>
                                    <th scope="col">Title</th>
                                    <th scope="col">Author Name</th>
                                    <th scope="col">Posted By</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="elt" items="${allBooks}">
                                    <tr style="text-align: center;">
                                        <td>
                                            <c:out value="${elt.id}" />
                                        </td>
                                        <td>
                                            <a href="/books/${elt.id}">
                                                <c:out value="${elt.title}" />
                                            </a>
                                        </td>
                                        <td>
                                            <c:out value="${elt.author}" />
                                        </td>
                                        <td>
                                            <c:out value="${elt.user.userName}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>


                    <!-- link Js -->
                    <script type="text/javascript" src="/js/main.js"></script>
                    <!-- For any Bootstrap that uses jquery -->
                    <script src="/webjars/jquery/jquery.min.js"></script>
                    <!--For any Bootstrap that uses JS -->
                    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
                </body>

                </html>