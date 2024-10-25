@import hexlet.code.dto.UrlsPage
@import hexlet.code.utils.NamedRoutes
@param UrlsPage page
@template.layout.page(
page = page,
content = @`
    <h1 class="text-center p-5">Сайты</h1>
    <table class="table table-striped">
        @if(page.getUrls().isEmpty())
            <p class="text-center p-5">Ни один сайт не добавлен</p>
        @endif
        @for(var url : page.getUrls())
            <tr>
                <td>
                    ${url.getId()}
                </td>
                <td>
                    <a href = "${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                </td>
            </tr>
        @endfor
    </table>
`
)