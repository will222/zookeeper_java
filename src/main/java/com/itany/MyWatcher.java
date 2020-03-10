package com.itany;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Author：汤小洋
 * Date：2018-12-10 16:22
 * Description：<描述>
 */
public class MyWatcher implements Watcher {
    public void process(WatchedEvent watchedEvent) {
        System.out.println("MyWatcher.process------" + watchedEvent);
    }
}
