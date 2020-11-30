package models.utils.webparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PageParser {
    private String mainPage = "https://github.com/CSSEGISandData/COVID-19/tree/master/csse_covid_19_data/csse_covid_19_daily_reports";
    private String siteDomain = "https://github.com/";

    public List<String> getListFileNames () throws IOException {
        Document doc = Jsoup.connect(mainPage).get();
        Elements files = doc.select("a");

        List<String> filenames = new LinkedList<>();
        for (Element el : files) {
            if (el.hasClass("js-navigation-open") && el.attr("title").contains(".csv")) {
                filenames.add(el.attr("title"));
            }
        }

        return filenames;
    }

    public HashMap<String, String> getMapFiles () throws IOException {
        Document doc = Jsoup.connect(mainPage).get();
        Elements files = doc.select("a");

        HashMap<String, String> files_ = new HashMap<>();
        for (Element el : files) {
            if (el.hasClass("js-navigation-open") && el.attr("title").contains(".csv")) {
                files_.put(el.attr("title"), siteDomain + el.attr("href"));
            }
        }

        return files_;
    }
}
