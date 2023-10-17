import com.kb.JDiProcessor;
import us.codecraft.webmagic.Spider;

public class TestJD {
    public static void main(String[] args) {
        String baseUrl="https://i-search.jd.com/Search?keyword=";
        String pName="风电手套";
        String endUrl="&enc=utf-8";


        Spider.create(new JDiProcessor())
                .addUrl(baseUrl+pName+endUrl)
                .thread(1)
                .run();
    }
}
