@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap demo</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
    </head>
    <body>
<%--        <nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--            <div class="collapse navbar-collapse" id="navbarNavDropdown">--%>
<%--                <ul class="navbar-nav">--%>
<%--                    <li class="nav-item active">--%>
<%--                        <a class="nav-link" href="/">Home</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="${NamedRoutes.usersPath()}">Users</a>--%>
<%--                    </li>--%>
<%--                    <li class="nav-item">--%>
<%--                        <a class="nav-link" href="${NamedRoutes.coursesPath()}">Courses</a>--%>
<%--                    </li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </nav>--%>
        <h1 class=text-center>Hello, world!</h1>
        <div>
            ${content}
        </div>

    </body>
</html>
