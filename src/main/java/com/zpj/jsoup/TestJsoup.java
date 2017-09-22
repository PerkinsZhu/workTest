package com.zpj.jsoup;

import com.zpj.thread.testsynchronized.SynchronizedTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.Test;

/**
 * Created by PerkinsZhu on 2017/7/17 10:21.
 */
public class TestJsoup {
  /*  public static void main(String [] args){
        testParas();
    }*/
    @Test
    public void testParas(){
//        String str ="{\"chatId\":\"596c0cda0c0000fe006a6376\",\"channel\":\"weixin\",\"userId\":\"oH9hEwNIZYyx0LpVK38uUqQt9YJk\",\"sessionId\":\"oH9hEwNIZYyx0LpVK38uUqQt9YJk\",\"question\":\"国籍\",\"answers\":[{\"respond\":\"<p><p><br/>    <img src=\\\"http://atool.qiniudn.com/wx_editor_files_0(1)\\\" data-original=\\\"http://atool.qiniudn.com/wx_editor_files_0(1)\\\" style=\\\"display: inline;\\\" width=\\\"100%\\\"/> <br/></p><br/><p><br/>    <br/><br/></p><br/><p><br/>    <br/><br/></p></p>\",\"score\":50.94714279450389,\"choices\":[],\"suggestions\":[]}]}";
//        String str ="\"<p><p><br/><a href=\"https://trip.csair.com/module/newgroupon/D1610260000004.html\">我是一张图片</a> <a href=\"https://trip.csair.com/module/newgroupon/D1610260000004.html\">是也是一张图片02</a>    <img src=\\\"http://atool.qiniudn.com/wx_editor_files_0(1)\\\" data-original=\\\"http://atool.qiniudn.com/wx_editor_files_0(1)\\\" style=\\\"display: inline;\\\" width=\\\"100%\\\"/> <br/></p><br/><p><br/>    <br/><br/></p><br/><p><br/>    <br/><br/></p></p>";
        String str ="<p>\n" + " <a href=\\\"https://trip.csair.com/module/newgroupon/D1610260000004.html\\\">我是一张图片</a> aswefdqew<a href=\\\"https://trip.csair.com/module/newgroupon/D1610260000004.html\\\">我是一张图片02222</a>    <img src=\"https://res-vip.chatbot.cn/file/b0caf115-53d6-4282-92a1-138ce07c0845\" alt=\"b0caf115-53d6-4282-92a1-138ce07c0845\"/><em><strong>hello 是打</strong></em>发斯蒂芬<br/>\n" + "</p>";
        Document doc = Jsoup.parse(str);
//        System.out.println(doc.text());
        for (Element img : doc.select("img")) {
            String src = "\t查看图片请点击 ：" + img.attr("src") + "\t";
            img.replaceWith(new TextNode(src, "wwww.baidu.com/"));
        }
//        System.out.println(doc.text());
        for (Element p : doc.select("p")) {
            p.appendText("\\n");
        }
       for (Element p : doc.select("p")) {
//            System.out.println(p);
            p.appendText("\\n");
//            System.out.println(p);
        }
        for (Element element : doc.select("a")) {
//            System.out.println(element);
            String elementStr= element.toString();
            String strTemp = elementStr.replaceAll("(<a href)(.*)(</a>)", "/a href$2/a");
            org.jsoup.nodes.Node node = new TextNode(elementStr, "www.baidu.con/");
            element.replaceWith(node);
        }
        String temp = doc.text();
        System.out.println(temp);
        String answer = temp.replaceAll("\\\\n( )?","\n").replaceAll("\\n$", "").replaceAll("(/a href)(.*)(/a)", "<a href$2</a>").replaceAll("www.baidu.com/getWechatPic/\\?picUrl=", "");
        System.out.println(answer);
    }
}
