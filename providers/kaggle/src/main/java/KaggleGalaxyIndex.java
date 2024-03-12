import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Created by Graham Perry on 19/08/16.
 *
 * @author Graham Perry
 */
public class KaggleGalaxyIndex implements Iterator<KaggleObj> {
    private Iterator<KaggleObj> current;
    private static final Logger logger = LoggerFactory.getLogger(KaggleGalaxyIndex.class);
    private static final Preferences prefs = Preferences.userRoot().node("Kaggle");
    private File csvFile;

    public KaggleGalaxyIndex() {
        File prefFile = new File(prefs.get("CSV file", System.getProperty("user.home")+"/test.csv"));
        if (prefFile.getName().contains("csv")){
            csvFile = prefFile;
        }else if (prefFile.getName().contains("zip")){
            csvFile = unzip(prefFile);
        }
        CSVReader reader = new CSVReader();

        current = reader.readCSV(csvFile);
    }

    @Override
    public boolean hasNext() {
        return current.hasNext();
    }

    @Override
    public KaggleObj next() {
        return current.next();
    }

    static File unzip(File source){
        String destination = source.getParent();
        File csv = null;
        try {
            ZipFile zipFile = new ZipFile(source);
            FileHeader header = (FileHeader) zipFile.getFileHeaders().get(0);
            String filename = header.getFileName();
            zipFile.extractFile(header,destination);
            csv = new File(destination,filename);
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return csv;
    }
    private class CSVReader {

        Iterator<KaggleObj> readCSV(File csv) {

            String line = "";
            String cvsSplitBy = ",";
            List<KaggleObj> index = new ArrayList<>();
            if(csv != null && csv.exists()){
                try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
                    br.readLine(); //skip header
                    while ((line = br.readLine()) != null) {

                        // use comma as separator
                        String[] galaxy = line.split(cvsSplitBy);
                        index.add(new KaggleObj(galaxy));

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return index.listIterator();
        }

    }
}
