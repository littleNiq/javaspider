package project.service;

import project.aargs.Answer;
import project.args.*;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Naq on 2018/7/15.
 */
public class SpiderAnswerTwo {
    public static void main(String[] args) throws IOException {

        String URLONR = "https://www.zhihu.com/api/v4/topics/20137817/feeds/timeline_question?include=data[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Danswer)].target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Danswer)].target.is_normal%2Ccomment_count%2Cvoteup_count%2Ccontent%2Crelevant_info%2Cexcerpt.author.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Darticle)].target.content%2Cvoteup_count%2Ccomment_count%2Cvoting%2Cauthor.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Dtopic_sticky_module)].target.data[%3F(target.type%3Dpeople)].target.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Danswer)].target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata[%3F(target.type%3Danswer)].target.author.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Darticle)].target.content%2Cauthor.badge[%3F(type%3Dbest_answerer)].topics%3Bdata[%3F(target.type%3Dquestion)].target.comment_count&limit=10&offset=";
        List<Answer> answers = new ArrayList<>();
        String URLTWO = "";
        ExportExcelUtil<Answer> util = new ExportExcelUtil<Answer>();
        String[] columnNames = {"回答对应的问题", "回答者", "回答者关注者", "回答支持量", "回答评论量"};

        for (int i = 340; ; i += 10) {
            Document document = Jsoup.connect(URLONR + String.valueOf(i)).timeout(5000000)
                    .ignoreContentType(true).get();

            String body = document.text();
            JSONObject jsonOne = JSONObject.fromObject(body);

            Map map = new HashMap();
            map.put("data", Data.class);
            map.put("author", Author.class);
            map.put("paging", Paging.class);
            map.put("target", Target.class);

            JsonRootBean jsonRootBean = (JsonRootBean) JSONObject.toBean(jsonOne, JsonRootBean.class, map);
            for (int j = 0; j < jsonRootBean.getData().size(); j++) {

                // 先得到每个问题
                Data data = jsonRootBean.getData().get(j);
                String title = data.getTarget().getTitle();

                // 当该一问题的答案数量不为0
                if (jsonRootBean.getData().get(j).getTarget().getAnswer_count() != 0) {

                    String url = data.getTarget().getUrl();
                    URLTWO = url + "/answers?include=data%5B%2A%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata%5B%2A%5D.mark_infos%5B%2A%5D.url%3Bdata%5B%2A%5D.author.follower_count%2Cbadge%5B%3F%28type%3Dbest_answerer%29%5D.topics&limit=20&sort_by=default&offset=";
                    for (int k = 0; k <= jsonRootBean.getData().get(j).getTarget().getAnswer_count(); k += 20) {
                        String urll = URLTWO + String.valueOf(k);
                        Document documentt = Jsoup.connect(urll).timeout(5000000)
                                .ignoreContentType(true).get();
                        String bodys = documentt.text();
                        JSONObject jsonOnes = JSONObject.fromObject(bodys);
                        Map maps = new HashMap();
                        maps.put("data", com.company.project.aargs.Data.class);
                        maps.put("author", com.company.project.aargs.Author.class);
                        maps.put("paging", com.company.project.aargs.Paging.class);
                        maps.put("qustion", com.company.project.aargs.Question.class);
                        com.company.project.aargs.JsonRootBean jsonRootBeans = (com.company.project.aargs.JsonRootBean) JSONObject.toBean(jsonOnes, com.company.project.aargs.JsonRootBean.class, maps);
                        for (int l = 0; l < jsonRootBeans.getData().size(); l++) {
                            String author = jsonRootBeans.getData().get(l).getAuthor().getName();
                            String comment_count = String.valueOf(jsonRootBeans.getData().get(l).getComment_count());
                            String voteup_count = String.valueOf(jsonRootBeans.getData().get(l).getVoteup_count());
                            String follower_count = String.valueOf(jsonRootBeans.getData().get(l).getAuthor().getFollower_count());
                            answers.add(new Answer(title, author, follower_count, voteup_count, comment_count));
                            util.exportExcel("问题列表", columnNames, answers, new FileOutputStream("D:/0714/test2.xls"), ExportExcelUtil.EXCEl_FILE_2007);

                        }
                    }
                }

            }
            System.out.println("===========================" + i + "==========================");
        }
    }
}

