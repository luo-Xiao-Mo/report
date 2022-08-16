package com.gwssi.calculate.blame;

import java.util.List;

public abstract class Handler {
    private Handler handler;
    private String expression;

    public Handler(String expression) {
        this.expression = expression;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public abstract List<String> process();
}
