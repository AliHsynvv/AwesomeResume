<%@ page import="entity.dao.inter.UserDaoInter" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jsp</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/users.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9c0ccc64eb.js" crossorigin="anonymous"></script>
    <link rel="icon"
          href="data:image/png;base64,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">

    <script type="text/javascript" src="assets/js/users.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>

<%
    User user = (User) session.getAttribute("loggedInUser");
%>

<%="Wellcome, " + user.getName()%>
<%
    UserDaoInter userDao = main.Context.instanceUserDao();
    String name = request.getParameter("name");
    String surname = request.getParameter("surname");
    String nationalityIdstr = request.getParameter("nid");
    Integer nationalityId = null;
    if (nationalityIdstr != null && !nationalityIdstr.trim().isEmpty()) {
        nationalityId = Integer.parseInt(nationalityIdstr);
    }

    List<User> list = userDao.getAll(name, surname, nationalityId);

%>


<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>

            </div>
            <div class="modal-body">
                Are your sure ?
            </div>
            <div class="modal-footer">
                <form action="userdetails" method="post">
                    <input type="hidden" name="id" value="" id="idForDelete">
                    <input type="hidden" name="action" value="" id="delete">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary btn-danger" value="Delete">
                </form>
            </div>

        </div>
    </div>
</div>
<div class="container col-12">
    <div class="col-4">
        <form>
            <div class="mb-3">
                <label class="form-label">Name:</label>
                <input onkeyup="writeWhatIamTypeing()" placeholder="Enter name" type="text" class="form-control"
                       id="whatIamTypeing"
                       aria-describedby="emailHelp" name="name">
            </div>
            <div class="mb-3">
                <label class="form-label">Surname:</label>
                <input placeholder="Enter surname" type="text" class="form-control" id="exampleInputPassword1"
                       name="surname">
            </div>

            <button onclick="changeColor()" type="submit" class="btn btn-primary" name="search" id="btnsearch">Search
            </button>
        </form>
    </div>


    <div>
        <table class="table">


            <thead>
            <tr>
                <th>name</th>
                <th>surname</th>
                <th>nationality</th>
            </tr>
            </thead>

            <tbody>
            <%
                for (User u : list) {

            %>
            <tr>
                <td><%=u.getName()%>
                </td>
                <td><%=u.getSurname()%>
                </td>
                <td><%=u.getNationality().getName() == null ? "N/A" : u.getNationality().getName()%>
                </td>
                <td style="width: 2px">
                    <input type="hidden" name="id" value="<%=u.getId()%>"/>
                    <input type="hidden" name="action" value="delete"/>
                    <button style="margin-bottom: 5px" class="btn btn-danger" type="submit" value="delete"
                            data-toggle="modal" data-target="#exampleModalCenter"
                            onclick="setIdForDelete(<%=u.getId()%>)">
                        <i class="fa-solid fa-trash-can"></i>
                    </button>
                </td>
                <td style="width: 2px">
                    <form action="userdetails" method="GET">
                        <input type="hidden" name="id" value="<%=u.getId()%>"/>
                        <input type="hidden" name="action" value="update"/>
                        <button class="btn btn-success" type="submit" value="update">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

</div>

</body>
</html>
