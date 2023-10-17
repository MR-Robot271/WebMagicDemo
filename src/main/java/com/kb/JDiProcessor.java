package com.kb;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import javax.swing.text.html.HTML;
import java.util.List;

public class JDiProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        // 通过css选择器得到 所有产品的li
        List<String> goodsList = page.getHtml().css("#J_goodsList > ul > li").all();

//        // 创建driver
//        WebDriver driver=new ChromeDriver();
//        // 打开网页


        for (String s : goodsList) {
            Html html=new Html(s);
            String img=html.css("div.p-img>a>img","source-data-lazy-img").toString();
            System.out.println(img);
            String priceClass=html.css("div.p-price>strong", "class").toString();
            // 京东的价格使用js动态生成的 需要用Selenium渲染浏览器
            // float price= Float.parseFloat(html.css("div.p-price>strong>i").toString());
            // System.out.println("产品价格："+price);
            String s1 = html.css("div.p-price").toString();
            System.out.println(s1);
        }
    }

    private Site site = Site.me()
            // 京东工业需要添加 用户代理，它是浏览器或应用程序发送给网站的标识
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36")
            // 重试次数
            .setRetryTimes(3)
            // 间隔时间
            .setSleepTime(1000);

    @Override
    public Site getSite() {
        return site;
    }
}
