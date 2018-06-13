package java8.ex02;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;


/**
 * Exercice 02 - Map
 * @param <T>
 */
public class Lambda_02_Test{

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper {
        Account map(Person p);
    }
    
    interface PersonToFirstName {
        String map(Person p);
    }
    
    interface PersonToGeneric<T>{
    	T map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    /*
    private List<Account> map(List<Person> personList, PersonToAccountMapper mapper) {
        List<Account> compte = new ArrayList<Account>();
        for(Person p : personList)
        {
        	compte.add(mapper.map(p));
        }
        return compte;
    }
    // end::map[]

    private List<String> map(List<Person> personList, PersonToFirstName mapper)
    {
    	List<String> firstName = new ArrayList<String>();
    	for(Person p: personList)
    	{
    		firstName.add(mapper.map(p));
    	}
		return firstName;
    	
    }
*/
    private <T> List<T> map(List<Person> personList, PersonToGeneric<T> mapper)
    {
    	List<T> listeT = new ArrayList<T>();
    	for(Person p: personList)
    	{
    		listeT.add(mapper.map(p));
    	}
		return listeT;
    	
    }
    
    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        /*
        PersonToAccountMapper mapper = p -> {
        	Account acc = new Account();
        	acc.setOwner(p);
        	acc.setBalance(100);
        	return acc;
        };
        
         */
        PersonToGeneric<Account> mapper = p -> {
        	Account acc = new Account();
        	acc.setOwner(p);
        	acc.setBalance(100);
        	return acc;
        };
        
        List<Account> result =  map(personList, mapper);

        assert result.size() == personList.size();
        for (Account account : result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        //PersonToFirstName mapper = p -> p.getFirstname();
        PersonToGeneric<String> mapper = p -> p.getFirstname();
        List<String> result =  map(personList, mapper);

        assert result.size() == personList.size();
        for (String firstname : result) {
            assert firstname.startsWith("first");
        }
    }
    // end::test_map_person_to_firstname[]
}
