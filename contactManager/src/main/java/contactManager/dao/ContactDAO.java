package contactManager.dao;

import java.util.List;

import contactManager.model.Contact;
 
/**
 * Defines DAO operations for the contact model.
 * @author www.codejava.net
 *
 */
public interface ContactDAO {
     
    public void save(Contact contact);
    
    public void update(Contact contact);
     
    public void delete(int contactId);
     
    public Contact get(int contactId);
     
    public List<Contact> list();
}
