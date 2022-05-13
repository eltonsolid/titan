package br.com.elementi.core.xml;

import java.util.Date;

/**
 * Created by eltonsolid on 07/09/17.
 */
public class XmlEvent {


    private Integer id;

    private String file;

    private Date create;

    private Date update;

    private String host;

    public Integer getId() {
        return id;
    }

    public String getFile() {
        return file;
    }

    public Date getCreate() {
        return create;
    }

    public Date getUpdate() {
        return update;
    }

    public String getHost() {
        return host;
    }

    public void close() {

    }
}
