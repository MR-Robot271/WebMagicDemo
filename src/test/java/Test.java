import com.kb.CrawlerProcessor;
import us.codecraft.webmagic.Spider;

public class Test {
    public static void main(String[] args) {
        String baseUrl="https://www.ehsy.com/search?k=";
        String searchName="绿联（UGREEN）六类RJ45水晶头镀金 50248 100个装";
        Spider.create(new CrawlerProcessor())
                .addUrl(baseUrl+searchName)
                .thread(1)
                .run();
    }
}
