package net.stapi.mv.service.delay;

import java.util.Random;

public class RandomAccessDelayConfig extends AbstractDelayConfig {
    private Random random = new Random();

    @Override
    public long getDelay() {
        return params[random.nextInt(params.length)]*1000;
    }
}
