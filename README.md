# Netty-Rpc-Demo

netty rpc 的一个小demo

    启动测试：
           1.NettyRpcServerApplication 
             NettyRpcClientApplication
           2.访问localhost:8080
           3.cn/like/netty/rpc/client/controller 测试对应接口

## common-netty

```
└───cn
    └───like
        └───netty
            └───common
                ├───channel         --- channel管理相关的接口(server端使用)
                ├───message         --- client和server相互传递的消息(请求以及响应)
                │   ├───auth            ---  用户注册
                │   ├───chat            ---  用户聊天
                │   ├───dispatcher      ---  消息分发
                │   ├───heartbeat       ---  心跳
                │   └───rpc             ---  rpc
                ├───protocol        --- 本demo采用的协议
                ├───serializer      --- 序列化实现
                ├───service         --- rpc调用的service接口
                └───util            --- 所包含的工具类
```

## rpc-server

client和server一样。

```
└───cn
    └───like
        └───netty
            └───rpc
                └───server
                    ├───channel     --- channel管理相关的接口实现
                    ├───config      --- 配置类
                    ├───handler     --- 消息对应的处理器
                    ├───init        --- 初始化channel中的pipline
                    └───service     --- rpc调用的service的具体实现
                    ---  NettyRpcServer server 启动
```

todo 序列化工具 Protobuf 
