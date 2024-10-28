@import hexlet.code.dto.UrlsPage
@import hexlet.code.repository.CheckUrlRepository
@import hexlet.code.utils.FormattedTime
@import hexlet.code.utils.NamedRoutes
@param UrlsPage page
@template.layout.page(
page = page,
content = @`
    <section>
        <div class="container-lg mt-5">
            <h1>Сайты</h1>
            @if(page.getUrls().isEmpty())
                <p class="text-center p-5">Ни один сайт не добавлен</p>
            @else
            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th>Имя</th>
                    <th class="col-2">Последняя проверка</th>
                    <th class="col-1">Код ответа</th>
                </tr>
                </thead>
                <tbody>
                @for(var url : page.getUrls())
                <tr>
                    <td>
                        ${url.getId()}
                    </td>
                    <td>
                        <a href = "${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                    </td>
                    <td>
                        @if(CheckUrlRepository.getLastCheck(url.getId()).isPresent())
                            ${FormattedTime.getFormattedTime(CheckUrlRepository.getLastCheck(url.getId()).get().getCreatedAt())}
                        @endif
                    </td>
                    <td>
                        @if(CheckUrlRepository.getLastCheck(url.getId()).isPresent())
                            ${CheckUrlRepository.getLastCheck(url.getId()).get().getStatusCode()}
                        @endif
                    </td>
                </tr>
                </tbody>
                @endfor
            </table>
            @endif
        </div>

    </section>
`
)
