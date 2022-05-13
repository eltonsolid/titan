package br.com.elementi.core.xml;

import br.com.elementi.core.xml.XmlResolver.XmlFileOperation;

import java.util.TimerTask;

/**
 * Created by eltonsolid on 07/09/17.
 */

public class Consumer extends TimerTask {

    FPB fpb;
    SincadService service;
    String reader = "./Performance/";
    String write = "./output/";

    public Consumer(SincadService service) {
        this.service = service;
    }

    @Override
    public void run() {
        for (XmlEvent event : service.listEvents()) {
            try {
                XmlFile fileReceive = read(event);
                XmlFile fileResponse = process(fileReceive);
                write(fileResponse);
                finishEvent(event);
                fpb(fileResponse);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    private void finishEvent(XmlEvent event) {
        event.close();
        service.update(event);
    }

    public XmlFile process(XmlFile fileReceive) throws Exception {
        XmlFileOperation operation = XmlResolver.operation(fileReceive.creator());
        switch (operation) {
            case ACCOUNT_INSERT:
                return BVBG_001.use(service).open(fileReceive);
            case ACCOUNT_UPDATE:
                return accountUpdate(fileReceive);
            case ACCOUNT_DETAIL:
                return accountDetail(fileReceive);
            case ACCOUNT_HASH:
                return hash(fileReceive);
            case LINK_INSERT_UPDATE:
                return linkInsertUpdate(fileReceive);
            case LINK_DETAIL:
                return linkDetail(fileReceive);
            default:
                return XmlFile.erro(fileReceive.creator());
        }
    }

    public void fpb(XmlFile fileResponse) {
        fpb.call(write + fileResponse.fileName());
    }

    public void write(XmlFile fileResponse) throws Exception {
        Xml.write(fileResponse, write + fileResponse.fileName());
    }

    public XmlFile read(XmlEvent event) throws Exception {
        return Xml.read(reader + event.getFile());
    }

    private XmlFile accountInsert(XmlFile fileReceive) {
        BVBG_001.use(service).open(fileReceive);



        XmlFile response = XmlFile.accountRespose(fileReceive.creator());
        for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
            response.add(new XmlProcessAccount().insert(bizGrp));
        }
        return response;
    }

    private XmlFile accountUpdate(XmlFile fileReceive) {
        XmlFile response = XmlFile.accountRespose(fileReceive.creator());
        for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
            response.add(new XmlProcessAccount().update(bizGrp));
        }
        return response;
    }

    private XmlFile accountDetail(XmlFile fileReceive) {
        XmlFile response = XmlFile.accountRespose(fileReceive.creator());
        for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
            response.add(new XmlProcessAccount().update(bizGrp));
        }
        return response;
    }

    private XmlFile hash(XmlFile fileReceive) {
        XmlFile response = XmlFile.hash(fileReceive.creator());
        for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
            response.add(XmlProcessAccount.hash(bizGrp));
        }
        return response;
    }

    private XmlFile linkInsertUpdate(XmlFile fileReceive) {
        XmlFile response = XmlFile.link(fileReceive.creator());
        for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
            response.add(new XmlProcessAccount().update(bizGrp));
        }
        return response;
    }

    private XmlFile linkDetail(XmlFile fileReceive) {
        XmlFile response = XmlFile.link(fileReceive.creator());
        for (XmlBizGrp bizGrp : fileReceive.getBizGrp()) {
            response.add(new XmlProcessAccount().update(bizGrp));
        }
        return response;
    }


}
