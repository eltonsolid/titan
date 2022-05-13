package br.com.elementi.core.xml;

import com.google.common.collect.Lists;

import java.util.List;

public class BVBG_001 {

    private SincadService service;

    private class EntityValue {
        public EntityValue(String document, String name) {
            this.document = document;
            this.name = name;
        }

        public String document;
        public String name;


    }


    public BVBG_001(SincadService service) {
        this.service = service;
    }

    public static BVBG_001 use(SincadService service) {
        return new BVBG_001(service);
    }

    public XmlFile open(XmlFile receive) {
        XmlFile response = XmlFile.accountRespose(receive.creator());
        try {
            fileControll(receive);
            preProcessor(receive);
            process(response, receive);
        } catch (Exception exception) {
            handleFatal(response, exception);
        }
        return response;
    }

    private void preProcessor(XmlFile receive) {
        List<String> processed = Lists.newArrayList();
        for (XmlBizGrp bizGrp : receive.getBizGrp()) {
            insertEntityOwner(processed,receive.creator(), bizGrp);
        }

    }

    private void insertEntityOwner(List<String> processed, XmlFileHeader creator, XmlBizGrp bizGrp) {
        EntityValue entityValue = getEntityValue(bizGrp);
        if (!processed.contains(entityValue.document)) {
            processed.add(entityValue.document);
            insertEntity(entityValue);
            insertOwner(creator.from.identification ,entityValue);
        }
    }

    private void insertEntity(EntityValue entityValue) {
        if (!service.existEntity(entityValue.document)) {
            service.insertEntity(entityValue.document, entityValue.name);
        }

    }

    private void insertOwner(String participant, EntityValue entityValue) {
        service.existOwner(participant, entityValue.document);



    }

    private EntityValue getEntityValue(XmlBizGrp bizGrp) {
        if (bizGrp.body.createAccount.accountOwner.individual != null) {

            return new EntityValue(bizGrp.body.createAccount.accountOwner.individual.document.number, bizGrp.body.createAccount.accountOwner.individual.name);
        }
        if (bizGrp.body.createAccount.accountOwner.organization != null) {
            return new EntityValue(bizGrp.body.createAccount.accoun tOwner.organization.mainDocument, bizGrp.body.createAccount.accountOwner.organization.name);
        }
        throw new IllegalStateException("File Invalid");
    }

    public void handleFatal(XmlFile response, Exception exception) {
        exception.printStackTrace();
    }

    private void process(XmlFile response, XmlFile receive) {
        for (XmlBizGrp bizGrp : receive.getBizGrp()) {
            response.add(process(bizGrp));
        }

    }

    private XmlBizGrp process(XmlBizGrp bizGrp) {
        try {
            xsd(bizGrp);
            polices(bizGrp);
            XmlDetailAccount detailAccount = insert(bizGrp);
            return XmlBizGrp.create(bizGrp.header, detailAccount);
        } catch (Exception exception) {
            return handle(bizGrp.header, exception);
        }
    }

    private XmlDetailAccount insert(XmlBizGrp bizGrp) {
        return null;
    }

    private XmlBizGrp handle(XmlHeader header, Exception exception) {
        return exception instanceof XmlException ? erro(header, ((XmlException) exception)) : erro(header, exception);
    }


    private XmlBizGrp erro(XmlHeader header, Exception exception) {
        String description = exception.getMessage() == null ? exception.getClass().getName() : exception.getMessage();
        return erro(header, new XmlException("999", description));
    }

    private XmlBizGrp erro(XmlHeader header, XmlException exception) {
        exception.printStackTrace();
        XmlErro erro = XmlErro.create(header);
        for (XmlException.Value value : exception.values) {
            erro.add(value.code, value.description);
        }
        return XmlBizGrp.create(header, erro);
    }


    private void polices(XmlBizGrp receive) {

    }

    private void xsd(XmlBizGrp receive) {

    }

    private void fileControll(XmlFile receive) {

    }


}
