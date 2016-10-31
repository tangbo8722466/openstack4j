package com.openstack4j.controller;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Server;
import org.openstack4j.openstack.OSFactory;

public class FactoryController {
	private OSClientV2 clientV2 = null;
	public OSClientV2 getClientV2(){
		if(clientV2 == null){
			clientV2 = OSFactory.builderV2()
					.endpoint("http://192.168.1.200:5000/v2.0")
					.credentials("admin","b42a6657ab1d4399")
					.tenantName("admin")
					.authenticate();
		}
		return clientV2;
	}
	
	private OSClientV3 clientV3 = null;
	public OSClientV3 getClientV3(){
		if(clientV3 == null){
			clientV3 = OSFactory.builderV3()
		            .endpoint("http://192.168.1.200:5000/v3")
		            .credentials("8d7cf0599c82469cabd8ba209e81a616", "b42a6657ab1d4399")
                    .scopeToDomain(Identifier.byName("admin"))
                    .authenticate();
		}
		return clientV3;
	}
	public static void main(String[] args){
		OSClientV2 client = new FactoryController().getClientV2();
		//OSClientV3 client = new FactoryController().getClientV3();
		List<Server> servers = (List<Server>) client.compute().servers().list();
		if(servers.size() > 0){
			for(Server server:servers){
				System.out.println("主机名字："+server.getName());
			}
		}
	}
	
}
