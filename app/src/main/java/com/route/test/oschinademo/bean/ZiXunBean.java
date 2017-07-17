package com.route.test.oschinademo.bean;

import java.util.List;

/**
 * Created by my301s on 2017/7/12.
 */

//@org.simpleframework.xml.Root(name = "oschina")
public class ZiXunBean {
    private String catalog;
    private String pagesize;
    private String newsCount;
    private List<NewsBean> newslist;

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(String newsCount) {
        this.newsCount = newsCount;
    }

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }

    // @org.simpleframework.xml.Root(name = "news")
    public static class NewsBean {
        private String id;
        private String title;
        private String body;
        private String commentCount;
        private String author;
        private String authorid;
        private String pubDate;
        private String url;
        private String newstype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNewstype() {
            return newstype;
        }

        public void setNewstype(String newstype) {
            this.newstype = newstype;
        }
    }
}
