package project.service;

import project.pargs.*;
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
 * Created by Naq on 2018/7/14.
 */
public class SpiderColumnOne {
    public static void main(String[] args) throws IOException {

        String URLONR = "https://www.zhihu.com/api/v4/topics/20172755/feeds/essence?include=data%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Danswer)%5D.target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Danswer)%5D.target.is_normal%2Ccomment_count%2Cvoteup_count%2Ccontent%2Crelevant_info%2Cexcerpt.author.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Darticle)%5D.target.content%2Cvoteup_count%2Ccomment_count%2Cvoting%2Cauthor.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Dtopic_sticky_module)%5D.target.data%5B%3F(target.type%3Dpeople)%5D.target.answer_count%2Carticles_count%2Cgender%2Cfollower_count%2Cis_followed%2Cis_following%2Cbadge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Danswer)%5D.target.content%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%3Bdata%5B%3F(target.type%3Danswer)%5D.target.author.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Darticle)%5D.target.content%2Cauthor.badge%5B%3F(type%3Dbest_answerer)%5D.topics%3Bdata%5B%3F(target.type%3Dquestion)%5D.target.comment_count&limit=10&offset=";
        List<Columnlist> columnlists = new ArrayList<>();

        ExportExcelUtil<Columnlist> util = new ExportExcelUtil<Columnlist>();

        for (int i = 0; ; i += 10) {
            Document document = Jsoup.connect(URLONR + String.valueOf(i)).timeout(5000)
                    .ignoreContentType(true).get();
            String body = document.text();
            int k = 0;
            for (k = body.length() - 1; ; k--) {
                if (body.charAt(k) == '}') {
                    break;
                }
            }
            body = body.substring(0, k + 1);
            JSONObject jsonOne = JSONObject.fromObject(body);

            Map map = new HashMap();
            map.put("data", Data.class);
            map.put("author", Author.class);
            map.put("paging", Paging.class);
            map.put("target", Target.class);
            map.put("column", Column.class);
            map.put("hd", Hd.class);
            map.put("ld", Ld.class);
            map.put("sd", Sd.class);
            map.put("thumbnail_extra_info", Thumbnail_extra_info.class);
            map.put("playlist", Playlist.class);

            JsonRootBean jsonRootBean = (JsonRootBean) JSONObject.toBean(jsonOne, JsonRootBean.class, map);
            String[] columnNames = {"专栏标题", "发布者", "点赞量", "评论量"};
            for (int j = 0; j < jsonRootBean.getData().size(); j++) {
                Data data = jsonRootBean.getData().get(j);
                Target target = data.getTarget();
                if (target.getUrl().contains("zhuanlan")) {
                    String title = target.getTitle();
                    String author = target.getAuthor().getName();
                    String voteup_count = String.valueOf(target.getVoteup_count());
                    String comment_count = String.valueOf(target.getComment_count());
                    columnlists.add(new Columnlist(title, author, voteup_count, comment_count));
                    util.exportExcel("专栏列表", columnNames, columnlists, new FileOutputStream("H:/0714/Columns1.xls"), ExportExcelUtil.EXCEl_FILE_2007);
                }
            }
            System.out.println("----" + i);
        }
    }
}
