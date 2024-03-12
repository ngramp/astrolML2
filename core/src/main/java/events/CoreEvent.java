package events;

/**
 * Created by Graham Perry on 28/08/16.
 *
 * @author Graham Perry
 */
public interface CoreEvent<L> {
        void notify (final L listener);
}
