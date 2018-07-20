package project.service;

import project.args.*;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Naq on 2018/7/1.
 */
public class SpiderQuestionOne {
    public static void main(String[] args) throws IOException {

        String URLONR = "https://www.zhihu.com/api/v4/topics/20172755/feeds/timeline_question?include=data[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Danswer)].target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Danswer)].target.is_normal%2Ccomment_count%2Cvoteup_count%2Ccontent%2Crelevant_info%2Cexcerpt.author.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Darticle)].target.content%2Cvoteup_count%2Ccomment_count%2Cvoting%2Cauthor.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Dpeople)].target.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Danswer)].target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata[%3F(target.type%3Danswer)].target.author.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Darticle)].target.content%2Cauthor.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Dquestion)].target.comment_count&limit=10&offset=";
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Questions> questionses = new ArrayList<>();

        ExportExcelUtil<Questions> util = new ExportExcelUtil<Questions>();

        for (int i = 0; ; i += 10) {
            int finalI = i;
            executorService.submit(() -> {
                Document document = null;
                try {
                    document = Jsoup.connect(URLONR + String.valueOf(finalI)).timeout(50000)
                            .ignoreContentType(true).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String body = document.text();
                JSONObject jsonOne = JSONObject.fromObject(body);

                Map map = new HashMap();
                map.put("data", Data.class);
                map.put("author", Author.class);
                map.put("paging", Paging.class);
                map.put("target", Target.class);

                JsonRootBean jsonRootBean = (JsonRootBean) JSONObject.toBean(jsonOne, JsonRootBean.class, map);
                String[] columnNames = {"问题", "问题关注人数", "问题浏览量", "问题发布者", "问题发布时间"};
                for (int j = 0; j < jsonRootBean.getData().size(); j++) {
                    Data data = jsonRootBean.getData().get(j);
                    String urlss = data.getTarget().getUrl();
                    String urls[] = urlss.split("/");
                    String url = urls[urls.length - 1];
                    url = "https://www.zhihu.com/question/" + url;
                    Document document1 = null;
                    try {
                        document1 = Jsoup.connect(url).timeout(5000)
                                .ignoreContentType(true).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // 浏览量
                    Elements visit = document1.select("#root").select("div").select("main").select("div:nth-child(11)").select("div.QuestionHeader").select("div.QuestionHeader-content")
                            .select("div.QuestionHeader-side").select("div").select("div").select("div").select("div").select("div").select("strong");
                    String visitors = visit.text();
                    String[] visitor = visitors.split(" ");
                    visitors = visitor[1];
                    String title = data.getTarget().getTitle();
                    int follower_count = data.getTarget().getFollower_count();
                    String author = data.getTarget().getAuthor().getName();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    long msl = data.getTarget().getCreated() * 1000;
                    String date = sdf.format(msl);
                    questionses.add(new Questions(title, follower_count, visitors, author, date));
                    try {
                        util.exportExcel("问题列表", columnNames, questionses, new FileOutputStream("D:/Questions1.xls"), ExportExcelUtil.EXCEl_FILE_2007);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("----" + finalI);
            });

        }
//        executorService.shutdown();
    }
}
