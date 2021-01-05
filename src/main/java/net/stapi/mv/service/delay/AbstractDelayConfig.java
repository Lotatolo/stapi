package net.stapi.mv.service.delay;

public abstract class AbstractDelayConfig implements DelayStubConfig{
    protected int[] params;
    protected boolean enable;
    protected String sid;


    @Override
    public void setParams(int[] params) {
        this.params = params;
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public boolean hasSid() {
        return sid != null;
    }
}
