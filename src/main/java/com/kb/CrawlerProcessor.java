package com.kb;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

public class CrawlerProcessor implements PageProcessor {
    public void process(Page page) {
//        String products = page.getHtml().css("div.layout-product-list").toString();
//        System.out.println(products);

        List<String> imgs=page.getHtml().css("div.product-list>div.product>a>div.p-image>div.image-div").xpath("//img/@src").all();
        System.out.println(imgs);
        System.out.println(imgs.size());
        page.putField("imgs",imgs);


//        Selectable selectable = page.getHtml().css("div.product-list>div.product").$("a");
//        for (String s : selectable.all()) {
//            System.out.println(s);
//        }
    }

    private Site site = Site.me()
            // 重试的次数
            .setRetryTimes(3)
            // 间隔时间
            .setSleepTime(1000);

    @Override
    public Site getSite() {
        return site;
    }
}
