package fr.insee.knowledge.service.impl;

import fr.insee.knowledge.repository.MongoDao;
import fr.insee.knowledge.service.ImportService;
import fr.insee.knowledge.utils.Utils;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//TODO Catch and log all exception
@Service
public class ImportServiceImpl implements ImportService {

    public ImportServiceImpl(MongoDao mongoDao) {
        this.mongoDao = mongoDao;
    }

    private MongoDao mongoDao;

    private final static Logger LOGGER = LoggerFactory.getLogger(ImportServiceImpl.class);

    @Value("${fr.insee.knowledge.git.access.rawrepository}")
    private String githubRepository;

    public String importHierarchy(String filename) throws IOException {
        String strHierarchy = Utils.readFileFromUrl(new URL(githubRepository + filename));
        Document document = Document.parse(strHierarchy);
        return mongoDao.insertHierarchy(document);
    }

    public String importListFunctions(String filename) throws IOException {
        String strFunctions = Utils.readFileFromUrl(new URL(githubRepository + filename));
        Object object = Document.parse("{\"json\":" + strFunctions + "}").get("json");
        if (object instanceof ArrayList) {
            ArrayList<Document> documents = (ArrayList<Document>) object;
            return mongoDao.insertListFunctions(documents);
        }
        if (object instanceof Document) {
            Document document = (Document) object;
            return mongoDao.insertFunction(document);
        }
        return "An error occured with data structure";
    }
}
