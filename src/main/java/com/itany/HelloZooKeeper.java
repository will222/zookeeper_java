package com.itany;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * Author：汤小洋
 * Date：2018-12-10 16:19
 * Description：<描述>
 */
public class HelloZooKeeper {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //获取ZooKeeper的连接，即创建ZooKeeper的客户端
        String connectString = "127.0.0.1:2181";  //服务器地址
        int sessionTimeout = 3000; //超时时间，单位为毫秒
        Watcher watcher = new MyWatcher();
        ZooKeeper zkClient = new ZooKeeper(connectString, sessionTimeout, watcher);

        // Thread.sleep(2000);
         System.out.println(zkClient.getState());


        /**
         * 操作ZooKeeper
         */
        //查看指定节点下的内容
         List<String> children = zkClient.getChildren("/", true);//第二个参数表示是否监视该节点
         System.out.println(children);

        //创建节点，OPEN_ACL_UNSAFE表示acl权限列表为完全开放，PERSISTENT表示节点类型为持久化节点
         zkClient.create("/world", "世界".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //获取节点的数据（节点的值和节点状态Stat）
         byte[] data = zkClient.getData("/world", true, null);
         System.out.println(new String(data));

         Stat stat = new Stat();
//         byte[] data = zkClient.getData("/hello", true, stat);
//         byte[] data = zkClient.getData("/hello", new DataWatcher(), stat);
         System.out.println(new String(data));
          System.out.println(stat);
         System.out.println(stat.getCtime());
         System.out.println(stat.getVersion());
         System.out.println(stat.getDataLength());

        //修改节点的数据
         zkClient.setData("/hello","aaa".getBytes(),stat.getVersion()); //第三个参数表示当前节点的数据版本，一般先获取数据stat，然后指定数据版本
         zkClient.setData("/hello", "bbb".getBytes(), -1);  //也可以设置为-1，表示不检测版本

        //删除节点
         zkClient.delete("/hello", -1);

        //判断节点是否存在
        System.out.println(zkClient.exists("/hello",false));  //存在时返回节点状态，不存在则返回null

        //休眠
         Thread.sleep(1000000);

        //关闭连接
        zkClient.close();
    }
}
