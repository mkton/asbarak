/*
 * Generated by: org.ow2.frascati.tinfi.opt.oo.InitializerOOCtrlClassGenerator
 * on: Mon Aug 23 14:31:14 CEST 2010
 */

package org.ow2.podcasti.core;


public class PodcastiCoreImplFCscaPrimitiveFCd9c82204
extends juliac.generated.scaPrimitive
implements org.objectweb.fractal.juliac.runtime.Factory {

  public org.objectweb.fractal.api.Type getFcInstanceType()  {
    try {
      return new org.objectweb.fractal.julia.type.BasicComponentType( new org.objectweb.fractal.api.type.InterfaceType[]{new org.objectweb.fractal.julia.type.BasicInterfaceType("podcasti-ui-service","org.ow2.podcasti.ui.PodcastiUIService",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("podcasti-db-reference","org.ow2.podcasti.model.PodcastiDBService",true,true,false),} );
    }
    catch( org.objectweb.fractal.api.factory.InstantiationException ie ) {
      throw new org.objectweb.fractal.juliac.runtime.RuntimeException(ie);
    }
  }

  public Object getFcControllerDesc()  {
      return "scaPrimitive";
  }

  public Object getFcContentDesc()  {
      return "org.ow2.podcasti.core.PodcastiCoreImpl";
  }

  public Object newFcContent() throws org.objectweb.fractal.api.factory.InstantiationException  {
    return null;
  }

  public org.objectweb.fractal.api.Component newFcInstance() throws org.objectweb.fractal.api.factory.InstantiationException  {
    Object content = newFcContent();
    return newFcInstance(content);
  }

  public org.objectweb.fractal.api.Component newFcInstance(Object content) throws org.objectweb.fractal.api.factory.InstantiationException  {
    org.objectweb.fractal.julia.InitializationContext ic = newFcInitializationContext(content);
    ic.content = org.ow2.podcasti.core.PodcastiCoreImpl.class;
    org.objectweb.fractal.api.Interface proxy;
    Object intercept;
    org.objectweb.fractal.api.Component proxyForCompCtrl = (org.objectweb.fractal.api.Component) ic.interfaces.get("component");
    ic.type = new org.objectweb.fractal.julia.type.BasicComponentType( new org.objectweb.fractal.api.type.InterfaceType[]{new org.objectweb.fractal.julia.type.BasicInterfaceType("component","org.objectweb.fractal.api.Component",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("sca-component-controller","org.ow2.frascati.tinfi.control.component.ReconfigurableComponentContext",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("binding-controller","org.objectweb.fractal.api.control.BindingController",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("super-controller","org.objectweb.fractal.julia.control.content.SuperControllerNotifier",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("lifecycle-controller","org.objectweb.fractal.julia.control.lifecycle.LifeCycleCoordinator",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("name-controller","org.objectweb.fractal.api.control.NameController",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("/sca-content-controller","org.ow2.frascati.tinfi.control.content.SCAContentController",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("sca-intent-controller","org.ow2.frascati.tinfi.control.intent.SCAIntentController",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("sca-property-controller","org.ow2.frascati.tinfi.control.property.SCAPropertyController",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("podcasti-ui-service","org.ow2.podcasti.ui.PodcastiUIService",false,false,false),new org.objectweb.fractal.julia.type.BasicInterfaceType("podcasti-db-reference","org.ow2.podcasti.model.PodcastiDBService",true,true,false),} );
    intercept = new org.ow2.podcasti.ui.PodcastiUIServiceInterceptorSCALCb56bb98SCACCIntent();
    ic.controllers.add(intercept);
    ((org.objectweb.fractal.julia.Interceptor)intercept).setFcItfDelegate(content);
    proxy = new org.ow2.podcasti.ui.PodcastiUIServiceFcInItf(proxyForCompCtrl,"podcasti-ui-service",new org.objectweb.fractal.julia.type.BasicInterfaceType("podcasti-ui-service","org.ow2.podcasti.ui.PodcastiUIService",false,false,false),false,intercept);
    ic.interfaces.put("podcasti-ui-service",proxy);
    ((org.ow2.frascati.tinfi.TinfiComponentInterceptor)intercept).setFcItf(proxy);
    intercept = new org.ow2.podcasti.model.PodcastiDBServiceInterceptorSCAIntent();
    ic.controllers.add(intercept);
    proxy = new org.ow2.podcasti.model.PodcastiDBServiceFcOutItf(proxyForCompCtrl,"podcasti-db-reference",new org.objectweb.fractal.julia.type.BasicInterfaceType("podcasti-db-reference","org.ow2.podcasti.model.PodcastiDBService",true,true,false),false,intercept);
    ic.interfaces.put("podcasti-db-reference",proxy);
    ((org.ow2.frascati.tinfi.TinfiComponentInterceptor)intercept).setFcItf(proxy);
    initFcController(ic);
    return proxyForCompCtrl;
  }

}