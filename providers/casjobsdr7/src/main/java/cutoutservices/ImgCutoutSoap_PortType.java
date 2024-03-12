/**
 * ImgCutoutSoap_PortType.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cutoutservices;

public interface ImgCutoutSoap_PortType extends java.rmi.Remote {

    /**
     * Return CVS revision numbers
     */
    String[] revisions() throws java.rmi.RemoteException;

    /**
     * Returns the bytes of the Jpeg image for a given pointing<br><b>Input
     * 1:</b> RA in degrees (double)<br><b>Input 2:</b> Dec in degrees (double)<br><b>Input
     * 3:</b> Scale, in arcsec/pixel (double)<br><b>Input 4:</b> Width in
     * pixels (int)<br><b>Input 5:</b> Height in pixels (int)<br><b>Input
     * 6:</b> Drawing options (string)<br><b>Output:</b> Image (byte[])
     */
    byte[] getJpeg(double ra_, double dec_, double scale_, int width_, int height_, String opt_) throws java.rmi.RemoteException;

    /**
     * Returns the bytes of the Jpeg image for a given pointing.<br><b>Input
     * 1:</b> RA in degrees (double)<br><b>Input 2:</b> Dec in degrees (double)<br><b>Input
     * 3:</b> Scale, in arcsec/pixel (double)<br><b>Input 4:</b> Width in
     * pixels (int)<br><b>Input 5:</b> Height in pixels (int)<br><b>Input
     * 6:</b> Drawing options (string)<br><b>Input 7:</B> Marking selection
     * (string)<br>The marking selection string can be:<UL><LI>List of objects.
     * RA and DEC columns must be included. <B>Example:</B><BR><br><TABLE><TR><TD><b>ObjId</b></TD><TD><b>RA</b></TD><TD><b>DEC</b></TD><TD><b>RMag</b></TD></TR><TR><TD>582378008234755083</TD><TD>
     * 195.578582</TD><TD> 2.548192</TD><TD> 23.565</TD></TR><TR><TD> 582378008234754800</TD><TD>
     * 195.572594</TD><TD> 2.554787</TD><TD> 21.446</TD></TR></TABLE><LI>SELECT
     * SQL query. RA and DEC columns must be included. <B>Example:</B><BR><br>'SELECT
     * top 10 p.objID, p.ra, p.dec, p.r<br>FROM fGetObjFromRect(195.5,195.65,2.5,2.6)
     * n,   PhotoPrimary p<br>WHERE n.objID=p.objID'<LI>String&nbsp;following
     * the pattern: <B><I>ObjType Band (low_mag, high_mag)</I></B><UL><LI><EM><STRONG>ObjType:</STRONG></EM>
     * S | G | P<br>S for Stars<br>G for Galaxies<br>P for both Stars and
     * Galaxies<LI><STRONG><EM>Band:</EM></STRONG>&nbsp;U |&nbsp;G |&nbsp;R
     * | I&nbsp;|&nbsp;Z | A<br>to select objects with <STRONG><EM>Band </EM></STRONG>BETWEEN
     * <STRONG><EM>low_mag</EM></STRONG> AND <STRONG><EM>high_mag</EM></STRONG><P><br>Band
     * 'A' will look for all the objects with values betwen <b><i>low_mag</i></b>
     * and <b><i>high_mag</i></b> 	  for any band (compositions of ORs).<br>Only
     * Stars, Galaxies, or PhotoPrimary objects will be marked when magnitude
     * ranges are not specified. <B>Examples:</B><BR><br>S<br>S R (0.0, 23.5)<br>G
     * A (10, 25)</P></LI></UL></LI></UL><b>Output:</b> Image (byte[])
     */
    byte[] getJpegQuery(double ra_, double dec_, double scale_, int width_, int height_, String opt_, String query_) throws java.rmi.RemoteException;

    /**
     * Returns the Jpeg image for a given pointing in a SOAP attachment
     * (DIME)<br><b>Input 1:</b> RA in degrees J2000 (double)<br><b>Input
     * 2:</b> Dec in degrees J2000 (double)<br><b>Input 3:</b> Scale, in
     * arcsec/pixel (double)<br><b>Input 4:</b> Width in pixels (int)<br><b>Input
     * 5:</b> Height in pixels (int)<br><b>Input 6:</b> Drawing options (string)<br><b>Output:</b>
     * Jpeg image in attachment
     */
    void dimeJpeg(double ra_, double dec_, double scale_, int width_, int height_, String opt_) throws java.rmi.RemoteException;
}
