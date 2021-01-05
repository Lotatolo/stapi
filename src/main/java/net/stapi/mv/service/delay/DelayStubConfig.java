package net.stapi.mv.service.delay;

public interface DelayStubConfig {

    void setParams(int[] params);

    long getDelay();

    boolean isEnable();
}
