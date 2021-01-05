package net.stapi.mv.service.delay;

public class DefaultDelayConfig extends AbstractDelayConfig {

    @Override
    public long getDelay() {
        return params[0];
    }
}
