package com.route.test.oschinademo.utils;

/**
 * Created by admin on 2017/4/10.
 */
/**
 * 所有的网址类
 *
 */

public class Urls {

    //后台服务器的接口
    public static  final  String Base_Url="http://www.oschina.net/";
    //获取赞
    public static final String Zan=Base_Url+"action/api/my_tweet_like_list";
    //获取新闻评论列表
    public static final String DongTanPingLun=Base_Url+"action/api/comment_list";
    //线下活动
    public static final String XianXiaHuoDong=Base_Url+"action/api/event_list";
  //发表评论
    public static final String FaBiaoPingLun=Base_Url+"action/api/comment_pub";
    //粉丝
    public static final String FenSi=Base_Url+"action/api/friends_list";
    //线下详情
    public static final String XianXiaXiangQing=Base_Url+"action/api/post_detail";
    //点赞喜欢
    public static final String DianZanXiHuan=Base_Url+"action/api/tweet_like";
    //点赞不喜欢
    public static final String DianZanBuXiHuan=Base_Url+"action/api/tweet_unlike";
    //发表动弹
    public static final String FaBiaoDongTan=Base_Url+"action/api/tweet_pub";
    //用户信息
    public static final String UserInfo=Base_Url+"action/api/my_information";
    //找人的接口
    public static final String ZhaoRen=Base_Url+"action/api/find_user";
    //登录的接口
    public static final String Login=Base_Url+"action/api/login_validate";
    //搜索的接口
    public static final String Search=Base_Url+"action/api/search_list";
    //咨询的接口
    public static  final String NewsList=Base_Url+"action/api/news_list";
  //技术问答
    public static final String JiShuWenDa=Base_Url+"action/api/post_list";
    //技术问答详情
    public static final String JiShuWenDaXiangQing=Base_Url+"action/api/post_detail";
    //咨询跳转到详情的接口
    public static final String Detail=Base_Url+"action/api/news_detail";

    //搜索的接口
    public static final String Find=Base_Url+"action/api/search_list";
    //博客推荐的接口
    public static final String BoKe=Base_Url+"action/api/blog_list";
    //博客详情的接口
    public static final String BoKe_detail=Base_Url+"action/api/blog_detail";
    //动弹的接口
    public  static final String DongTan=Base_Url+"action/api/tweet_list";
    //新闻详情的接口
    public  static final String NewsDetail=Base_Url+"action/api/news_detail";
    //登录的接口
    public  static final String CATA_LOGONE="1";
    public  static final String PAGE_SIZE="10";
    public  static final String CATA_LOGFOUR="4";
//博客类别
    public  static final String BLOG_TYPE="latest";
    //推荐类别
    public  static final String TUIJIE_TYPE="recommend";
    //最新动弹
    public  static final String DONGTAN_ZUIXINR="0";
    //热门动弹
    public  static final String DONGTAN_REMEN="-1";
    //我的动弹
    public  static final String DONGTAN_WO="1";

    //分类的接口
    public static  final String FenLei=Base_Url+"action/api/softwarecatalog_list";
    //二级分类的接口
    public  static final String FenLei_ErJi=Base_Url+"action/api/softwarecatalog_list";
    //二级分类的列表接口
    public  static final String FeiLei_LieBiao=Base_Url+"action/api/softwaretag_list";
    //推荐的接口
    public static final String TuiJie=Base_Url+"action/api/software_list";
    //最新的接口
    public static final String ZuiXin_=Base_Url+"action/api/software_list";
    //热门的接口
    public static final String Remen_=Base_Url+"action/api/software_list";
    //国产的接口
    public static final String GuoChan=Base_Url+"action/api/software_list";
    //摇一摇
    public static final String YaoYiYao=Base_Url+"action/api/rock_rock";
}
