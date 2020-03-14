package com.kframe.loadbalanced.conf;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

/**
 * 负载均衡策略
 * 
 * @author fk
 */
public class BalanceRule1 implements IRule {

	private ILoadBalancer lb;

	@Override
	public Server choose(Object key) {
		return lb.getAllServers().get(0);
	}

	@Override
	public void setLoadBalancer(ILoadBalancer lb) {

		this.lb = lb;
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		// TODO Auto-generated method stub
		return lb;
	}

}
