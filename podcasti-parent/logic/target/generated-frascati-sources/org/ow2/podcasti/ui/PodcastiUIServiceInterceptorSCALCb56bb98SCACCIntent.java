/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.InterceptorClassGenerator
 * on: Mon Aug 23 14:31:14 CEST 2010
 */

package org.ow2.podcasti.ui;


public class PodcastiUIServiceInterceptorSCALCb56bb98SCACCIntent
extends org.ow2.frascati.tinfi.TinfiComponentInterceptor<org.ow2.podcasti.ui.PodcastiUIService>
implements org.ow2.podcasti.ui.PodcastiUIService,org.objectweb.fractal.julia.Interceptor {

  private juliac.generated.SCALifeCycleControllerImpl _lc;
  private static java.lang.reflect.Method[] METHODS;
  public PodcastiUIServiceInterceptorSCALCb56bb98SCACCIntent()  {
  }

  private PodcastiUIServiceInterceptorSCALCb56bb98SCACCIntent(Object obj)  {
    setFcItfDelegate(obj);
  }

  public void initFcController(org.objectweb.fractal.julia.InitializationContext ic) throws org.objectweb.fractal.api.factory.InstantiationException  {
    super.initFcController(ic);
    Object olc = ic.getInterface("lifecycle-controller");
    if( ! (olc instanceof juliac.generated.SCALifeCycleControllerImpl) ) {
      while( olc instanceof org.objectweb.fractal.julia.Interceptor ) {
        olc = ((org.objectweb.fractal.julia.Interceptor)olc).getFcItfDelegate();
      }
    }
    _lc = (juliac.generated.SCALifeCycleControllerImpl) olc;
    initIntentHandlersMap(METHODS);
  }

  public Object clone()  {
    PodcastiUIServiceInterceptorSCALCb56bb98SCACCIntent clone = new PodcastiUIServiceInterceptorSCALCb56bb98SCACCIntent(getFcItfDelegate());
    initFcClone(clone);
    clone._lc = _lc;
    return clone;
  }

  public java.util.HashSet<org.ow2.podcasti.model.Feed> getFeeds()  {
      synchronized(_lc) {
        if(_lc.fcState != 2)
          _lc.incrementFcInvocationCounter();
        else
          _lc.fcInvocationCounter++;
      }
      try {
    org.ow2.podcasti.ui.PodcastiUIService impl = pushFcAndGet("podcasti-ui-service",org.ow2.podcasti.ui.PodcastiUIService.class,this);
    try {
    java.util.List<org.ow2.frascati.tinfi.control.intent.IntentHandler> handlers = intentHandlersMap.get(METHODS[0]);
    try {
      if( handlers.size() == 0 ) {
        java.util.HashSet<org.ow2.podcasti.model.Feed> ret = impl.getFeeds();
        return ret;
      }
      else {
        org.ow2.frascati.tinfi.control.component.ReconfigurableComponentContext compctx = getFcCompCtxCtrlItf();
        org.objectweb.fractal.api.Interface itf = getFcItf();
        org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl<org.ow2.podcasti.ui.PodcastiUIService> ijp = new org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl();
        ijp.init(handlers,compctx,itf,impl,METHODS[0]);
    java.util.HashSet<org.ow2.podcasti.model.Feed> ret = (java.util.HashSet<org.ow2.podcasti.model.Feed>) ijp.proceed();
    return ret;
      }
    }
    catch( Throwable t ) {
      if( t instanceof RuntimeException ) {
        throw (RuntimeException) t;
      }
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(t);
    }
    }
    finally {
      releaseFcAndPop(impl,false);
    }
      }
      finally {
        synchronized(_lc) {
          if(_lc.fcState != 2)
            _lc.decrementFcInvocationCounter();
          else
            _lc.fcInvocationCounter--;
        }
      }
  }

  public boolean addFeed(final java.net.URI arg0)  {
      synchronized(_lc) {
        if(_lc.fcState != 2)
          _lc.incrementFcInvocationCounter();
        else
          _lc.fcInvocationCounter++;
      }
      try {
    org.ow2.podcasti.ui.PodcastiUIService impl = pushFcAndGet("podcasti-ui-service",org.ow2.podcasti.ui.PodcastiUIService.class,this);
    try {
    java.util.List<org.ow2.frascati.tinfi.control.intent.IntentHandler> handlers = intentHandlersMap.get(METHODS[2]);
    try {
      if( handlers.size() == 0 ) {
        boolean ret = impl.addFeed(arg0);
        return ret;
      }
      else {
        org.ow2.frascati.tinfi.control.component.ReconfigurableComponentContext compctx = getFcCompCtxCtrlItf();
        org.objectweb.fractal.api.Interface itf = getFcItf();
        org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl<org.ow2.podcasti.ui.PodcastiUIService> ijp = new org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl();
        ijp.init(handlers,compctx,itf,impl,METHODS[2],arg0);
    Object proceed = ijp.proceed();
    boolean ret = (proceed==null) ? false : (java.lang.Boolean)proceed;
    return ret;
      }
    }
    catch( Throwable t ) {
      if( t instanceof RuntimeException ) {
        throw (RuntimeException) t;
      }
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(t);
    }
    }
    finally {
      releaseFcAndPop(impl,false);
    }
      }
      finally {
        synchronized(_lc) {
          if(_lc.fcState != 2)
            _lc.decrementFcInvocationCounter();
          else
            _lc.fcInvocationCounter--;
        }
      }
  }

  public void playOnServer(final java.net.URI arg0)  {
      synchronized(_lc) {
        if(_lc.fcState != 2)
          _lc.incrementFcInvocationCounter();
        else
          _lc.fcInvocationCounter++;
      }
      try {
    org.ow2.podcasti.ui.PodcastiUIService impl = pushFcAndGet("podcasti-ui-service",org.ow2.podcasti.ui.PodcastiUIService.class,this);
    try {
    java.util.List<org.ow2.frascati.tinfi.control.intent.IntentHandler> handlers = intentHandlersMap.get(METHODS[4]);
    try {
      if( handlers.size() == 0 ) {
        impl.playOnServer(arg0);
      }
      else {
        org.ow2.frascati.tinfi.control.component.ReconfigurableComponentContext compctx = getFcCompCtxCtrlItf();
        org.objectweb.fractal.api.Interface itf = getFcItf();
        org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl<org.ow2.podcasti.ui.PodcastiUIService> ijp = new org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl();
        ijp.init(handlers,compctx,itf,impl,METHODS[4],arg0);
    ijp.proceed();
    Object ret = null;
      }
    }
    catch( Throwable t ) {
      if( t instanceof RuntimeException ) {
        throw (RuntimeException) t;
      }
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(t);
    }
    }
    finally {
      releaseFcAndPop(impl,false);
    }
      }
      finally {
        synchronized(_lc) {
          if(_lc.fcState != 2)
            _lc.decrementFcInvocationCounter();
          else
            _lc.fcInvocationCounter--;
        }
      }
  }

  public java.util.HashSet<org.ow2.podcasti.core.Episode> get3Last(final java.lang.Integer arg0)  {
      synchronized(_lc) {
        if(_lc.fcState != 2)
          _lc.incrementFcInvocationCounter();
        else
          _lc.fcInvocationCounter++;
      }
      try {
    org.ow2.podcasti.ui.PodcastiUIService impl = pushFcAndGet("podcasti-ui-service",org.ow2.podcasti.ui.PodcastiUIService.class,this);
    try {
    java.util.List<org.ow2.frascati.tinfi.control.intent.IntentHandler> handlers = intentHandlersMap.get(METHODS[3]);
    try {
      if( handlers.size() == 0 ) {
        java.util.HashSet<org.ow2.podcasti.core.Episode> ret = impl.get3Last(arg0);
        return ret;
      }
      else {
        org.ow2.frascati.tinfi.control.component.ReconfigurableComponentContext compctx = getFcCompCtxCtrlItf();
        org.objectweb.fractal.api.Interface itf = getFcItf();
        org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl<org.ow2.podcasti.ui.PodcastiUIService> ijp = new org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl();
        ijp.init(handlers,compctx,itf,impl,METHODS[3],arg0);
    java.util.HashSet<org.ow2.podcasti.core.Episode> ret = (java.util.HashSet<org.ow2.podcasti.core.Episode>) ijp.proceed();
    return ret;
      }
    }
    catch( Throwable t ) {
      if( t instanceof RuntimeException ) {
        throw (RuntimeException) t;
      }
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(t);
    }
    }
    finally {
      releaseFcAndPop(impl,false);
    }
      }
      finally {
        synchronized(_lc) {
          if(_lc.fcState != 2)
            _lc.decrementFcInvocationCounter();
          else
            _lc.fcInvocationCounter--;
        }
      }
  }

  public void removeFeed(final java.lang.Integer arg0)  {
      synchronized(_lc) {
        if(_lc.fcState != 2)
          _lc.incrementFcInvocationCounter();
        else
          _lc.fcInvocationCounter++;
      }
      try {
    org.ow2.podcasti.ui.PodcastiUIService impl = pushFcAndGet("podcasti-ui-service",org.ow2.podcasti.ui.PodcastiUIService.class,this);
    try {
    java.util.List<org.ow2.frascati.tinfi.control.intent.IntentHandler> handlers = intentHandlersMap.get(METHODS[1]);
    try {
      if( handlers.size() == 0 ) {
        impl.removeFeed(arg0);
      }
      else {
        org.ow2.frascati.tinfi.control.component.ReconfigurableComponentContext compctx = getFcCompCtxCtrlItf();
        org.objectweb.fractal.api.Interface itf = getFcItf();
        org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl<org.ow2.podcasti.ui.PodcastiUIService> ijp = new org.ow2.frascati.tinfi.control.intent.IntentJoinPointImpl();
        ijp.init(handlers,compctx,itf,impl,METHODS[1],arg0);
    ijp.proceed();
    Object ret = null;
      }
    }
    catch( Throwable t ) {
      if( t instanceof RuntimeException ) {
        throw (RuntimeException) t;
      }
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(t);
    }
    }
    finally {
      releaseFcAndPop(impl,false);
    }
      }
      finally {
        synchronized(_lc) {
          if(_lc.fcState != 2)
            _lc.decrementFcInvocationCounter();
          else
            _lc.fcInvocationCounter--;
        }
      }
  }

  static {
    try {
      METHODS = new java.lang.reflect.Method[]{
        org.ow2.podcasti.ui.PodcastiUIService.class.getMethod("getFeeds"),
        org.ow2.podcasti.ui.PodcastiUIService.class.getMethod("removeFeed",java.lang.Integer.class),
        org.ow2.podcasti.ui.PodcastiUIService.class.getMethod("addFeed",java.net.URI.class),
        org.ow2.podcasti.ui.PodcastiUIService.class.getMethod("get3Last",java.lang.Integer.class),
        org.ow2.podcasti.ui.PodcastiUIService.class.getMethod("playOnServer",java.net.URI.class),
      };
    }
    catch( NoSuchMethodException e ) {
      throw new org.ow2.frascati.tinfi.TinfiRuntimeException(e);
    }
  }

}
