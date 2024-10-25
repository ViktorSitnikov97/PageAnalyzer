@import hexlet.code.dto.UrlPage
@import hexlet.code.utils.NamedRoutes
@param UrlPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-1">
        <h3>${page.getUrl().getName()}</h3>
    </div>
`
)