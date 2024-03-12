package model;

/**
 * Created by Graham Perry on 03/07/16.
 *
 * @author Graham Perry
 */
public interface utilType {
    void get_photoid();

    //    Create a 64-bit index from the id info.
    void photoid_extract();
//    Extract run,rerun,camcol,field,id from
//    a super id created using the get_photoid() function.

    void get_objid();

    //    similar to photoid, but this is the id used in the CAS.
//    You can not just read off the run,rerun etc by looking
//    at the id because bit shifts are used.
    void objid_extract();
//    Extract run,rerun,camcol,field,id from
//    a super id created using the get_objid() function.

    void nmgy2mag();

    //    Convert nano-maggies to log magnitude.  If ivar is sent, the tuple
//            (mag, err) is returned.
    void mag2nmgy();
//    Convert from magnitudes to nano-maggies.
//            nmgy2lups(nmgy, ivar=None, band=None):
//    Convert from nano-maggies to luptitudes, which are asinh based
//    mags.

    void make_cmodelflux();
//    Combine dev and exp fluxes into a composite flux.  The basic
//    formula is
//    fc = (1-fracdev)*flux_exp + fracdev*flux_dev
//    public void make_cmodelmag(recarr, doerr=True, dered=False, lups=False)
//    Combine dev and exp fluxes into a composite flux, and then convert
//    to mags.

    void dered_fluxes();
//    Deredden fluxes using the 'extinction' given in mags by
//    the SDSS pipeline.  It is slightly more precise to correct
//    the fluxes before converting to mags.
}
