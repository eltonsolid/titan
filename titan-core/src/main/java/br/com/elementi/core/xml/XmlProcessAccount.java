package br.com.elementi.core.xml;

import br.com.elementi.core.xml.XmlException.Value;

import java.util.List;

public class XmlProcessAccount {

    public static XmlBizGrp hash(XmlBizGrp bizGrp) {
        return null;
    }

    public XmlBizGrp insert(XmlBizGrp bizGrp) {
        try {
            XmlCreateAccount createAccount = bizGrp.body.createAccount;
            validateAccountExist(createAccount);
            validateBizGrpValues(createAccount);
            Account account = toAccount(createAccount);
            applayAccountConstraint(account);
            applaySincadRules(account);
            insertAccount(account);
            XmlDetailAccount detailAccount = toXml(bizGrp.header, account);
            return XmlBizGrp.create(bizGrp.header, detailAccount);
        } catch (XmlException exception) {
            return erro(bizGrp.header, exception);
        } catch (Exception exception) {
            return erro(bizGrp.header, exception);
        }
    }

    private void insertAccount(Account account) {
        service().insertAccount(account);
    }

    public XmlBizGrp update(XmlBizGrp bizGrp) {
        try {
            XmlUpdateAccount updateAccount = bizGrp.body.updateAccount;
            validateBizGrpValues(updateAccount);
            Account account = returnAccountIfExist(updateAccount);
            merge(updateAccount, account);
            applayAccountConstraint(account);
            applaySincadRules(account);
            XmlDetailAccount detailAccount = toXml(bizGrp.header, account);
            return XmlBizGrp.create(bizGrp.header, detailAccount);
        } catch (XmlException exception) {
            return erro(bizGrp.header, exception);
        } catch (Exception exception) {
            return erro(bizGrp.header, exception);
        }
    }

    private void merge(XmlUpdateAccount updateAccount, Account account) {
        XmlMergeAccount.merge(updateAccount, account);
    }

    private XmlDetailAccount toXml(XmlHeader header, Account account) {
        return XmlMapper.mapper(account, XmlDetailAccount.create(header), null);
    }

    private void applaySincadRules(Account account) {
        service().executeRules(account);
    }

    private void applayAccountConstraint(Account account) {
        XmlConstraint.applay(account);
    }

    private Account toAccount(XmlCreateAccount createAccount) {
        return XmlMapper.mapper(createAccount, new Account(), null);
    }

    private void validateBizGrpValues(XmlCreateAccount createAccount) throws Exception {
        validateBizGrpValues(createAccount, XmlValidateAccount.insert());
    }

    private void validateBizGrpValues(XmlUpdateAccount updateAccount) throws Exception {
        validateBizGrpValues(updateAccount, XmlValidateAccount.insert());
    }

    public void validateBizGrpValues(Object value, List<XmlNode> nodes) throws XmlException {
        XmlException xmlException = XmlValidate.match(value, nodes);
        if (xmlException.values.size() > 0) {
            throw xmlException;
        }
    }

    private XmlBizGrp erro(XmlHeader header, Exception exception) {
        String description = exception.getMessage() == null ? exception.getClass().getName() : exception.getMessage();
        return erro(header, new XmlException("999", description));
    }

    private XmlBizGrp erro(XmlHeader header, XmlException exception) {
        exception.printStackTrace();
        XmlErro erro = XmlErro.create(header);
        for (Value value : exception.values) {
            erro.add(value.code, value.description);
        }
        return XmlBizGrp.create(header, erro);
    }

    private void validateAccountExist(XmlCreateAccount createAccount) throws Exception {
        Account account = findAccount(createAccount.account);
        if (account != null) {
            throw new XmlException("201", "A conta ja existe");
        }
    }

    private Account returnAccountIfExist(XmlUpdateAccount createAccount) throws Exception {
        Account account = findAccount(createAccount.account);
        if (account == null) {
            throw new XmlException("201", "A conta não existe");
        }
        return account;
    }

    private Account findAccount(XmlAccount createAccount) {
        String operationalCode = createAccount.categoryParticipant.split("-")[1];
        String accountNumber = createAccount.number;
        Account account = service().findAccount(operationalCode, accountNumber);
        return account;
    }

    private AccountRepository service() {
        return new AccountRepository();
    }

}
