@import gg.jte.Content
@import hexlet.code.dto.BasePage
@import hexlet.code.utils.NamedRoutes
@param Content content
@param BasePage page = null
@param Content footer = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Page Analyzer</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
              rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                crossorigin="anonymous"></script>
    </head>
    <body class="d-flex flex-column min-vh-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <a class="navbar-brand" href="${NamedRoutes.rootPath()}">Анализатор страниц</a>
            <div class="collapse navbar-collapse" id="navbarNav">
                <div class="navbar-nav">
                    <a class="nav-link" href="${NamedRoutes.rootPath()}">Главная</a>
                    <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
                </div>
            </div>
        </div>
    </nav>
    </body>
    <main class="flex-grow-1">
        @if(page != null && page.getFlash() != null)
            <div class="rounded-0 m-0 alert alert-${page.getFlashType()} alert-dismissible fade show" role="alert">
                ${page.getFlash()}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        @endif
        ${content}
    </main>
    <footer class="footer border-top py-3 mt-5 bg-light">
        <div class="container-xl">
            <div class="text-center">
                created by
                <a href="https://github.com/ViktorSitnikov97" target="_blank">Viktor Sitnikov</a>
            </div>
        </div>
    </footer>

</html>
