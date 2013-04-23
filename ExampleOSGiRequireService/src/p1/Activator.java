package p1;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;

import service.itf.HelloWorldItf;

public class Activator implements BundleActivator, ServiceListener {

	ServiceReference ref;
	HelloWorldItf serv;
	BundleContext ctx;

	@Override
	public void start(BundleContext ctx) throws Exception {
		this.ctx = ctx;
		// property for the service
		Dictionary<String, ? extends Object> args = new Hashtable<String, Object>();
		ref = ctx.getServiceReference(HelloWorldItf.class);
		serv = ctx.getService(ref);

		if (serv != null)
			System.err.println(serv.sayHello("Toto"));

		String filter = "(objectclass=" + HelloWorldItf.class.getName() + ")";
		ctx.addServiceListener(this, filter);

	}

	@Override
	public void stop(BundleContext ctx) throws Exception {
		if (ref != null) {
			ctx.ungetService(ref);
			serv = null;
		}

	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		if (event.getType() == ServiceEvent.REGISTERED) {
			serv = (HelloWorldItf) ctx.getService(event.getServiceReference());
			if (serv != null)
				System.err.println(serv.sayHello("Toto"));
		} else if (event.getType() == ServiceEvent.UNREGISTERING) {
			ctx.ungetService(event.getServiceReference());
			serv = null;
		}
	}

}
