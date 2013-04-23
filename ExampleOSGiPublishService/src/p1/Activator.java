package p1;
import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import service.impl.HelloWorldImpl;
import service.itf.HelloWorldItf;


public class Activator implements BundleActivator{

	
	ServiceRegistration reg;
	@Override
	public void start(BundleContext ctx) throws Exception {
		//property for the service
		Dictionary<String, ? extends Object> args = new Hashtable<String,  Object>() ;
		 reg = ctx.registerService(HelloWorldItf.class.getName(), new HelloWorldImpl(), args);
	}

	@Override
	public void stop(BundleContext ctx) throws Exception {
		if (reg!=null)
			reg.unregister();
		
	}

}
