package com.itany;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Author：汤小洋
 * Date：2018-12-10 16:45
 * Description：<描述>
 */
public class DataWatcher implements Watcher {
    public void process(WatchedEvent watchedEvent) {
        System.out.println("DataWatcher.process==============="+watchedEvent);
    }
}
