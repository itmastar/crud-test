package com.itmastar.crudtest.service;

import com.itmastar.crudtest.model.PersonEntity;
import com.itmastar.crudtest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public String createPerson(PersonEntity person){
        personRepository.save(person);
        return "Person record created with id :"+person.getId();
    }

    public List<PersonEntity> getPersons(){
        return personRepository.findAll();
    }

    public String getPersonById(int id){
       return "Person with id :"+id +"  is "+personRepository.findById(id);
    }

    public String updatePerson(PersonEntity updatedPerson,int id){
       Optional<PersonEntity> existingPerson= personRepository.findById(id);
       if(existingPerson.isPresent()){
          PersonEntity person= existingPerson.get();
          person.setEmail(updatedPerson.getEmail());
          person.setAge(updatedPerson.getAge());
          person.setFirstName(updatedPerson.getFirstName());
          person.setLastName(updatedPerson.getLastName());
          personRepository.save(person);
           return "Updated person record with id :"+ id +" is "+person;
       }else{
           return "Record does not exist";
       }

    }

    public String deletePerson(int id ){
        personRepository.deleteById(id);
        return " Person record with id :"+id +" is deleted";
    }
}
