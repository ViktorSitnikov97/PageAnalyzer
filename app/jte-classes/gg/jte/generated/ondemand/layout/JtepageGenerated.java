package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.dto.BasePage;
import hexlet.code.utils.NamedRoutes;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,24,24,24,24,24,24,24,24,24,27,27,27,27,27,27,27,27,28,28,28,28,28,28,28,28,35,35,36,36,36,36,37,37,37,40,40,41,41,41,48};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page, Content footer) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"en\">\n    <head>\n        <meta charset=\"utf-8\">\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n        <title>Bootstrap demo</title>\n        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\"\n              rel=\"stylesheet\"\n              integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\"\n              crossorigin=\"anonymous\">\n        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"\n                integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\"\n                crossorigin=\"anonymous\"></script>\n    </head>\n    <body class=\"d-flex flex-column min-vh-100\">\n    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n        <div class=\"container-fluid\">\n            <a class=\"navbar-brand\"");
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.rootPath())) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(NamedRoutes.rootPath());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Анализатор страниц</a>\n            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n                <div class=\"navbar-nav\">\n                    <a class=\"nav-link\"");
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.rootPath())) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(NamedRoutes.rootPath());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Главная</a>\n                    <a class=\"nav-link\"");
		if (gg.jte.runtime.TemplateUtils.isAttributeRendered(NamedRoutes.urlsPath())) {
			jteOutput.writeContent(" href=\"");
			jteOutput.setContext("a", "href");
			jteOutput.writeUserContent(NamedRoutes.urlsPath());
			jteOutput.setContext("a", null);
			jteOutput.writeContent("\"");
		}
		jteOutput.writeContent(">Сайты</a>\n                </div>\n            </div>\n        </div>\n    </nav>\n    </body>\n    <main>\n        ");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\n            <div class=\"alert alert-");
			jteOutput.setContext("div", "class");
			jteOutput.writeUserContent(page.getFlashType());
			jteOutput.setContext("div", null);
			jteOutput.writeContent(" alert-dismissible fade show\" role=\"alert\">\n                ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("\n                <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n            </div>\n        ");
		}
		jteOutput.writeContent("\n        ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </main>\n    <footer>\n\n    </footer>\n\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		Content footer = (Content)params.getOrDefault("footer", null);
		render(jteOutput, jteHtmlInterceptor, content, page, footer);
	}
}
