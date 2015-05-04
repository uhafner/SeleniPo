package de.muenchen.SeleniPoHtmlParser;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.muenchen.SeleniPoHtmlParser.config.HtmlParserConfig;
import de.muenchen.selenipo.PoGeneric;
import de.muenchen.selenipo.Selector;

@org.junit.runner.RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HtmlParserConfig.class, loader = AnnotationConfigContextLoader.class)
public class HtmlParserTest {

	@Autowired
	private HtmlParserService htmlParserService;

	@Test
	public void simpleTest() {
		PoGeneric parseElementsFromHtmlForType = htmlParserService
				.parseElementsFromHtmlForType(getDummyHtml(), Selector.LINK);
		Assert.assertEquals(5, parseElementsFromHtmlForType.getElements()
				.size());
	}

	private String getDummyHtml() {
		return "<!DOCTYPE html>\r\n<html>\r\n<head>\r\n  <title>ToDo</title>\r\n  <link data-turbolinks-track=\"true\" href=\"/assets/twitter-bootstrap-static/bootstrap.css?body=1\" media=\"all\" rel=\"stylesheet\" />\r\n<link data-turbolinks-track=\"true\" href=\"/assets/twitter-bootstrap-static/fontawesome.css?body=1\" media=\"all\" rel=\"stylesheet\" />\r\n<link data-turbolinks-track=\"true\" href=\"/assets/bootstrap_and_overrides.css?body=1\" media=\"all\" rel=\"stylesheet\" />\r\n<link data-turbolinks-track=\"true\" href=\"/assets/welcome.css?body=1\" media=\"all\" rel=\"stylesheet\" />\r\n<link data-turbolinks-track=\"true\" href=\"/assets/application.css?body=1\" media=\"all\" rel=\"stylesheet\" />\r\n  <script data-turbolinks-track=\"true\" src=\"/assets/jquery.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/jquery_ujs.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/transition.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/alert.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/modal.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/dropdown.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/scrollspy.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/tab.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/tooltip.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/popover.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/button.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/collapse.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/carousel.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap/affix.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/twitter/bootstrap.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/turbolinks.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/bootstrap.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/todos.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/welcome.js?body=1\"></script>\r\n<script data-turbolinks-track=\"true\" src=\"/assets/application.js?body=1\"></script>\r\n  <meta content=\"authenticity_token\" name=\"csrf-param\" />\r\n<meta content=\"OYtdf4XdGUpweVm7oCajitCvOwmM+ck8tqytQJk83qA=\" name=\"csrf-token\" />\r\n</head>\r\n<body>\r\n\r\n<!-- Navigation -->\r\n    <nav class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\r\n        <div class=\"container\">\r\n            <!-- Brand and toggle get grouped for better mobile display -->\r\n            <div class=\"navbar-header\">\r\n                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\r\n                    <span class=\"sr-only\">Toggle navigation</span>\r\n                    <span class=\"icon-bar\"></span>\r\n                    <span class=\"icon-bar\"></span>\r\n                    <span class=\"icon-bar\"></span>\r\n                </button>\r\n                <a class=\"navbar-brand\" id=\"toDo\" href=\"/welcome/index\">ToDo-App</a>\r\n            </div>\r\n            <!-- Collect the nav links, forms, and other content for toggling -->\r\n            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\r\n                <ul class=\"nav navbar-nav navbar-right\">\r\n                    <li>\r\n                        <a href=\"#\">About</a>\r\n                    </li>\r\n                    <li>\r\n                        <a href=\"#\">Services</a>\r\n                    </li>\r\n                    <li>\r\n                        <a href=\"#\">Contact</a>\r\n                    </li>\r\n                </ul>\r\n            </div>\r\n            <!-- /.navbar-collapse -->\r\n        </div>\r\n        <!-- /.container -->\r\n    </nav>\r\n    <div class=\"banner\">\r\n    </div>\r\n    \r\n\t<div class=\"container\">\r\n    <div class=\"row \">\r\n      <div class=\"col-md-9\"><div class=\"page-header\">\r\n  <h1>New Todo</h1>\r\n</div>\r\n<form accept-charset=\"UTF-8\" action=\"/todos\" class=\"form-horizontal todo\" id=\"new_todo\" method=\"post\"><div style=\"display:none\"><input name=\"utf8\" type=\"hidden\" value=\"&#x2713;\" /><input name=\"authenticity_token\" type=\"hidden\" value=\"OYtdf4XdGUpweVm7oCajitCvOwmM+ck8tqytQJk83qA=\" /></div>\r\n\r\n\r\n  <div class=\"control-group\">\r\n    <label class=\"control-label\" for=\"todo_title\">Title</label>\r\n    <div class=\"controls\">\r\n      <input class=\"form-control\" id=\"todo_title\" name=\"todo[title]\" type=\"text\" />\r\n    </div>\r\n    \r\n  </div>\r\n  <div class=\"control-group\">\r\n    <label class=\"control-label\" for=\"todo_notes\">Notes</label>\r\n    <div class=\"controls\">\r\n      <input class=\"form-control\" id=\"todo_notes\" name=\"todo[notes]\" type=\"text\" />\r\n    </div>\r\n    \r\n  </div>\r\n\r\n\r\n  <input class=\"btn btn-primary\" name=\"commit\" type=\"submit\" value=\"Create Todo\" />\r\n  <a class=\"btn btn-default\" href=\"/todos\">Cancel</a>\r\n\r\n</form>\r\n</div>\r\n      <div class=\"col-md-3\">\r\n     \r\n    </div>\r\n  </div>\r\n\t</div>\r\n\r\n   \r\n\r\n\r\n\r\n\r\n</body>\r\n</html>\r\n";
	}
}
