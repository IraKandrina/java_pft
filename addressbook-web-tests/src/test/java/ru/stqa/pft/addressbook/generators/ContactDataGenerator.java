package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class
ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }


    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstName(),
                        contact.getLastName(),
                        contact.getAddress(),
                        contact.getHomePhone(),
                        contact.getMobilePhone(),
                        contact.getWorkPhone(),
                        contact.getEmail(),
                        contact.getEmail2(),
                        contact.getEmail3()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("Ivan %s", i))
                    .withLastName(String.format("Ivanov %s", i))
                    .withAddress(String.format("St.Petersburg, Palace Square, 1 %s", i))
                    .withHomePhone(String.format("+7 495 312-15-77 %s", i))
                    .withMobilePhone(String.format("+7(111)111-11-11 %s", i))
                    .withWorkPhone(String.format("123-45-67 %s", i))
                    .withEmail(String.format("ivanov_ivan@mail.ru %s", i))
                    .withEmail2(String.format("ivanov_ivan@list.ru %s", i))
                    .withEmail3(String.format("ivanov_ivan@gmail.com %s", i)));
        }
        return contacts;
    }
}
