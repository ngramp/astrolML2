package events;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Graham Perry on 28/08/16.
 * @see "http://stackoverflow.com/questions/937302/simple-java-message-dispatching-system"
 *
 * @author Graham Perry
 */
public final class Events {

    /** mapping of class events to active listeners **/
    private final HashMap<Class,ArrayList> map = new HashMap<>(10);

    /** Add a listener to an event class **/
    public <L> void listen( Class<? extends CoreEvent<L>> evtClass, L listener) {
        final ArrayList<L> listeners = listenersOf( evtClass );
        synchronized( listeners ) {
            if ( !listeners.contains( listener ) ) {
                listeners.add( listener );
            }
        }
    }

    /** Stop sending an event class to a given listener **/
    public <L> void mute( Class<? extends CoreEvent<L>> evtClass, L listener) {
        final ArrayList<L> listeners = listenersOf( evtClass );
        synchronized( listeners ) {
            listeners.remove( listener );
        }
    }

    /** Gets listeners for a given event class **/
    @SuppressWarnings("unchecked")
    private <L> ArrayList<L> listenersOf(Class<? extends CoreEvent<L>> evtClass) {
        synchronized ( map ) {
            final ArrayList<L> existing = map.get( evtClass );
            if (existing != null) {
                return existing;
            }

            final ArrayList<L> emptyList = new ArrayList<L>(5);
            map.put(evtClass, emptyList);
            return emptyList;
        }
    }


    /** Notify a new event to registered listeners of this event class **/
    @SuppressWarnings("unchecked")
    public <L> void notify( final CoreEvent<L> evt) {
        Class<CoreEvent<L>> evtClass = (Class<CoreEvent<L>>) evt.getClass();

        listenersOf(evtClass).forEach(evt::notify);
    }

}
