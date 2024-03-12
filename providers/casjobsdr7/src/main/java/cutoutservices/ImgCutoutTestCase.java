/**
 * ImgCutoutTestCase.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cutoutservices;

public class ImgCutoutTestCase extends junit.framework.TestCase {
    public ImgCutoutTestCase(String name) {
        super(name);
    }

    public void testImgCutoutSoapWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new ImgCutoutLocator().getImgCutoutSoapAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new ImgCutoutLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1ImgCutoutSoapRevisions() throws Exception {
        ImgCutoutSoap_BindingStub binding;
        try {
            binding = (ImgCutoutSoap_BindingStub)
                    new ImgCutoutLocator().getImgCutoutSoap();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        String[] value = null;
        value = binding.revisions();
        // TBD - validate results
    }

    public void test2ImgCutoutSoapGetJpeg() throws Exception {
        ImgCutoutSoap_BindingStub binding;
        try {
            binding = (ImgCutoutSoap_BindingStub)
                    new ImgCutoutLocator().getImgCutoutSoap();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        byte[] value = null;
        value = binding.getJpeg(0, 0, 0, 0, 0, new String());
        // TBD - validate results
    }

    public void test3ImgCutoutSoapGetJpegQuery() throws Exception {
        ImgCutoutSoap_BindingStub binding;
        try {
            binding = (ImgCutoutSoap_BindingStub)
                    new ImgCutoutLocator().getImgCutoutSoap();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        byte[] value = null;
        value = binding.getJpegQuery(0, 0, 0, 0, 0, new String(), new String());
        // TBD - validate results
    }

    public void test4ImgCutoutSoapDimeJpeg() throws Exception {
        ImgCutoutSoap_BindingStub binding;
        try {
            binding = (ImgCutoutSoap_BindingStub)
                    new ImgCutoutLocator().getImgCutoutSoap();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.dimeJpeg(0, 0, 0, 0, 0, new String());
        // TBD - validate results
    }

    public void testImgCutoutSoap12WSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new ImgCutoutLocator().getImgCutoutSoap12Address() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new ImgCutoutLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test5ImgCutoutSoap12Revisions() throws Exception {
        ImgCutoutSoap12Stub binding;
        try {
            binding = (ImgCutoutSoap12Stub)
                    new ImgCutoutLocator().getImgCutoutSoap12();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        String[] value = null;
        value = binding.revisions();
        // TBD - validate results
    }

    public void test6ImgCutoutSoap12GetJpeg() throws Exception {
        ImgCutoutSoap12Stub binding;
        try {
            binding = (ImgCutoutSoap12Stub)
                    new ImgCutoutLocator().getImgCutoutSoap12();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        byte[] value = null;
        value = binding.getJpeg(0, 0, 0, 0, 0, new String());
        // TBD - validate results
    }

    public void test7ImgCutoutSoap12GetJpegQuery() throws Exception {
        ImgCutoutSoap12Stub binding;
        try {
            binding = (ImgCutoutSoap12Stub)
                    new ImgCutoutLocator().getImgCutoutSoap12();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        byte[] value = null;
        value = binding.getJpegQuery(0, 0, 0, 0, 0, new String(), new String());
        // TBD - validate results
    }

    public void test8ImgCutoutSoap12DimeJpeg() throws Exception {
        ImgCutoutSoap12Stub binding;
        try {
            binding = (ImgCutoutSoap12Stub)
                    new ImgCutoutLocator().getImgCutoutSoap12();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.dimeJpeg(0, 0, 0, 0, 0, new String());
        // TBD - validate results
    }

}
