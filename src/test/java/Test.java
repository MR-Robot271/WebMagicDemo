import com.kb.CrawlerProcessor;
import com.kb.pipeline.ExcelPipeline;
import com.kb.pojo.Keyword;
import com.kb.utils.FileUtils;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String baseUrl="https://www.ehsy.com/search?k=";
//        String baseUrl="https://www.zkh.com/search.html?keywords=";
        String searchName="绿联（UGREEN）六类RJ45水晶头镀金 50248 100个装";

        // 获取网页地址
        String keywordPath="D:\\feishuDownloads\\test4.xlsx";
        List<Keyword> keywords = FileUtils.getKeywords(keywordPath);
        List<String> urls = new ArrayList<>();
        for (Keyword keyword:keywords){
            String noBlankModelParameters=keyword.getModelParameters().replaceAll(" ", "");
            keyword.setModelParameters(noBlankModelParameters);
            String noBlankType=keyword.getType().replaceAll(" ", "");
            keyword.setType(noBlankType);
            String url=baseUrl+keyword;
            urls.add(url);
        }
        // 需要把List转为String数组 addUrl只能用String数组才能添加多个
        String[] strings = urls.toArray(new String[0]);
        // 去除strings中的换行符 如果有换行符 放入url会出现错误
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll("\n", "");
        }


        Spider.create(new CrawlerProcessor())
                .addUrl(strings)
//                .addPipeline(new ConsolePipeline())
                .addPipeline(new ExcelPipeline())
                .thread(1) // 多线程可能会触发反爬虫
                .run();
    }
}
