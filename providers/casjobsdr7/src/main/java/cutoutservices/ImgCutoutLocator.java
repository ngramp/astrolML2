/**
 * ImgCutoutLocator.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cutoutservices;

public class ImgCutoutLocator extends org.apache.axis.client.Service implements ImgCutout {

    // Use to get a proxy class for ImgCutoutSoap
    private String ImgCutoutSoap_address = "http://casjobs.sdss.org/ImgCutoutDR7/ImgCutout.asmx";
    // The WSDD service name defaults to the port name.
    private String ImgCutoutSoapWSDDServiceName = "ImgCutoutSoap";
    // Use to get a proxy class for ImgCutoutSoap12
    private String ImgCutoutSoap12_address = "http://casjobs.sdss.org/ImgCutoutDR7/ImgCutout.asmx";
    // The WSDD service name defaults to the port name.
    private String ImgCutoutSoap12WSDDServiceName = "ImgCutoutSoap12";
    private java.util.HashSet ports = null;

    /**
     * This is an <b>XML Web Service</b> interface to fetch <b>JPEG</b>
     * image cutouts from the <b>SDSS </b> image archive.<br>Send comments
     * to <b>Maria Nieto-Santisteban</b> -- nieto@pha.jhu.edu
     */

    public ImgCutoutLocator() {
    }

    public ImgCutoutLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ImgCutoutLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    public String getImgCutoutSoapAddress() {
        return ImgCutoutSoap_address;
    }

    public String getImgCutoutSoapWSDDServiceName() {
        return ImgCutoutSoapWSDDServiceName;
    }

    public void setImgCutoutSoapWSDDServiceName(String name) {
        ImgCutoutSoapWSDDServiceName = name;
    }

    public ImgCutoutSoap_PortType getImgCutoutSoap() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ImgCutoutSoap_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getImgCutoutSoap(endpoint);
    }

    public ImgCutoutSoap_PortType getImgCutoutSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ImgCutoutSoap_BindingStub _stub = new ImgCutoutSoap_BindingStub(portAddress, this);
            _stub.setPortName(getImgCutoutSoapWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setImgCutoutSoapEndpointAddress(String address) {
        ImgCutoutSoap_address = address;
    }

    public String getImgCutoutSoap12Address() {
        return ImgCutoutSoap12_address;
    }

    public String getImgCutoutSoap12WSDDServiceName() {
        return ImgCutoutSoap12WSDDServiceName;
    }

    public void setImgCutoutSoap12WSDDServiceName(String name) {
        ImgCutoutSoap12WSDDServiceName = name;
    }

    public ImgCutoutSoap_PortType getImgCutoutSoap12() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ImgCutoutSoap12_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getImgCutoutSoap12(endpoint);
    }

    public ImgCutoutSoap_PortType getImgCutoutSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ImgCutoutSoap12Stub _stub = new ImgCutoutSoap12Stub(portAddress, this);
            _stub.setPortName(getImgCutoutSoap12WSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setImgCutoutSoap12EndpointAddress(String address) {
        ImgCutoutSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ImgCutoutSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ImgCutoutSoap_BindingStub _stub = new ImgCutoutSoap_BindingStub(new java.net.URL(ImgCutoutSoap_address), this);
                _stub.setPortName(getImgCutoutSoapWSDDServiceName());
                return _stub;
            }
            if (ImgCutoutSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ImgCutoutSoap12Stub _stub = new ImgCutoutSoap12Stub(new java.net.URL(ImgCutoutSoap12_address), this);
                _stub.setPortName(getImgCutoutSoap12WSDDServiceName());
                return _stub;
            }
        } catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("ImgCutoutSoap".equals(inputPortName)) {
            return getImgCutoutSoap();
        } else if ("ImgCutoutSoap12".equals(inputPortName)) {
            return getImgCutoutSoap12();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://skyserver.sdss.org/", "ImgCutout");
    }

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://skyserver.sdss.org/", "ImgCutoutSoap"));
            ports.add(new javax.xml.namespace.QName("http://skyserver.sdss.org/", "ImgCutoutSoap12"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

        if ("ImgCutoutSoap".equals(portName)) {
            setImgCutoutSoapEndpointAddress(address);
        } else if ("ImgCutoutSoap12".equals(portName)) {
            setImgCutoutSoap12EndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
