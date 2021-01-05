package net.stapi.mv.config;

import net.stapi.mv.service.delay.AbstractDelayConfig;
import net.stapi.mv.service.delay.DefaultDelayConfig;

public class ServiceStubConfig {
    private String localName;
    private String remoteName;
    private AbstractDelayConfig delayConfig;

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getRemoteName() {
        return remoteName;
    }

    public void setRemoteName(String remoteName) {
        this.remoteName = remoteName;
    }

    public AbstractDelayConfig getDelayConfig() {
        return delayConfig;
    }

    public void setDelayConfig(AbstractDelayConfig delayConfig) {
        this.delayConfig = delayConfig;
    }

}