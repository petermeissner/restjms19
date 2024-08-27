package de.petermeissner.restjms19;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "list")
public class JaxbList<T> {
    protected List<T> list;

    public JaxbList() {
    }

    public JaxbList(List<T> list) {
        this.list = list;
    }

    @XmlElements({
            @XmlElement(name = "message", type = String.class),
    })
    public List<T> getList() {
        return list;
    }
}

