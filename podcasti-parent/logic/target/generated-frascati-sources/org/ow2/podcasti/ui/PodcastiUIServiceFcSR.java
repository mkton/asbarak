/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.ServiceReferenceClassGenerator
 * on: Mon Aug 23 14:31:14 CEST 2010
 */

package org.ow2.podcasti.ui;


public class PodcastiUIServiceFcSR
extends org.ow2.frascati.tinfi.osoa.ServiceReferenceImpl<org.ow2.podcasti.ui.PodcastiUIService>
implements org.ow2.podcasti.ui.PodcastiUIService {

  public PodcastiUIServiceFcSR(Class<org.ow2.podcasti.ui.PodcastiUIService> businessInterface,org.ow2.podcasti.ui.PodcastiUIService service)  {
    super(businessInterface,service);
  }

  public void playOnServer(final java.net.URI arg0)  {
    final org.ow2.podcasti.ui.PodcastiUIService impl = getService();
    final org.osoa.sca.ServiceReference<org.ow2.podcasti.ui.PodcastiUIService> sr = this;
    impl.playOnServer(arg0);
  }

  public void removeFeed(final java.lang.Integer arg0)  {
    final org.ow2.podcasti.ui.PodcastiUIService impl = getService();
    final org.osoa.sca.ServiceReference<org.ow2.podcasti.ui.PodcastiUIService> sr = this;
    impl.removeFeed(arg0);
  }

  public java.util.HashSet<org.ow2.podcasti.model.Feed> getFeeds()  {
    final org.ow2.podcasti.ui.PodcastiUIService impl = getService();
    final org.osoa.sca.ServiceReference<org.ow2.podcasti.ui.PodcastiUIService> sr = this;
    java.util.HashSet<org.ow2.podcasti.model.Feed> ret = impl.getFeeds();
    return ret;
  }

  public java.util.HashSet<org.ow2.podcasti.core.Episode> get3Last(final java.lang.Integer arg0)  {
    final org.ow2.podcasti.ui.PodcastiUIService impl = getService();
    final org.osoa.sca.ServiceReference<org.ow2.podcasti.ui.PodcastiUIService> sr = this;
    java.util.HashSet<org.ow2.podcasti.core.Episode> ret = impl.get3Last(arg0);
    return ret;
  }

  public boolean addFeed(final java.net.URI arg0)  {
    final org.ow2.podcasti.ui.PodcastiUIService impl = getService();
    final org.osoa.sca.ServiceReference<org.ow2.podcasti.ui.PodcastiUIService> sr = this;
    boolean ret = impl.addFeed(arg0);
    return ret;
  }

}
