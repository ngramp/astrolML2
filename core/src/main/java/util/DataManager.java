package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;


/**
 * Created by gram on 24/03/16.
 */
class DataManager {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);
    private Criteria criteria;
    private Session session;
    private Class data;

    DataManager(Class data) {
        logger.trace("Starting data manager for class " +data.toString());
        this.data = data;
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        logger.trace("Starting transaction for class " +data.toString());
        session.beginTransaction();
        criteria = session.createCriteria(data);
    }
    private void commit() {
        logger.trace("Commiting data for class " + data.toString());
        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
    }

    ObservableList observableList(Class cls) {

        criteria.setFirstResult(0);
        criteria.setMaxResults(1000);

        //String hql = "from " + obj.getClass().getName();
        //Query query = session.createQuery(hql);
        //query.setMaxResults(10);
        ObservableList result = FXCollections.observableArrayList(criteria.list());

        session.getTransaction().commit();
        HibernateUtil.getSessionFactory().close();
        return result;
    }
    //implement closure around callback function
    void scrollOverResults(CallBack callback) {
        criteria.setReadOnly(true);
        criteria.setFetchSize(100);
        ScrollableResults scrollable = criteria.scroll(ScrollMode.FORWARD_ONLY);
        callback.Scrollable(scrollable);
        scrollable.close();
        commit();
    }
    void saveAll(Runnable callback) {
        callback.run();
        commit();
    }
}
