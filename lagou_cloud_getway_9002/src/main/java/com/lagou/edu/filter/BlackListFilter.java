package com.lagou.edu.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局过滤器 对所有路由生效
 * @author zhangchi
 * @create 2020-11-12
 */
@Slf4j
@Component
public class BlackListFilter implements GlobalFilter, Ordered {


   private static List<String> blackList = new ArrayList<>();

    static{
        //黑名单
        blackList.add("0:0:0:0:0:0:0:1");
    }
    /**
     *
     * @param exchange 封装了request、response上下文对象
     * @param chain 网关过滤器链 （包含全局过滤器和单路由过滤器）
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {



        //获取客户端IP
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //客户端IP
        String hostString = request.getRemoteAddress().getHostString();

        if(blackList.contains(hostString)){
            //拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);//状态码
            System.out.println("======拒绝访问");

            String data = "no request";

            DataBuffer wrap = response.bufferFactory().wrap(data.getBytes());

            return response.writeWith(Mono.just(wrap));
        }

        //放行
        return chain.filter(exchange);
    }

    //执行优先级 数值越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
