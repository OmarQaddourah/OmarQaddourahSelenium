package core.readers;

import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;

public class XmlReader {

    public static String readXml(String tagName) {
        String dirPath = System.getProperty("user.dir") + "/src/test/resources/WriteFiles";
        StringJoiner strJoiner = new StringJoiner("\n");

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Files.walk(Paths.get(dirPath)).filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".xml")).forEach(filePath -> {
                try {
                    File file = filePath.toFile();
                    Document doc = docBuilder.parse(file);
                    doc.getDocumentElement().normalize();
                    NodeList nodeList = doc.getElementsByTagName(tagName);

                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            String content = element.getTextContent();
                            strJoiner.add(content);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strJoiner.toString();
    }

    @Test(enabled = false)
    public void test() {
        System.out.println(readXml("name"));
    }
}
