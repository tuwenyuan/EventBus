package com.twy.eventbus.listener;

public enum ThreadMode {
    /**
     * 和发起事件是同一个线程
     */
    POSTING,
    /**
     * 主线程
     */
    MAIN,
    /**
     * 子线程
     */
    BACKG
}
