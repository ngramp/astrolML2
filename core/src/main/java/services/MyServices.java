package services;

import javafx.util.StringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spi.IProvider;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Manages service provider for all categories in a generic manner
 * Created by Graham Perry on 20/08/16.
 *
 * @author Graham Perry
 */

public class MyServices<T extends IProvider>{
    private static Map<Class, MyServices> registry;
    //private static MyServices service;
    private ServiceLoader<T> loader;
    private static final Logger logger = LoggerFactory.getLogger(MyServices.class);

    private MyServices(Class<T> clazz) throws IOException {
        Path path = FileSystems.getDefault().getPath("plugins");
        loader = loadPlugins(clazz, path);
        //loader = ServiceLoader.load(DataSource.class);
        listServices();
    }

    @SuppressWarnings("unchecked")
    public static synchronized <T extends IProvider> MyServices<T> getProviders(Class<T> clazz) {
        MyServices<T> service = null;
        try {
            if (registry == null) {
                service = new MyServices<T>(clazz);
                registry = new HashMap<>();
                registry.put(clazz,service);
            }
            else{
                if (registry.containsKey(clazz)){
                    service =   registry.get(clazz);
                }
                else{
                    service = new MyServices<>(clazz);
                    registry.put(clazz,service);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    private ServiceLoader<T> loadPlugins(Class<T> clazz, Path path) {
        List<URL> fileNames = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path each : directoryStream) {
                fileNames.add(each.toUri().toURL());
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        URL[] array = fileNames.stream().toArray(URL[]::new);
        ClassLoader cl = new URLClassLoader(array, MyServices.class.getClassLoader());
        return ServiceLoader.load(clazz, cl);
    }

    private void listServices() {

        try {
            Iterator<T> providers = loader.iterator();
            logger.info("Loading services:");
            int i = 0;
            while (providers.hasNext()) {
                T d = providers.next();
                i++;
                logger.info(d.getTitle());
            }
            logger.info("Total: " + i);

        } catch (ServiceConfigurationError serviceError) {
            serviceError.printStackTrace();
        }
    }

    public List<T> getList() {
        List<T> list = new ArrayList<>();
        try {
            for (T aLoader : loader) {
                list.add(aLoader);
            }
        } catch (ServiceConfigurationError serviceError) {
            serviceError.printStackTrace();

        }
        return list;
    }

    public StringConverter<T> getClassConverter() {
        return new MyClassConverter();
    }

    /**
     * Class reposible for finding an implementation of a service based on its title
     */
    private class MyClassConverter extends StringConverter<T> {

        @Override
        public T fromString(String string) {

            T source = null;
            try {
                Iterator<T> providers = loader.iterator();
                logger.info("Looking for service with title " + string + " in " + loader.toString());
                while (providers.hasNext()) {
                    T d = providers.next();
                    if (d.getTitle().equals(string)) {
                        source = d;
                    }
                }
            } catch (ServiceConfigurationError serviceError) {
                source = null;
                serviceError.printStackTrace();

            }
            return source;
        }

        @Override
        public String toString(T myClassinstance) {
            // convert a myClass instance to the text displayed in the choice box
            return myClassinstance.getTitle();
        }
    }
}