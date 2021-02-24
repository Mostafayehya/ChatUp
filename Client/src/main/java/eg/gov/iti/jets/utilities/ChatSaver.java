package eg.gov.iti.jets.utilities;

import domains.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ChatSaver {
    List<Message> messages;
    DocumentBuilder builder;
    Document document;
    DOMSource domSource;
    File target;

    public ChatSaver(List<Message> messages) {
        this.messages = messages;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        fillDOMWithMessages();
        saveDocument();
        saveAsHTML();
    }

    public ChatSaver(List<Message> messages,File target){
        this.target = target;
        this.messages = messages;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            builder = factory.newDocumentBuilder();
            document = builder.newDocument();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        fillDOMWithMessages();
        saveDocument();
        saveAsHTML();
    }

    public void fillDOMWithMessages() {

        Element root = document.createElement("Messages");
        for (int i = 0; i < messages.size(); i++) {
            Element message = document.createElement("Message");
            Element senderName = document.createElement("SenderName");
            senderName.setTextContent(messages.get(i).getSenderName());
            message.appendChild(senderName);
            Element receiverName = document.createElement("ReceiverName");
            receiverName.setTextContent(messages.get(i).getReceiverName());
            message.appendChild(receiverName);
            Element messageContent = document.createElement("MessageContent");
            messageContent.setTextContent(messages.get(i).getContent());
            message.appendChild(messageContent);
            Element time = document.createElement("Time");
            time.setTextContent(messages.get(i).getTime());
            message.appendChild(time);

            root.appendChild(message);

        }
        document.appendChild(root);
    }

    public void saveDocument(){
        try {
            domSource = new DOMSource(document);
            FileOutputStream fileOutputStream = new FileOutputStream(new File("src/main/resources/xml/messages.xml"));
            StreamResult streamResult = new StreamResult(fileOutputStream);
            TransformerFactory factory1 = TransformerFactory.newInstance();
            Transformer transformer = factory1.newTransformer();
            transformer.transform(domSource, streamResult);
        }catch (FileNotFoundException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void saveAsHTML(){
        Document instrDoc = null;
        try {
            instrDoc = builder.parse(new File("src/main/resources/xml/saveChat.xsl"));
            DOMSource instrSource = new DOMSource(instrDoc);

            FileOutputStream fileOutputStream = new FileOutputStream(target);
            StreamResult result = new StreamResult(fileOutputStream);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(instrSource);
            transformer.transform(domSource, result);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
