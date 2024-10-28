@import hexlet.code.dto.UrlPage
@import hexlet.code.utils.FormattedTime
@import hexlet.code.utils.NamedRoutes
@param UrlPage page

@template.layout.page(
    page = page,
content = @`
    <section>
        <div class="container-lg mt-5">
            <h1>Сайт: ${page.getUrl().getName()}</h1>
            <table class="table table-bordered table-hover mt-3">
                <tbody>
                <tr>
                    <td>ID</td>
                    <td>${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td>${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <td>Дата создания</td>
                    <td>${FormattedTime.getFormattedTime(page.getUrl().getCreatedAt())}</td>
                </tr>
                </tbody>
            </table>
            <h2 class="mt-5">Проверки</h2>
            <form method="post" action="${NamedRoutes.urlCheckPath(page.getUrl().getId())}">
                <button type="submit" class="btn btn-primary">Запустить проверку</button>
            </form>
            <table class="table table-bordered table-hover mt-3">
                <thead>
                    <th class="col-1">ID</th>
                    <th class="col-1">Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th class="col-2">Дата проверки</th>
                </thead>
                <tbody>
                @for(var check : page.getChecks())
                <tr>
                    <td>
                        <p>${check.getId()}</p>
                    </td>
                    <td>
                        <p>${check.getStatusCode()}</p>
                    </td>
                    <td>
                        <p>${check.getTitle()}</p>
                    </td>
                    <td>
                        <p>${check.getH1()}</p>
                    </td>
                    <td>
                        <p>${check.getDescription()}</p>
                    </td>
                    <td>
                        <p>${FormattedTime.getFormattedTime(check.getCreatedAt())}</p>
                    </td>
                </tr>
                @endfor
                </tbody>
            </table>
        </div>

    </section>
`
)

