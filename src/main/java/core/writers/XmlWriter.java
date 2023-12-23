package core.writers;

import org.testng.annotations.Test;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class XmlWriter {

    private static final Logger LOGGER = Logger.getLogger(Thread.currentThread()
            .getStackTrace()[0].getClassName());

    public static void writeXml(String elementName, String elementValue) {
        try {
            String filePath = "src/test/resources/WriteFiles/xmlData.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filePath);
            Node users = doc.getElementsByTagName("Data").item(0);
            NodeList nodes = users.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node element = nodes.item(i);
                if (elementName.equals(element.getNodeName())) {
                    element.setTextContent("1500");
                    users.removeChild(element);
                }
            }
            Element docElement = doc.createElement(elementName);
            docElement.appendChild(doc.createTextNode(elementValue));
            users.appendChild(docElement);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
            LOGGER.info("Done writing XML");
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
    public void test() {
        writeXml("age", "27");
    }
}
