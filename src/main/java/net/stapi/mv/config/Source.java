package net.stapi.mv.config;

public class Source {
    private String sid;
    private String cid;
    private String query;

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
